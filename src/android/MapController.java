package com.dagatsoin.plugins.mapbox;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.MapboxMapOptions;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.offline.OfflineManager;
import com.mapbox.mapboxsdk.offline.OfflineRegion;
import com.mapbox.mapboxsdk.offline.OfflineRegionError;
import com.mapbox.mapboxsdk.offline.OfflineRegionStatus;
import com.mapbox.mapboxsdk.offline.OfflineTilePyramidRegionDefinition;
import com.mapbox.mapboxsdk.plugins.annotation.OnSymbolClickListener;
import com.mapbox.mapboxsdk.plugins.annotation.Symbol;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static com.mapbox.mapboxsdk.style.layers.Property.ICON_ROTATION_ALIGNMENT_VIEWPORT;

class MapController extends AppCompatActivity {
    private static float _retinaFactor;
    private final static String TAG = "MAP_CONTROLLER";
    private Style style;

    private MapView mMapView;
    private SymbolManager mSymbolManager;
    private String mStyleUrl;
    private MapboxMap mMapboxMap;
    private OfflineManager mOfflineManager;
    private OfflineRegion mOfflineRegion;
    private boolean mDownloading;
    private int mDownloadingProgress;
    private String mSelectedMarkerId;
    private ArrayList<String> mOfflineRegionsNames = new ArrayList();
    private HashMap<String, BouncingSymbol> mSymbols = new HashMap();
    private Activity activity;
    private final static String JSON_CHARSET = "UTF-8";
    private final static String JSON_FIELD_REGION_NAME = "FIELD_REGION_NAME";
    boolean isReady = false;
    Runnable mapReady;
    private boolean mPreventMapClick;

    MapView getMapView() {
        return mMapView;
    }

    int getDownloadingProgress() {
        return mDownloadingProgress;
    }

    boolean isDownloading() {
        return mDownloading;
    }

    ArrayList<String> getOfflineRegionsNames() {
        return mOfflineRegionsNames;
    }

    String getSelectedMarkerId() {
        return mSelectedMarkerId;
    }

    MapController(final JSONObject options, Activity _activity, Context context, @Nullable final ScrollView scrollView) {

        MapboxMapOptions initOptions;
        try {
            initOptions = _createMapboxMapOptions(options);
            mStyleUrl = _getStyle(options.getString("style"));
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }
        _retinaFactor = Resources.getSystem().getDisplayMetrics().density;
        mOfflineManager = OfflineManager.getInstance(context);
        activity = _activity;

        mMapView = new MapView(activity, initOptions);
        mMapView.setLayoutParams(
                new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT
                ));

        // need to do this to register a receiver which onPause later needs
        mMapView.onResume();
        mMapView.onCreate(null);

        // Prevent scroll to intercept the touch when pane the map
        if (scrollView != null) {
            mMapView.setOnTouchListener((v, event) -> {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        scrollView.requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        scrollView.requestDisallowInterceptTouchEvent(false);
                        break;
                }
                return mMapView.onTouchEvent(event);
            });
        }

        mMapView.getMapAsync(mapView -> {
            mMapboxMap = mapView;
            mapView.setStyle(new Style.Builder().fromUrl(mStyleUrl), _style -> {
                style = _style;

                // create symbol manager object
                mSymbolManager = new SymbolManager(mMapView, mMapboxMap, style);

                // set non-data-driven properties, such as:
                mSymbolManager.setIconAllowOverlap(true);
                mSymbolManager.setIconTranslate(new Float[]{-4f, 5f});
                mSymbolManager.setIconRotationAlignment(ICON_ROTATION_ALIGNMENT_VIEWPORT);
                mapReady.run();
                isReady = true;

                try {
                    // drawing initial markers
                    if (options.has("sources")) {
                        JSONArray sources = options.getJSONArray("sources");
                        for (int i = 0; i < sources.length(); i++) {
                            //todo refactor when #5626
                            if (!sources.getJSONObject(i).getJSONObject("source").getString("type").equals("geojson"))
                                throw new JSONException("Sources only handle GeoJSON at map creation");

                            String sourceId = sources.getJSONObject(i).getString("sourceId");
                            JSONObject source = sources.getJSONObject(i).getJSONObject("source");

                            String dataType = source.getJSONObject("data").getString("type");
                            if (!dataType.equals("Feature"))
                                throw new JSONException("Only feature are supported as markers source");

                            String type = source.getJSONObject("data").getJSONObject("geometry").getString("type");
                            if (!type.equals("Point"))
                                throw new JSONException("Only type Point are supported for markers");

                            addSymbol(sourceId, source.getJSONObject("data"));
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });


        });
    }

    public LatLng getCenter() {
        CameraPosition cameraPosition = mMapboxMap.getCameraPosition();
        double lat = cameraPosition.target.getLatitude();
        double lng = cameraPosition.target.getLongitude();
        return new LatLng(lat, lng);
    }

    public void setCenter(double... coords) {
        CameraPosition cameraPosition = mMapboxMap.getCameraPosition();
        double lng = coords.length > 0
                ? coords[0]
                : cameraPosition.target != null
                ? cameraPosition.target.getLongitude()
                : 0;
        double lat = coords.length > 1
                ? coords[1]
                : cameraPosition.target != null
                ? cameraPosition.target.getLatitude()
                : 0;
        double alt = coords.length > 2
                ? coords[2]
                : cameraPosition.target != null
                ? cameraPosition.target.getAltitude()
                : 1000;

        mMapboxMap.moveCamera(CameraUpdateFactory.newCameraPosition(
                new CameraPosition.Builder()
                        .target(new LatLng(lat, lng, alt))
                        .build()
        ));
    }

    void scrollMap(float x, float y) {
        mMapboxMap.scrollBy(x, y);
    }

    double getTilt() {
        return mMapboxMap.getCameraPosition().tilt;
    }

    void setTilt(double tilt) {
        mMapboxMap.moveCamera(CameraUpdateFactory.newCameraPosition(
                new CameraPosition.Builder()
                        .tilt(tilt)
                        .build()
        ));
    }

    void flyTo(JSONObject position) {
        CameraPosition cameraPosition = mMapboxMap.getCameraPosition();

        try {
            int duration = position.isNull("duration") ? 5000 : position.getInt("duration");

            mMapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(MapController.getCameraPosition(position, cameraPosition)), duration);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Download the actual region for offline use.
     *
     * @param regionName the region name
     * @param onStart    a callback fired when download start
     * @param onProgress a callback fired along the download progression
     * @param onFinish   a callback fired at the end of the download
     */
    void downloadRegion(final String regionName, final Runnable onStart, final Runnable onProgress, final Runnable onFinish) {

        // Set the style, bounds zone and the min/max zoom whidh will be available once offline.
        LatLngBounds bounds = mMapboxMap.getProjection().getVisibleRegion().latLngBounds;
        double minZoom = mMapboxMap.getCameraPosition().zoom;
        double maxZoom = mMapboxMap.getMaxZoomLevel();
        OfflineTilePyramidRegionDefinition definition = new OfflineTilePyramidRegionDefinition(
                mStyleUrl, bounds, minZoom, maxZoom, _retinaFactor);

        // Build a JSONObject using the user-defined offline region title,
        // convert it into string, and use it to create a metadata variable.
        // The metadata variable will later be passed to createOfflineRegion()
        byte[] metadata;
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(JSON_FIELD_REGION_NAME, regionName);
            String json = jsonObject.toString();
            metadata = json.getBytes(JSON_CHARSET);
        } catch (Exception e) {
            Log.e(TAG, "Failed to encode metadata: " + e.getMessage());
            metadata = null;
        }

        if (metadata != null) {
            // Create the offline region and launch the download
            mOfflineManager.createOfflineRegion(definition, metadata, new OfflineManager.CreateOfflineRegionCallback() {
                @Override
                public void onCreate(OfflineRegion offlineRegion) {
                    Log.d(TAG, "Offline region created: " + regionName);
                    mOfflineRegion = offlineRegion;
                    launchDownload(onStart, onProgress, onFinish);
                }

                @Override
                public void onError(String error) {
                    Log.e(TAG, "Error: " + error);
                }
            });
        }
    }

    private void launchDownload(final Runnable onStart, final Runnable onProgress, final Runnable onFinish) {
        // Set up an observer to handle download progress and
        // notify the user when the region is finished downloading
        // Start the progression
        mDownloading = true;
        onStart.run();

        mOfflineRegion.setObserver(new OfflineRegion.OfflineRegionObserver() {
            @Override
            public void onStatusChanged(OfflineRegionStatus status) {
                // Compute a percentage
                double percentage = status.getRequiredResourceCount() >= 0 ?
                        (100.0 * status.getCompletedResourceCount() / status.getRequiredResourceCount()) :
                        0.0;

                if (status.isComplete()) {
                    // Download complete
                    mDownloading = false;
                    onFinish.run();
                    return;
                } else if (status.isRequiredResourceCountPrecise()) {
                    // Switch to determinate state
                    onProgress.run();
                    mDownloadingProgress = ((int) Math.round(percentage));
                }

                // Log what is being currently downloaded
                Log.d(TAG, String.format("%s/%s resources; %s bytes downloaded.",
                        String.valueOf(status.getCompletedResourceCount()),
                        String.valueOf(status.getRequiredResourceCount()),
                        String.valueOf(status.getCompletedResourceSize())));
            }

            @Override
            public void onError(OfflineRegionError error) {
                Log.e(TAG, "onError reason: " + error.getReason());
                Log.e(TAG, "onError message: " + error.getMessage());
            }

            @Override
            public void mapboxTileCountLimitExceeded(long limit) {
                Log.e(TAG, "Mapbox tile count limit exceeded: " + limit);
            }
        });

        // Change the region state
        mOfflineRegion.setDownloadState(OfflineRegion.STATE_ACTIVE);
    }

    void pauseDownload() {
        mOfflineRegion.setDownloadState(OfflineRegion.STATE_INACTIVE);
    }

    void getOfflineRegions(final Runnable callback) {
        mOfflineManager.listOfflineRegions(new OfflineManager.ListOfflineRegionsCallback() {
            @Override
            public void onList(final OfflineRegion[] offlineRegions) {

                // Check result. If no regions have been
                // downloaded yet, notify user and return
                if (offlineRegions == null || offlineRegions.length == 0) {
                    return;
                }

                // Clean the last ref array and add all of the region names to the list.
                mOfflineRegionsNames.clear();
                for (OfflineRegion offlineRegion : offlineRegions) {
                    mOfflineRegionsNames.add(getRegionName(offlineRegion));
                }
                callback.run();
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    void removeOfflineRegion(final int regionSelected, final Runnable onDeleteCallback) {
        mOfflineManager.listOfflineRegions(new OfflineManager.ListOfflineRegionsCallback() {
            @Override
            public void onList(final OfflineRegion[] offlineRegions) {

                if (offlineRegions.length > regionSelected - 1) return;

                offlineRegions[regionSelected].delete(new OfflineRegion.OfflineRegionDeleteCallback() {
                    @Override
                    public void onDelete() {
                        onDeleteCallback.run();
                    }

                    @Override
                    public void onError(String error) {
                    }
                });
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    private String getRegionName(OfflineRegion offlineRegion) {
        // Get the region name from the offline region metadata
        String regionName;

        try {
            byte[] metadata = offlineRegion.getMetadata();
            String json = new String(metadata, JSON_CHARSET);
            JSONObject jsonObject = new JSONObject(json);
            regionName = jsonObject.getString(JSON_FIELD_REGION_NAME);
        } catch (Exception e) {
            Log.e(TAG, "Failed to decode metadata: " + e.getMessage());
            regionName = "Region " + offlineRegion.getID();
        }
        return regionName;
    }


    void addSymbol(String id, JSONObject symbolParams) throws JSONException {
        BouncingSymbol bouncingSymbol = mSymbols.get(id);
        LatLng latLng;

        if (bouncingSymbol != null) {
            removeMarker(id);
        }
        JSONObject geometry = symbolParams.isNull("geometry") ? null : symbolParams.getJSONObject("geometry");

        if (geometry != null) {
            latLng = new LatLng(
                    geometry.getJSONArray("coordinates").getDouble(1),
                    geometry.getJSONArray("coordinates").getDouble(0)
            );
        } else throw new JSONException("No position found in marker.");

        bouncingSymbol = new BouncingSymbol(id, latLng, symbolParams.getJSONObject("properties"), activity, style, mSymbolManager);

        // Store in the map markers collection
        mSymbols.put(id, bouncingSymbol);
    }

    void setSymbolPosition(String id, LatLng latLng) throws JSONException {
        BouncingSymbol bouncingSymbol = mSymbols.get(id);
        if (bouncingSymbol != null) {
            bouncingSymbol.setLatLng(latLng);
        } else throw new JSONException(" MapController.setSymbolPosition: unknown marker id " + id);
    }

    void setMarkerIcon(String id, JSONObject imageObject) throws JSONException {
        BouncingSymbol bouncingSymbol = mSymbols.get(id);
        if (bouncingSymbol != null) {
            bouncingSymbol.setIcon(imageObject);
        } else throw new JSONException(" MapController.setMarkerIcon: unknown marker id " + id);
    }


    void removeMarkers(ArrayList<String> ids) {
        for (int i = 0; i < ids.size(); i++) removeMarker(ids.get(i));
    }

    void removeMarker(String id) {
        BouncingSymbol bouncingSymbol = mSymbols.get(id);
        if (bouncingSymbol == null) return;
        mSymbolManager.delete(bouncingSymbol.get());
        mSymbols.remove(id);
    }

    void addMarkerCallBack(Runnable callback) {
        if (!isReady) return;
        mSymbolManager.addClickListener(new SymbolClickListener(callback));
        mMapboxMap.addOnMapClickListener(new MapClickListener(callback));
    }

    public double getZoom() {
        return mMapboxMap.getCameraPosition().zoom;
    }

    public void setZoom(double zoom) {
        CameraPosition position = new CameraPosition.Builder()
                .zoom(zoom)
                .build();

        mMapboxMap.moveCamera(CameraUpdateFactory
                .newCameraPosition(position));
    }

    void zoomTo(double zoom) {
        CameraPosition position = new CameraPosition.Builder()
                .zoom(zoom)
                .build();

        mMapboxMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(position));
    }

    LatLngBounds getBounds() {
        return mMapboxMap.getProjection().getVisibleRegion().latLngBounds;
    }

    PointF convertCoordinates(LatLng coords) {
        return mMapboxMap.getProjection().toScreenLocation(coords);
    }

    LatLng convertPoint(PointF point) {
        return mMapboxMap.getProjection().fromScreenLocation(point);
    }

    private MapboxMapOptions _createMapboxMapOptions(JSONObject options) throws JSONException {
        MapboxMapOptions opts = new MapboxMapOptions();
        opts.attributionEnabled(options.isNull("hideAttribution") || !options.getBoolean("hideAttribution"));
        opts.logoEnabled(options.isNull("hideLogo") || options.getBoolean("hideLogo"));
        opts.camera(MapController.getCameraPosition(options.isNull("cameraPosition") ? null : options.getJSONObject("cameraPosition"), null));
        opts.compassEnabled(options.isNull("hideCompass") || !options.getBoolean("hideCompass"));
        opts.rotateGesturesEnabled(options.isNull("disableRotation") || !options.getBoolean("disableRotation"));
        opts.scrollGesturesEnabled(options.isNull("disableScroll") || !options.getBoolean("disableScroll"));
        opts.zoomGesturesEnabled(options.isNull("disableZoom") || !options.getBoolean("disableZoom"));
        opts.tiltGesturesEnabled(options.isNull("disableTilt") || !options.getBoolean("disableTilt"));
        opts.attributionMargins((!options.isNull("hideAttribution") && options.getBoolean("hideAttribution")) ? new int[]{-300, 0, 0, 0} : null);
        opts.logoMargins((!options.isNull("hideLogo") && options.getBoolean("hideLogo")) ? new int[]{-300, 0, 0, 0} : null);
        return opts;
    }

    private static String _getStyle(final String requested) {
        if ("light".equalsIgnoreCase(requested)) {
            return Style.LIGHT;
        } else if ("dark".equalsIgnoreCase(requested)) {
            return Style.DARK;
        } else if ("satellite".equalsIgnoreCase(requested)) {
            return Style.SATELLITE;
        } else if ("streets".equalsIgnoreCase(requested)) {
            return Style.MAPBOX_STREETS;
        } else {
            return requested;
        }
    }

    private static CameraPosition getCameraPosition(JSONObject position, @Nullable CameraPosition start) throws JSONException {
        CameraPosition.Builder builder = new CameraPosition.Builder(start);

        if (position != null) {
            if (!position.isNull("target")) {
                JSONObject target = position.getJSONObject("target");
                builder.target(new LatLng(target.getDouble("lat"), target.getDouble("lng")));
            }

            if (!position.isNull("zoom")) {
                builder.zoom(position.getDouble("zoom"));
            }

            if (!position.isNull("bearing")) {
                builder.bearing(position.getDouble("bearing"));
            }

            if (!position.isNull("tilt")) {
                builder.tilt(position.getDouble("tilt"));
            }
        }
        return builder.build();
    }

    JSONArray getJSONSymbolScreenPositions() {
        JSONArray positions = new JSONArray();
        try {
            for (Map.Entry<String, BouncingSymbol> entry : mSymbols.entrySet()) {
                String id = entry.getKey();
                Symbol symbol = entry.getValue().get();
                PointF screenPosition = mMapboxMap.getProjection().toScreenLocation(symbol.getLatLng());
                JSONObject position = new JSONObject();
                position.put("id", id);
                position.put("x", screenPosition.x);
                position.put("y", screenPosition.y);
                positions.put(position);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return positions;
    }

    JSONObject getJSONCameraScreenPosition() throws JSONException {
        CameraPosition position = mMapboxMap.getCameraPosition();
        PointF screenPosition = mMapboxMap.getProjection().toScreenLocation(position.target);
        try {
            return new JSONObject()
                    .put("x", screenPosition.x)
                    .put("y", screenPosition.y)
                    .put("alt", position.target.getAltitude())
                    .put("tilt", position.tilt)
                    .put("bearing", position.bearing);
        } catch (JSONException e) {
            e.printStackTrace();
            throw new JSONException(e.getMessage());
        }
    }

    JSONObject getJSONCameraGeoPosition() throws JSONException {
        CameraPosition position = mMapboxMap.getCameraPosition();

        try {
            return new JSONObject()
                    .put("zoom", position.zoom)
                    .put("long", position.target != null ? position.target.getLongitude() : 0)
                    .put("alt", position.target != null ? position.target.getAltitude() : 0)
                    .put("tilt", position.tilt)
                    .put("bearing", position.bearing);
        } catch (JSONException e) {
            e.printStackTrace();
            throw new JSONException(e.getMessage());
        }
    }

    public void addOnWillStartLoadingMapListener(Runnable callback) {
        mMapView.addOnWillStartLoadingMapListener(callback::run);
    }

    public void addOnWillStartRenderingMapListener(Runnable callback) {
        mMapView.addOnWillStartRenderingMapListener(callback::run);
    }

    public void addOnCameraWillChangeListener(Runnable callback) {
        mMapView.addOnCameraWillChangeListener((boolean isAnimated) -> callback.run());
    }

    public void addOnCameraDidChangeListener(Runnable callback) {
        mMapView.addOnCameraDidChangeListener((boolean isAnimated) -> callback.run());
    }

    public void addOnDidFinishLoadingStyleListener(Runnable callback) {
        mMapView.addOnDidFinishLoadingStyleListener(callback::run);
    }

    public void addOnSourceChangedListener(Runnable callback) {
        mMapView.addOnSourceChangedListener((String id) -> callback.run());
    }

    public void addOnWillStartRenderingFrameListener(Runnable callback) {
        mMapView.addOnWillStartRenderingFrameListener(callback::run);
    }

    public void addOnDidFinishRenderingFrameListener(Runnable callback) {
        mMapView.addOnDidFinishRenderingFrameListener((boolean fully) -> callback.run());
    }

    public void addOnDidFinishLoadingMapListener(Runnable callback) {
        mMapView.addOnDidFinishLoadingMapListener(callback::run);
    }

    public void addOnDidFinishRenderingMapListener(Runnable callback) {
        mMapView.addOnDidFinishRenderingMapListener((boolean fully) -> callback.run());
    }

    private class SymbolClickListener implements OnSymbolClickListener {
        private Runnable _callback;

        SymbolClickListener(Runnable callback) {
            _callback = callback;
        }

        @Override
        public void onAnnotationClick(Symbol symbol) {
            Set<Map.Entry<String, BouncingSymbol>> elements = mSymbols.entrySet();
            Iterator<Map.Entry<String, BouncingSymbol>> iterator = elements.iterator();
            Map.Entry<String, BouncingSymbol> entry;
            while (iterator.hasNext()) {
                entry = iterator.next();
                BouncingSymbol bouncingSymbol = entry.getValue();
                if (bouncingSymbol.get() == symbol) {
                    bouncingSymbol.select();
                    mSelectedMarkerId = entry.getKey();
                    break;
                }
            }
            // This prevent the MapClickListener to trigger.
            mPreventMapClick = true;
            _callback.run();
        }
    }

    private class MapClickListener implements MapboxMap.OnMapClickListener {
        private Runnable _callback;

        MapClickListener(Runnable callback) {
            _callback = callback;
        }

        @Override
        public boolean onMapClick(@NonNull LatLng point) {
            if (mPreventMapClick) {
                mPreventMapClick = false;
                return true;
            } else {
                final BouncingSymbol symbol = mSymbols.get(mSelectedMarkerId);
                if (symbol != null) {
                    symbol.deselect();
                }

                mSelectedMarkerId = "";

                _callback.run();
                return true;
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mMapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mMapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }
}