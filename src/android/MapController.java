package com.dagatsoin.plugins.mapbox;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.animation.BounceInterpolator;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;
import com.google.gson.JsonObject;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.MapboxMapOptions;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.expressions.Expression;
import com.mapbox.mapboxsdk.style.layers.Layer;
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.CannotAddSourceException;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class MapController extends AppCompatActivity implements MapboxMap.OnMapClickListener {
    @Nullable private String mSelectableFeaturePropType;
    @Nullable private String mSelectedFeatureLayerId;
    @Nullable private String mSelectedFeatureSourceId;
    private Style style;

    private MapView mMapView;
    private String mStyleUrl;
    private MapboxMap mMapboxMap;
    private ArrayList<String> mOfflineRegionsNames = new ArrayList<>();
    private Activity mActivity;
    boolean isReady = false;
    Runnable mapReady;
    private FeatureCollection mSelectedFeatureCollection =  FeatureCollection.fromFeatures(new ArrayList<>());
    private boolean mHasSelectedFeature;

    MapView getMapView() {
        return mMapView;
    }

    String getSelecteFeatureCollection() {
        return mSelectedFeatureCollection.toJson();
    }

    MapController(
            final JSONObject options,
            Activity activity,
            @Nullable String selectedFeatureLayerId,
            @Nullable String selectedFeatureSourceId,
            @Nullable String selectableFeaturePropType,
            @Nullable final ScrollView scrollView
    ) {

        MapboxMapOptions initOptions;
        try {
            initOptions = createMapboxMapOptions(options);
            mStyleUrl = getStyle(options.getString("style"));
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }
        mActivity = activity;

        mMapView = new MapView(mActivity, initOptions);
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
            mSelectedFeatureSourceId = selectedFeatureSourceId;
            mSelectedFeatureLayerId = selectedFeatureLayerId;
            mSelectableFeaturePropType= selectableFeaturePropType;
            mMapboxMap.addOnMapClickListener(MapController.this);
            mapView.setStyle(new Style.Builder().fromUrl(mStyleUrl), _style -> {
                style = _style;
                isReady = true;
                mapReady.run();
            });
        });
    }

    public OfflineController getOfflineController() {
        @Nullable OfflineController offlineController = OfflineControllerPool.get(mStyleUrl);
        return offlineController != null ? offlineController : OfflineControllerPool.create(mActivity, mStyleUrl);
    }

    public void addFeatureCollection(String featureCollectionId, FeatureCollection featureCollection ) {
        final GeoJsonSource geoJsonSource = new GeoJsonSource(featureCollectionId, featureCollection);
        if (style.getSource(featureCollectionId) == null) {
            addGeoJsonSource(geoJsonSource);
        }
    }

    public void addFeature(String featureId, Feature feature ) {
        final GeoJsonSource geoJsonSource = new GeoJsonSource(featureId, feature);
        if (style.getSource(featureId) == null) {
            addGeoJsonSource(geoJsonSource);
        }
    }

    public void addGeoJsonSource(String sourceId) {
        if (style.getSource(sourceId) == null) {
            style.addSource(new GeoJsonSource(sourceId));
        }
    }

    private void addGeoJsonSource(GeoJsonSource geoJsonSource) {
        if (style.getSource(geoJsonSource.getId()) == null) {
            // Throw when a source exists with the same id
            style.addSource(geoJsonSource);
        }
    }

    public boolean removeSource(String sourceId) {
        try {
            // Throw when source is still in use
            return style.removeSource(sourceId);
        } catch (CannotAddSourceException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setSourceGeoJsonData(String sourceId, FeatureCollection featureCollection) {
        final GeoJsonSource source = style.getSourceAs(sourceId);
        if (source != null) {
            // https://github.com/mapbox/mapbox-gl-native/issues/14565#issuecomment-496923239
            source.setGeoJson(FeatureCollection.fromFeatures(new ArrayList<>(Objects.requireNonNull(featureCollection.features()))));
        }
    }

    public void setSourceGeoJsonData(String sourceId, Feature feature) {
        final GeoJsonSource source = style.getSourceAs(sourceId);
        if (source != null) {
            source.setGeoJson(feature);
        }
    }

    public void addSymbolLayer(
            String layerId,
            String sourceId,
            Integer minZoom,
            Integer maxZoom,
            @Nullable Expression filter,
            @Nullable String beforeId
    ) {
        if (style.getLayer(layerId) != null) return;

        final SymbolLayer symbolLayer = new SymbolLayer(layerId, sourceId);
        symbolLayer.setMinZoom(minZoom);
        symbolLayer.setMaxZoom(maxZoom);
        if (filter != null) {
            symbolLayer.setFilter(filter);
        }

        if (beforeId == null || beforeId.isEmpty()) {
            style.addLayer(symbolLayer);
        } else {
            if (style.getLayer(layerId) != null) {
                removeLayer(layerId);
            }
            style.addLayerBelow(symbolLayer, beforeId);
        }
    }

    public boolean removeLayer(String layerId) {
        return style.removeLayer(layerId);
    }

    public void addImage(String imageId, JSONObject jsonImage) {
        try {
            final Bitmap bitmap = createImage(jsonImage);
            style.addImage(imageId, bitmap);
        } catch (JSONException | IOException | SVGParseException e) {
            e.printStackTrace();
        }
    }

    public void removeImage(String imageId) {
        style.removeImage(imageId);
    }


    public void setLayoutPropertyIconImage(String layerId, String imageId) {
        final Layer layer = style.getLayer(layerId);
        if (layer != null) {
            layer.setProperties(PropertyFactory.iconImage(imageId));
        }
    }

    public void setLayoutPropertyOffset(String layerId, Float[] offset) {
        final Layer layer = style.getLayer(layerId);
        if (layer != null) {
            layer.setProperties(PropertyFactory.iconOffset(offset));
        }
    }

    public void setLayoutPropertySize(String layerId, float size) {
        final Layer layer = style.getLayer(layerId);
        if (layer != null) {
            layer.setProperties(PropertyFactory.iconSize(size));
        }
    }

    public void setLayoutPropertyIconOverlap(String layerId, boolean isOverlap) {
        final Layer layer = style.getLayer(layerId);
        if (layer != null) {
            layer.setProperties(PropertyFactory.iconAllowOverlap(isOverlap));
        }
    }

    private float retinaFactor = Resources.getSystem().getDisplayMetrics().density;

    private int applyRetinaFactor(long d) {
        return Math.round(d * retinaFactor);
    }

    private BitmapDrawable createSVG(SVG svg, int width, int height) {
        if (width == 0)
            width = applyRetinaFactor((int) Math.ceil(svg.getDocumentWidth()));
        if (height == 0)
            height = applyRetinaFactor((int) Math.ceil(svg.getDocumentHeight()));
        Bitmap newBM = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas bmCanvas = new Canvas(newBM);
        svg.renderToCanvas(bmCanvas);
        return new BitmapDrawable(mActivity.getApplicationContext().getResources(), newBM);
    }

    /**
     * Creates icon for symbol from url or local file
     *
     * @param imageObject The properties.image part of a JSON feature
     * @return an icon with a custom image
     */
    // Thanks @anothar
    private Bitmap createImage(JSONObject imageObject) throws JSONException, IOException, SVGParseException {
        InputStream stream = null;
        BitmapDrawable bitmapDrawable;
        Bitmap bitmap = null;
        Context ctx = mActivity.getApplicationContext();
        AssetManager am = ctx.getResources().getAssets();

        try {
            if (imageObject != null) {
                if (imageObject.has("uri")) {
                    String stringURI = imageObject.getString("uri");

                    final Uri uri = Uri.parse(stringURI);
                    final String uriPath = uri.getPath() != null ? uri.getPath() : "";
                    final boolean isAsset = uriPath.contains("/android_asset/");
                    final String filesDir = mActivity.getFilesDir().getPath();
                    final boolean doesContainFilesDirInPath = uriPath.contains(filesDir);
                    final int startIndex = isAsset
                            ? "/android_asset/".length()
                            : doesContainFilesDirInPath
                            ? filesDir.length()
                            : 0;
                    final int endIndex = uriPath.lastIndexOf('/') + 1;
                    String path = isAsset
                            ? uriPath.substring(startIndex, endIndex)
                            : "www/" + uriPath.substring(startIndex, endIndex);
                    final String fileName = uri.getLastPathSegment();

                    // We first look in the current asset bundle.
                    final File iconFile = new File(mActivity.getFilesDir(), path + fileName);

                    if (iconFile.exists()) {
                        stream = new FileInputStream(iconFile);
                    }
                    // If file does not exists we get the original version in the initial asset bundle with AssetsManager
                    else {
                        try {
                            stream = am.open(path + fileName);
                        } catch (IOException e) {
                            throw new JSONException("File not found: " + uri);
                        }
                    }

                    if (fileName != null && fileName.endsWith(".svg")) {
                        bitmapDrawable = createSVG(SVG.getFromInputStream(stream), imageObject.has("width") ? applyRetinaFactor(imageObject.getInt("width")) : 0,
                                imageObject.has("height") ? applyRetinaFactor(imageObject.getInt("height")) : 0);
                    } else {
                        bitmapDrawable = new BitmapDrawable(ctx.getResources(), stream);
                    }
                } else if (imageObject.has("data")) {
                    byte[] decodedBytes = Base64.decode(imageObject.getString("data"), 0);
                    bitmapDrawable = new BitmapDrawable(ctx.getResources(), BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length));

                } else if (imageObject.has("svg")) {
                    bitmapDrawable = createSVG(SVG.getFromString(imageObject.getString("svg")), imageObject.has("width") ? applyRetinaFactor(imageObject.getInt("width")) : 0,
                            imageObject.has("height") ? applyRetinaFactor(imageObject.getInt("height")) : 0);
                } else {
                    throw new JSONException("Not found image data");
                }
                if (imageObject.has("width") && imageObject.has("height")) {
                    bitmap = new BitmapDrawable(ctx.getResources(),
                            Bitmap.createScaledBitmap(bitmapDrawable.getBitmap(),
                                    applyRetinaFactor(imageObject.getInt("width")),
                                    applyRetinaFactor(imageObject.getInt("height")),
                                    true
                            )).getBitmap();
                } else {
                    bitmap = bitmapDrawable.getBitmap();
                }
            }
        } finally {
            if (stream != null)
                stream.close();
        }
        return bitmap;
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

    void addMapClickCallback(Runnable callback) {
        if (!isReady) return;
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

    private MapboxMapOptions createMapboxMapOptions(JSONObject options) throws JSONException {
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

    private static String getStyle(final String requested) {
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

    JSONObject getJSONCameraScreenPosition() throws JSONException {
        CameraPosition position = mMapboxMap.getCameraPosition();
        PointF screenPosition = convertCoordinates(position.target);
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

    @Override
    public boolean onMapClick(@NonNull LatLng point) {
        if (style != null && mSelectedFeatureLayerId != null) {

            final PointF pixel = convertCoordinates(point);
            List<Feature> features = mMapboxMap.queryRenderedFeatures(pixel);
            Feature feature = null;
            for (int i = 0; i < features.size(); i++ ) {
                final JsonObject properties = features.get(i).properties();
                if (properties != null && properties.has("type") && properties.get("type").getAsString().equals(mSelectableFeaturePropType)) {
                    feature = features.get(i);
                    break;
                }
            }
            List<Feature> selectedFeature = mMapboxMap.queryRenderedFeatures(pixel, mSelectedFeatureLayerId);

            if (selectedFeature.size() > 0 && mHasSelectedFeature) {
                return false;
            }

            if (feature == null) {
                if (mHasSelectedFeature) {
                    deselectFeature();
                }
                return false;
            }

            if (mHasSelectedFeature) {
                deselectFeature();
            }

            selectFeature(feature);
        }
        return false;
    }

    private TimeInterpolator interpolator = new BounceInterpolator();

    private void selectFeature(final Feature feature) {
        if (mSelectedFeatureLayerId == null || mSelectedFeatureSourceId == null) return;

        final SymbolLayer selectedFeatureLayer = (SymbolLayer) style.getLayer(mSelectedFeatureLayerId);

        if (selectedFeatureLayer == null) return;

        GeoJsonSource source = style.getSourceAs(mSelectedFeatureSourceId);
        if (source != null) {
            source.setGeoJson(feature);
        }
        ValueAnimator featureAnimator = new ValueAnimator();
        featureAnimator.setObjectValues(0f, 1f);
        featureAnimator.setDuration(800);
        featureAnimator.setInterpolator(interpolator);
        featureAnimator.addUpdateListener(animator -> {
            final float factor = (float)(animator.getAnimatedValue());
            selectedFeatureLayer.setProperties(PropertyFactory.iconSize((float) (1 + .25 * factor)));
        });
        featureAnimator.start();
        mHasSelectedFeature = true;
    }

    public void deselectFeature() {
        if (mSelectedFeatureSourceId == null) return;

        final GeoJsonSource source = style.getSourceAs(mSelectedFeatureSourceId);
        if (source != null) {
            source.setGeoJson(FeatureCollection.fromFeatures(
                    new Feature[]{}));
        }
        mHasSelectedFeature = false;
    }

    private class MapClickListener implements MapboxMap.OnMapClickListener {
        private Runnable callback;

        MapClickListener(Runnable cb) {
            callback = cb;
        }

        @Override
        public boolean onMapClick(@NonNull LatLng point) {
            List<Feature> features = mMapboxMap.queryRenderedFeatures(convertCoordinates(point));
            Objects.requireNonNull(mSelectedFeatureCollection.features()).clear();
            Objects.requireNonNull(mSelectedFeatureCollection.features()).addAll(features);
            callback.run();
            return true;
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