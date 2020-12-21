package com.dagatsoin.plugins.mapbox;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewTreeObserver;

import com.cocoahero.android.geojson.GeoJSON;
import com.cocoahero.android.geojson.GeoJSONObject;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.style.expressions.Expression;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.Iterator;

public class CDVMapbox extends CordovaPlugin implements ViewTreeObserver.OnScrollChangedListener {
    private static final String ADD_IMAGE = "ADD_IMAGE";
    private static final String ADD_LAYER = "ADD_LAYER";
    private static final String ADD_MAP_CLICK_CALLBACK = "ADD_MAP_CLICK_CALLBACK";
    private static final String ADD_SOURCE = "ADD_SOURCE";
    private static final String ADD_ON_MOVE_LISTENER = "ADD_ON_MOVE_LISTENER";
    private static final String ADD_ON_FLING_LISTENER = "ADD_ON_FLING_LISTENER";
    private static final String ADD_ON_ROTATE_LISTENER = "ADD_ON_ROTATE_LISTENER";
    private static final String ADD_ON_SCALE_LISTENER = "ADD_ON_SCALE_LISTENER";
    private static final String ADD_ON_WILL_START_LOADING_MAP_LISTENER = "ADD_ON_WILL_START_LOADING_MAP_LISTENER";
    private static final String ADD_ON_WILL_START_RENDERING_MAP_LISTENER = "ADD_ON_WILL_START_RENDERING_MAP_LISTENER";
    private static final String ADD_ON_CAMERA_WILL_CHANGE_LISTENER = "ADD_ON_CAMERA_WILL_CHANGE_LISTENER";
    private static final String ADD_ON_CAMERA_DID_CHANGE_LISTENER = "ADD_ON_CAMERA_DID_CHANGE_LISTENER";
    private static final String ADD_ON_DID_FINISH_LOADING_STYLE_LISTENER = "ADD_ON_DID_FINISH_LOADING_STYLE_LISTENER";
    private static final String ADD_ON_SOURCE_CHANGED_LISTENER = "ADD_ON_SOURCE_CHANGED_LISTENER";
    private static final String ADD_ON_WILL_START_RENDERING_FRAME_LISTENER = "ADD_ON_WILL_START_RENDERING_FRAME_LISTENER";
    private static final String ADD_ON_DID_FINISH_RENDERING_FRAME_LISTENER = "ADD_ON_DID_FINISH_RENDERING_FRAME_LISTENER";
    private static final String ADD_ON_DID_FINISH_LOADING_MAP_LISTENER = "ADD_ON_DID_FINISH_LOADING_MAP_LISTENER";
    private static final String ADD_ON_DID_FINISH_RENDERING_MAP_LISTENER = "ADD_ON_DID_FINISH_RENDERING_MAP_LISTENER";
    private static final String CONVERT_COORDINATES = "CONVERT_COORDINATES";
    private static final String CONVERT_POINT = "CONVERT_POINT";
    private static final String DELETE_OFFLINE_REGION = "DELETE_OFFLINE_REGION";
    private static final String DESTROY = "DESTROY";
    private static final String DOWNLOAD_REGION = "DOWNLOAD_REGION";
    private static final String FLY_TO = "FLY_TO";
    private static final String GET_BOUNDS = "GET_BOUNDS";
    private static final String GET_CAMERA_POSITION = "GET_CAMERA_POSITION";
    private static final String GET_CENTER = "GET_CENTER";
    private static final String GET_OFFLINE_REGION_LIST = "GET_OFFLINE_REGION_LIST";
    private static final String GET_PITCH = "GET_PITCH";
    private static final String GET_ZOOM = "GET_ZOOM";
    private static final String HIDE = "HIDE";
    private static final String PAUSE_DOWNLOAD = "PAUSE_DOWNLOAD";
    private static final String RESUME_DOWNLOAD = "RESUME_DOWNLOAD";
    private static final String REMOVE_IMAGE = "REMOVE_IMAGE";
    private static final String REMOVE_SOURCE = "REMOVE_SOURCE";
    private static final String REMOVE_LAYER = "REMOVE_LAYER";
    private static final String RESIZE = "RESIZE";
    private static final String SCROLL_MAP = "SCROLL_MAP";
    private static final String SET_CENTER = "SET_CENTER";
    private static final String SET_CLICKABLE = "SET_CLICKABLE";
    private static final String SET_CONTAINER = "SET_CONTAINER";
    private static final String SET_DEBUG = "SET_DEBUG";
    private static final String SET_GEO_JSON = "SET_GEO_JSON";
    private static final String SET_LAYOUT_PROPERTY = "SET_LAYOUT_PROPERTY";
    private static final String SET_PITCH = "SET_PITCH";
    private static final String SET_ZOOM = "SET_ZOOM";
    private static final String SHOW = "SHOW";
    private static final String DESELECT = "DESELECT";
    private static final String ZOOM_TO = "ZOOM_TO";

    private static final String MAPBOX_ACCESSTOKEN_RESOURCE_KEY = "mapbox_accesstoken";
    private CordovaWebView _webView;
    private Activity activity;
    @Nullable MapLayout mapLayout;

    PluginLayout pluginLayout;

    /**
     * Handler listening to scroll changes.
     * Important! Both plugin layout and map layout have to be updated.
     */
    @Override
    public void onScrollChanged() {
        if (pluginLayout == null) {
            return;
        }
        int scrollX = _webView.getView().getScrollX();
        int scrollY = _webView.getView().getScrollY();

        pluginLayout.scrollTo(scrollX, scrollY);
        if(mapLayout != null) {
            mapLayout.onScroll(scrollX, scrollY);
        }
    }

    @Override
    public void initialize(CordovaInterface cordova, final CordovaWebView webView) {
        super.initialize(cordova, webView);

        _webView = webView;
        activity = cordova.getActivity();
        _webView.getView().getViewTreeObserver().addOnScrollChangedListener(CDVMapbox.this);

        /*
         * Init the plugin layer responsible to capture touch events.
         * It permits to have Dom Elements on top of the map.
         * If a touch event occurs in one of the embed rectangles and outside of a inner html element,
         * the plugin layer considers that is a map action (drag, pan, etc.).
         * If not, the user surely want to access the UIWebView.
         */
        pluginLayout = new PluginLayout(_webView.getView(), activity);

        try {
            int mapboxAccesstokenResourceId = cordova.getActivity().getResources().getIdentifier(MAPBOX_ACCESSTOKEN_RESOURCE_KEY, "string", cordova.getActivity().getPackageName());
            final String _accessToken = cordova.getActivity().getString(mapboxAccesstokenResourceId);

            activity.runOnUiThread(() -> Mapbox.getInstance(activity, _accessToken));
        } catch (Resources.NotFoundException e) {
            // we'll deal with this when the _accessToken property is read, but for now let's dump the error:
            e.printStackTrace();
        }
    }

    public boolean execute(final String action, final CordovaArgs args, final CallbackContext callbackContext) {
        try {
            if (SHOW.equals(action)) {
                if (mapLayout != null) {
                    if (mapLayout.getMapCtrl().getMapView().getVisibility() == View.GONE) {
                        activity.runOnUiThread(() -> {
                            mapLayout.getMapCtrl().getMapView().setVisibility(View.VISIBLE);
                            callbackContext.success();
                        });
                    } else {
                        callbackContext.error("Map is already displayed");
                    }
                } else {
                    activity.runOnUiThread(() -> {
                        mapLayout = new MapLayout(args, this, activity, callbackContext);
                        /* If it is the first map, we set the general layout.
                         * Arrange the layers. The final order is:
                         * - root (Application View)
                         *   - pluginLayout
                         *     - frontLayout
                         *       - webView
                         *     - scrollView
                         *       - scrollFrameLayout
                         *         - map
                         *         - background
                         */

                        pluginLayout.buildViewHierarchy(mapLayout.getViewGroup());
                        mapLayout.setContainer(args, callbackContext);

                        // Wait for the map to be ready then callback JS
                        mapLayout.getMapCtrl().mapReady = callbackContext::success;
                    });
                }
                return true;
            } else if (DOWNLOAD_REGION.equals(action)) {
                cordova.getThreadPool().execute(() -> {
                    try {
                        if (args.isNull(0)) {
                            throw new JSONException(action + " needs options object type of {\n" +
                                    "                            regionName: string // must be unique.\n" +
                                    "                            styleUrl?: string // only when the map is not display yet or the style is different from the current one.\n" +
                                    "                            bounds: {sw: [number, number], ne: [number, number]}\n" +
                                    "                            minZoom: number\n" +
                                    "                            maxZoom: number\n" +
                                    "                        },");
                        }
                        final JSONObject options = args.getJSONObject(0);
                        @Nullable final String styleUrl = options.has("styleUrl") ? options.getString("styleUrl") : null;
                        final OfflineController offlineController = getOfflineController(mapLayout, styleUrl, activity);
                        final String regionName = options.getString("regionName");

                        final JSONObject jsonBounds = options.getJSONObject("bounds");
                        final JSONArray NE = jsonBounds.getJSONArray("ne");
                        final JSONArray SW = jsonBounds.getJSONArray("sw");
                        final LatLngBounds latLngBounds = new LatLngBounds.Builder()
                                .include(new LatLng(NE.getDouble(0), NE.getDouble(1)))
                                .include(new LatLng(SW.getDouble(0), SW.getDouble(1)))
                                .build();
                        final int minZoom = options.getInt("minZoom");
                        final int maxZoom = options.getInt("maxZoom");

                        Runnable progressCallback = () -> {
                            final OfflineController.OfflineRegionState dlState = offlineController.getOfflineRegionDownloadState(regionName);
                            if (dlState != null) {
                                PluginResult result = new PluginResult(PluginResult.Status.OK, toJSONRegionState(dlState));
                                result.setKeepCallback(true);
                                callbackContext.sendPluginResult(result);
                            }
                        };


                        try {
                            ArrayList<OfflineController.OfflineRegionState> states = offlineController.getOfflineRegionStates();
                            boolean isAlreadyDownloaded = false;
                            for (OfflineController.OfflineRegionState state: states) {
                                if (state.regionName.equals("regionName")) {
                                    isAlreadyDownloaded = true;
                                }
                            }
                            if (!isAlreadyDownloaded) {
                                offlineController.downloadRegion(regionName, latLngBounds, minZoom, maxZoom, progressCallback);
                            } else {
                                callbackContext.error(new JSONObject("{error: 'MAP_EXISTS'}"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            } else if (PAUSE_DOWNLOAD.equals(action)) {
                cordova.getThreadPool().execute(() -> {
                    JSONObject options = null;
                    try {
                        options = args.getJSONObject(0);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        if (options != null) {
                            @Nullable final String regionName = options.has("regionName") ? options.getString("regionName") : null;
                            @Nullable final String styleUrl = options.has("styleUrl") ? options.getString("styleUrl") : null;
                            OfflineController offlineController = getOfflineController(mapLayout, styleUrl, activity);
                            offlineController.pauseDownload(regionName);
                            callbackContext.success();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            } else if (RESUME_DOWNLOAD.equals(action)) {
                cordova.getThreadPool().execute(() -> {
                    JSONObject options = null;
                    try {
                        options = args.getJSONObject(0);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (options != null) {
                        try {
                            @Nullable final String regionName = options.has("regionName") ? options.getString("regionName") : null;
                            @Nullable final String styleUrl = options.has("styleUrl") ? options.getString("styleUrl") : null;
                            OfflineController offlineController = getOfflineController(mapLayout, styleUrl, activity);
                            offlineController.resumeDownload(regionName);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    callbackContext.success();
                });
            } else if (GET_OFFLINE_REGION_LIST.equals(action)) {
                cordova.getThreadPool().execute(() -> {
                    try {
                        @Nullable final String styleUrl = args.optString(0).equals("") ? null : args.optString(0);
                        final OfflineController offlineController = getOfflineController(mapLayout, styleUrl, activity);
                        final ArrayList<OfflineController.OfflineRegionState> states = offlineController.getOfflineRegionStates();
                        final ArrayList<JSONObject> res = new ArrayList<>();
                        for (OfflineController.OfflineRegionState state: states) {
                            res.add(toJSONRegionState(state));
                        }
                        callbackContext.success(new JSONArray(res));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                });
            } else if (DELETE_OFFLINE_REGION.equals(action)) {
                cordova.getThreadPool().execute(() -> {
                    final JSONObject options;
                    try {
                        options = args.getJSONObject(0);
                        @Nullable final String regionName = options.has("regionName") ? options.getString("regionName") : null;
                        @Nullable final String styleUrl = options.has("styleUrl") ? options.getString("styleUrl") : null;
                        final OfflineController offlineController = getOfflineController(mapLayout, styleUrl, activity);
                        offlineController.removeOfflineRegion(
                                regionName,
                                () -> callbackContext.success(1),
                                () -> callbackContext.success(0)
                        );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            } else {


                // need a map for all following actions
                if (mapLayout == null || !mapLayout.getMapCtrl().isReady) {
                    callbackContext.error(new JSONObject("{error: 'MAP_IS_NOT_READY'}"));
                    return true;
                }

                final MapController mapCtrl = mapLayout.getMapCtrl();
                @Nullable final String styleUrl = args.optString(0).equals("") ? null : args.optString(0);
                final OfflineController offlineController = getOfflineController(mapLayout, styleUrl, activity);
                Runnable callback = () -> mapLayout.setContainer(args, callbackContext);
                switch (action) {
                    case HIDE:
                        activity.runOnUiThread(() -> {
                            mapLayout.getMapCtrl().getMapView().setVisibility(View.GONE);
                            mapLayout.getMapCtrl().deselectFeature();
                            if (offlineController.isDownloading())
                                offlineController.pauseDownload("");
                            callbackContext.success();
                        });
                        break;
                    case DESTROY:
                        activity.runOnUiThread(() -> {
                            if (offlineController.isDownloading())
                                offlineController.pauseDownload("");
                            mapLayout = null;
                            pluginLayout.detachViewGroup();
                        });
                        break;
                    case RESIZE:
                        activity.runOnUiThread(callback);
                        break;
                    case GET_ZOOM:
                        activity.runOnUiThread(() -> {
                            callbackContext.success(String.valueOf(mapCtrl.getZoom()));
                        });
                        break;
                    case SET_ZOOM:
                        activity.runOnUiThread(() -> {
                            try {
                                if (args.isNull(0)) {
                                    throw new JSONException(action + "needs a zoom level");
                                }
                                double zoom = args.getDouble(0);
                                mapCtrl.setZoom(zoom);
                                callbackContext.success();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                callbackContext.error(e.getMessage());
                            }
                        });
                        break;
                    case ZOOM_TO:  // todo allow AnimationOptions
                        activity.runOnUiThread(() -> {
                            try {
                                if (args.isNull(0)) {
                                    throw new JSONException(action + "needs a zoom level");
                                }
                                double zoom = args.getDouble(0);
                                mapCtrl.zoomTo(zoom);
                                callbackContext.success();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                callbackContext.error("action " + e.getMessage());
                            }
                        });
                        break;
                    case GET_CENTER:
                        activity.runOnUiThread(() -> {
                            LatLng latLng = mapCtrl.getCenter();
                            JSONObject json;
                            try {
                                json = new JSONObject()
                                        .put("lat", latLng.getLatitude())
                                        .put("lng", latLng.getLongitude());
                                callbackContext.success(json);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                callbackContext.error("action " + e.getMessage());
                            }
                        });
                        break;
                    case SET_CENTER:
                        activity.runOnUiThread(() -> {
                            try {
                                if (args.isNull(0)) {
                                    throw new JSONException(action + "need a [long, lat] coordinates");
                                }
                                JSONArray center = args.getJSONArray(0);
                                mapCtrl.setCenter(center.getDouble(0), center.getDouble(1));
                                callbackContext.success();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                callbackContext.error("action " + e.getMessage());
                            }
                        });
                        break;
                    case SCROLL_MAP:
                        activity.runOnUiThread(() -> {
                            try {
                                if (args.isNull(0)) {
                                    throw new JSONException(action + "need a [x, y] screen coordinates");
                                }
                                JSONArray delta = args.getJSONArray(0);
                                mapCtrl.scrollMap(delta.getLong(0), delta.getLong(1));
                                callbackContext.success();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                callbackContext.error("action " + e.getMessage());
                            }
                        });
                        break;
                    case SET_PITCH:
                        activity.runOnUiThread(() -> {
                            try {
                                if (args.isNull(0)) {
                                    throw new JSONException(action + " need a pitch value");
                                }
                                mapCtrl.setTilt(args.getDouble(0));
                                callbackContext.success();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                callbackContext.error("action " + e.getMessage());
                            }
                        });
                        break;
                    case GET_PITCH:
                        activity.runOnUiThread(() -> {
                            callbackContext.success(String.valueOf(mapCtrl.getTilt()));
                        });
                        break;
                    case FLY_TO:
                        activity.runOnUiThread(() -> {
                            try {
                                JSONObject cameraPosition = args.isNull(0) ? null : args.getJSONObject(0);
                                if (cameraPosition == null)
                                    callbackContext.error("Need a camera position");
                                else {
                                    mapCtrl.flyTo(cameraPosition);
                                    callbackContext.success("Animation started.");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                callbackContext.error("action " + e.getMessage());
                            }
                        });
                        break;
                    case ADD_MAP_CLICK_CALLBACK:
                        activity.runOnUiThread(() -> {
                            mapLayout.mMarkerCallbackContext = callbackContext;
                            mapCtrl.addMapClickCallback(() -> {
                                if (mapLayout.mMarkerCallbackContext != null) {
                                    try {
                                        JSONObject json = new JSONObject(mapCtrl.getSelecteFeatureCollection());
                                        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, json);
                                        pluginResult.setKeepCallback(true);
                                        mapLayout.mMarkerCallbackContext.sendPluginResult(pluginResult);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        callbackContext.error("action " + e.getMessage());
                                    }
                                }
                            });
                        });
                        break;
                    case ADD_SOURCE:
                        activity.runOnUiThread(() -> {
                            try {
                                final String sourceId = args.getString(0);
                                if (sourceId.isEmpty())
                                    throw new JSONException(action + " need a source ID");
                                if (args.isNull(1))
                                    throw new JSONException(action + " no source provided");
                                if (!args.getJSONObject(1).getString("type").equals("geojson"))
                                    throw new JSONException(action + " only handle GeoJSON");


                                final JSONObject source = args.getJSONObject(1);
                                final JSONObject sourceData = source.getJSONObject("data");

                                // We can pass a empty source
                                if (sourceData != null && sourceData.length() > 0) {

                                    // Validate GeoJSON data.
                                    final GeoJSONObject geoJSON = GeoJSON.parse(source.getJSONObject("data"));

                                    // This plugin has limited GeoJSON types. It supports:
                                    // - FeatureCollection of point feature
                                    // - Single point feature
                                    final String sourceType = geoJSON.getType();
                                    final boolean isFeatureCollection = sourceType.equals("FeatureCollection");
                                    final boolean isFeature = sourceType.equals("Feature");

                                    if (!isFeature && !isFeatureCollection) {
                                        throw new JSONException("Only support Feature or FeatureCollection source type");
                                    }

                                    if (isFeatureCollection) {
                                        final JSONArray features = sourceData.getJSONArray("features");
                                        final boolean isClusterEnabled = !source.isNull("cluster") && source.getBoolean("cluster");
                                        final int clusterMaxZoom = source.isNull("clusterMaxZoom") ? 14 : source.getInt("clusterMaxZoom");
                                        final int clusterRadius = source.isNull("clusterRadius") ? 50 : source.getInt("clusterRadius");
                                        if (features.length() > 0) {
                                            final String type = features
                                                    .getJSONObject(0)
                                                    .getJSONObject("geometry")
                                                    .getString("type");
                                            if (!type.equals("Point")) {
                                                throw new JSONException("Only support Feature of type Point");
                                            }
                                        }
                                        final FeatureCollection featureCollection = FeatureCollection.fromJson(sourceData.toString());
                                        mapCtrl.addFeatureCollection(sourceId, featureCollection, isClusterEnabled, clusterMaxZoom, clusterRadius);
                                    } else {
                                        final String type = sourceData
                                                .getJSONObject("geometry")
                                                .getString("type");
                                        if (!type.equals("Point")) {
                                            throw new JSONException("Only support Feature of type Point");
                                        }
                                        final Feature feature = Feature.fromJson(sourceData.toString());
                                        mapCtrl.addFeature(sourceId, feature);
                                        callbackContext.success();
                                    }
                                } else {
                                    mapCtrl.addGeoJsonSource(sourceId);
                                    callbackContext.success();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                callbackContext.error("action " + e.getMessage());
                            }
                        });
                        break;
                    case SET_GEO_JSON:
                        activity.runOnUiThread(() -> {
                            try {
                                final String sourceId = args.getString(0);
                                if (sourceId.isEmpty())
                                    throw new JSONException(action + " need a source ID");
                                if (args.isNull(1))
                                    throw new JSONException(action + " no geojson data provided");

                                // Validate GeoJSON data.
                                final GeoJSONObject geoJSON = GeoJSON.parse(args.getJSONObject(1));

                                // This plugin has limited GeoJSON types. It supports:
                                // - FeatureCollection of point feature
                                // - Single point feature
                                final JSONObject sourceData = args.getJSONObject(1);
                                final String sourceType = geoJSON.getType();
                                final boolean isFeatureCollection = sourceType.equals("FeatureCollection");
                                final boolean isFeature = sourceType.equals("Feature");

                                if (!isFeature && !isFeatureCollection) {
                                    throw new JSONException("Only support Feature or FeatureCollection source type");
                                }

                                if (isFeatureCollection) {
                                    final JSONArray features = sourceData.getJSONArray("features");
                                    if (features.length() > 0) {
                                        final String type = features
                                                .getJSONObject(0)
                                                .getJSONObject("geometry")
                                                .getString("type");
                                        if (!type.equals("Point")) {
                                            throw new JSONException("Only support Feature of type Point");
                                        }
                                    }
                                    final FeatureCollection featureCollection = FeatureCollection.fromJson(sourceData.toString());
                                    mapCtrl.setSourceGeoJsonData(sourceId, featureCollection);
                                } else {
                                    final String type = sourceData
                                            .getJSONObject("geometry")
                                            .getString("type");
                                    if (!type.equals("Point")) {
                                        throw new JSONException("Only support Feature of type Point");
                                    }
                                    final Feature feature = Feature.fromJson(sourceData.toString());
                                    mapCtrl.setSourceGeoJsonData(sourceId, feature);
                                }
                                callbackContext.success();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                callbackContext.error("action " + e.getMessage());
                            }
                        });
                        break;
                    case REMOVE_SOURCE:
                        activity.runOnUiThread(() -> {
                            try {
                                if (args.isNull(0))
                                    throw new JSONException(action + " no id provided");

                                final JSONObject result = new JSONObject()
                                        .put("success", mapCtrl.removeSource(args.getString(0)));
                                callbackContext.success(result);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                callbackContext.error("action " + e.getMessage());
                            }
                        });
                        break;
                    case ADD_LAYER:
                        activity.runOnUiThread(() -> {
                            try {
                                final JSONObject jsonLayer = args.getJSONObject(0);

                                String layerType = jsonLayer.getString("type");
                                if (!layerType.equals("symbol") && !layerType.equals("circle"))
                                    throw new JSONException(action + " only symbol layer and circle are currently supported");

                                if (!jsonLayer.has("source"))
                                    throw new JSONException(action + " no source provided");

                                final String layerId = jsonLayer.getString("id");
                                final Integer minZoom = jsonLayer.optInt("minzoom", 0);
                                final Integer maxZoom = jsonLayer.optInt("maxzoom", 24);
                                final Expression filter = jsonLayer.has("filter")
                                        ? Expression.Converter.convert(jsonLayer.getJSONArray("filter").toString())
                                        : null;
                                final boolean isRefSource = !jsonLayer.optString("source", "").isEmpty();
                                if (isRefSource) {
                                    String beforeId = null;
                                    if (args.getString(1) != null && !args.getString(1).equals("null")) {
                                        beforeId = args.getString(1);
                                    }
                                    final String sourceId = jsonLayer.getString("source");
                                    if (layerType.equals("symbol")) {
                                        mapCtrl.addSymbolLayer(
                                                layerId,
                                                sourceId,
                                                minZoom,
                                                maxZoom,
                                                filter,
                                                beforeId
                                        );
                                    }
                                    else mapCtrl.addCircleLayer(
                                            layerId,
                                            sourceId,
                                            minZoom,
                                            maxZoom,
                                            filter,
                                            beforeId
                                    );
                                }

                                if (jsonLayer.has("layout")) {
                                    final JSONObject layout = jsonLayer.getJSONObject("layout");
                                    Iterator<String> keys = layout.keys();
                                    while (keys.hasNext()) {
                                        String name = keys.next();
                                        switch (name) {
                                            case "icon-image":
                                                final String imageId = layout.getString(name);
                                                mapCtrl.setLayoutPropertyIconImage(layerId, imageId);
                                                break;
                                            case "icon-offset":
                                                final JSONArray jsonOffsetArray = layout.getJSONArray(name);
                                                final Float[] offset = {(float) jsonOffsetArray.getDouble(0), (float) jsonOffsetArray.getDouble(1)};
                                                mapCtrl.setLayoutPropertyOffset(layerId, offset);
                                                break;
                                            case "icon-size":
                                                final String iconSize = layout.getString(name);
                                                mapCtrl.setLayoutPropertySize(layerId, iconSize);
                                                break;
                                            case "icon-allow-overlap":
                                                final boolean isOverlap = layout.getBoolean(name);
                                                mapCtrl.setLayoutPropertyIconOverlap(layerId, isOverlap);
                                                break;
                                            case "text-field":
                                                final String fieldId = layout.getString(name);
                                                mapCtrl.setLayoutPropertyTextField(layerId, fieldId);
                                                break;
                                            case "text-size":
                                                final String textSize = layout.getString(name);
                                                mapCtrl.setLayoutPropertyTextSize(layerId, textSize);
                                                break;
                                            case "text-font":
                                                final String textFont = layout.getString(name);
                                                mapCtrl.setLayoutPropertyTextFont(layerId, textFont);
                                        }
                                    }
                                }

                                if (jsonLayer.has("paint")) {
                                    final JSONObject paintField = jsonLayer.getJSONObject("paint");
                                    Iterator<String> keys = paintField.keys();
                                    while (keys.hasNext()) {
                                        String name = keys.next();
                                        switch (name) {
                                            case "circle-color":
                                                mapCtrl.setPaintPropertyCircleColor(layerId, paintField.getString(name));
                                                break;
                                            case "circle-radius":
                                                mapCtrl.setPaintPropertyCircleRadius(layerId, paintField.getString(name));
                                                break;
                                            case "text-color":
                                                mapCtrl.setPaintPropertyTextColor(layerId, paintField.getString(name));
                                                break;
                                            case "text-halo-blur":
                                                mapCtrl.setPaintPropertyTextHaloBlur(layerId, paintField.getString(name));
                                                break;
                                            case "text-halo-color":
                                                mapCtrl.setPaintPropertyTextHaloColor(layerId, paintField.getString(name));
                                                break;
                                            case "text-halo-width":
                                                mapCtrl.setPaintPropertyTextHaloWidth(layerId, paintField.getString(name));
                                                break;
                                        }
                                    }
                                }
                                callbackContext.success();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                callbackContext.error("action " + e.getMessage());
                            }
                        });
                        break;
                    case REMOVE_LAYER:
                        activity.runOnUiThread(() -> {
                            try {
                                if (args.isNull(0))
                                    throw new JSONException(action + " no id provided");

                                final JSONObject result = new JSONObject()
                                        .put("success", mapCtrl.removeLayer(args.getString(0)));
                                callbackContext.success(result);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                callbackContext.error("action " + e.getMessage());
                            }
                        });
                        break;
                    case SET_LAYOUT_PROPERTY:
                        activity.runOnUiThread(() -> {
                            try {
                                final String layerId = args.optString(0);
                                if (layerId.isEmpty())
                                    throw new JSONException(action + " no layerId provided");

                                final String name = args.optString(1);
                                if (name.isEmpty())
                                    throw new JSONException(action + " no property name provided");

                                if (args.isNull(2))
                                    throw new JSONException(action + " no value provided");

                                switch (name) {
                                    case "icon-image":
                                        final String imageId = args.getString(2);
                                        mapCtrl.setLayoutPropertyIconImage(layerId, imageId);
                                        break;
                                    case "icon-offset":
                                        final JSONArray jsonOffsetArray = args.getJSONArray(2);
                                        final Float[] offset = {(float) jsonOffsetArray.getDouble(0), (float) jsonOffsetArray.getDouble(1)};
                                        mapCtrl.setLayoutPropertyOffset(layerId, offset);
                                        break;
                                    case "icon-size":
                                        final String size = args.getString(2);
                                        mapCtrl.setLayoutPropertySize(layerId, size);
                                        break;
                                    case "icon-allow-overlap":
                                        final boolean isOverlap = args.getBoolean(2);
                                        mapCtrl.setLayoutPropertyIconOverlap(layerId, isOverlap);
                                }

                                callbackContext.success();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                callbackContext.error("action " + e.getMessage());
                            }
                        });
                        break;
                    case ADD_IMAGE:
                        activity.runOnUiThread(() -> {
                            try {
                                final String imageId = args.getString(0);
                                final JSONObject jsonImage = args.getJSONObject(1);

                                if (jsonImage.isNull("width"))
                                    throw new JSONException(action + " no width found");

                                if (jsonImage.isNull("height"))
                                    throw new JSONException(action + " no height found");

                                if (jsonImage.isNull("path"))
                                    throw new JSONException(action + " no path found. Cordova Mapbox Plugin support only local path file. Note that should be a path (\"/foo/bar\"), not an uri (\"file:///foo/bar\")");

                                mapCtrl.addImage(imageId, jsonImage);
                                callbackContext.success();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                callbackContext.error("action " + e.getMessage());
                            }
                        });
                        break;
                    case REMOVE_IMAGE:
                        activity.runOnUiThread(() -> {
                            try {
                                if (args.isNull(0))
                                    throw new JSONException(action + " no id provided");

                                mapCtrl.removeImage(args.getString(0));
                                callbackContext.success();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                callbackContext.error("action " + e.getMessage());
                            }
                        });
                        break;
                    case DESELECT:
                        activity.runOnUiThread(() -> {
                            mapCtrl.deselectFeature();
                            callbackContext.success();
                        });
                    case SET_CLICKABLE:
                        activity.runOnUiThread(() -> {
                            try {
                                pluginLayout.setClickable(args.getBoolean(0));
                            } catch (JSONException e) {
                                e.printStackTrace();
                                callbackContext.error("action " + e.getMessage());
                            }
                        });
                        break;
                    case SET_DEBUG:
                        activity.runOnUiThread(() -> {
                            try {
                                pluginLayout.setDebug(args.getInt(0) != 0);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                callbackContext.error("action " + e.getMessage());
                            }
                        });
                        break;
                    case CONVERT_COORDINATES:
                        activity.runOnUiThread(() -> {
                            try {
                                JSONObject coords = args.getJSONObject(0);
                                PointF point = mapCtrl.convertCoordinates(new LatLng(
                                        coords.getDouble("lat"),
                                        coords.getDouble("lng")
                                ));
                                callbackContext.success(new JSONObject("{\"x\": " + point.x + ", \"y\": " + point.y + "}"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                                callbackContext.error("action " + e.getMessage());
                            }
                        });
                        break;
                    case CONVERT_POINT:
                        activity.runOnUiThread(() -> {
                            try {
                                JSONObject point = args.getJSONObject(0);
                                LatLng latLng = mapCtrl.convertPoint(new PointF(
                                        (float) point.getDouble("x"),
                                        (float) point.getDouble("y")
                                ));
                                callbackContext.success(new JSONObject("{\"lat\": " + latLng.getLatitude() + ", \"lng\": " + latLng.getLongitude() + "}"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                                callbackContext.error("action " + e.getMessage());
                            }
                        });
                        break;
                    case ADD_ON_WILL_START_LOADING_MAP_LISTENER:
                        activity.runOnUiThread(() -> mapCtrl.addOnWillStartLoadingMapListener(cameraPosition -> {
                            PluginResult result = new PluginResult(PluginResult.Status.OK, cameraPosition);
                            result.setKeepCallback(true);
                            callbackContext.sendPluginResult(result);
                        }));
                        break;
                    case ADD_ON_WILL_START_RENDERING_MAP_LISTENER:
                        activity.runOnUiThread(() -> mapCtrl.addOnWillStartRenderingMapListener(cameraPosition -> {
                            PluginResult result = new PluginResult(PluginResult.Status.OK, cameraPosition);
                            result.setKeepCallback(true);
                            callbackContext.sendPluginResult(result);
                        }));
                        break;
                    case ADD_ON_CAMERA_WILL_CHANGE_LISTENER:
                        activity.runOnUiThread(() -> mapCtrl.addOnCameraWillChangeListener(cameraPosition -> {
                            PluginResult result = new PluginResult(PluginResult.Status.OK, cameraPosition);
                            result.setKeepCallback(true);
                            callbackContext.sendPluginResult(result);
                        }));
                        break;
                    case ADD_ON_CAMERA_DID_CHANGE_LISTENER:
                        activity.runOnUiThread(() -> mapCtrl.addOnCameraDidChangeListener(cameraPosition -> {
                            PluginResult result = new PluginResult(PluginResult.Status.OK, cameraPosition);
                            result.setKeepCallback(true);
                            callbackContext.sendPluginResult(result);
                        }));
                        break;
                    case ADD_ON_DID_FINISH_LOADING_STYLE_LISTENER:
                        activity.runOnUiThread(() -> mapCtrl.addOnDidFinishLoadingStyleListener(cameraPosition -> {
                            PluginResult result = new PluginResult(PluginResult.Status.OK, cameraPosition);
                            result.setKeepCallback(true);
                            callbackContext.sendPluginResult(result);
                        }));
                        break;
                    case ADD_ON_SOURCE_CHANGED_LISTENER:
                        activity.runOnUiThread(() -> mapCtrl.addOnSourceChangedListener(cameraPosition -> {
                            PluginResult result = new PluginResult(PluginResult.Status.OK, cameraPosition);
                            result.setKeepCallback(true);
                            callbackContext.sendPluginResult(result);
                        }));
                        break;
                    case ADD_ON_WILL_START_RENDERING_FRAME_LISTENER:
                        activity.runOnUiThread(() -> mapCtrl.addOnWillStartRenderingFrameListener(cameraPosition -> {
                            PluginResult result = new PluginResult(PluginResult.Status.OK, cameraPosition);
                            result.setKeepCallback(true);
                            callbackContext.sendPluginResult(result);
                        }));
                        break;
                    case ADD_ON_DID_FINISH_RENDERING_FRAME_LISTENER:
                        activity.runOnUiThread(() -> mapCtrl.addOnDidFinishRenderingFrameListener(cameraPosition -> {
                            PluginResult result = new PluginResult(PluginResult.Status.OK, cameraPosition);
                            result.setKeepCallback(true);
                            callbackContext.sendPluginResult(result);
                        }));
                        break;
                    case ADD_ON_DID_FINISH_LOADING_MAP_LISTENER:
                        activity.runOnUiThread(() -> mapCtrl.addOnDidFinishLoadingMapListener(cameraPosition -> {
                            PluginResult result = new PluginResult(PluginResult.Status.OK, cameraPosition);
                            result.setKeepCallback(true);
                            callbackContext.sendPluginResult(result);
                        }));
                        break;
                    case ADD_ON_DID_FINISH_RENDERING_MAP_LISTENER:
                        activity.runOnUiThread(() -> mapCtrl.addOnDidFinishRenderingMapListener(fully -> {
                            try {
                                JSONObject json = new JSONObject();
                                json.put("fully", fully);
                                PluginResult result = new PluginResult(PluginResult.Status.OK, json);
                                result.setKeepCallback(true);
                                callbackContext.sendPluginResult(result);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }));
                        break;
                    case SET_CONTAINER:
                        activity.runOnUiThread(callback);
                        break;
                    case GET_BOUNDS:
                        activity.runOnUiThread(() -> {
                            try {
                                final LatLngBounds latLngBounds = mapCtrl.getBounds();
                                final JSONArray sw = new JSONArray();
                                final JSONArray ne = new JSONArray();
                                try {
                                    sw.put(latLngBounds.getSouthWest().getLongitude());
                                    sw.put(latLngBounds.getSouthWest().getLatitude());
                                    ne.put(latLngBounds.getNorthEast().getLongitude());
                                    ne.put(latLngBounds.getNorthEast().getLatitude());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                final JSONObject result = new JSONObject();
                                result.put("sw", sw);
                                result.put("ne", ne);
                                callbackContext.success(result);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                callbackContext.error(e.getMessage());
                            }
                        });
                        break;
                    case GET_CAMERA_POSITION:
                        activity.runOnUiThread(() -> {
                            try {
                                callbackContext.success(mapCtrl.getJSONCameraGeoPosition());
                            } catch (JSONException e) {
                                callbackContext.error(e.getMessage());
                                e.printStackTrace();
                            }
                        });
                        break;

                    case ADD_ON_MOVE_LISTENER:
                        activity.runOnUiThread(() -> mapCtrl.addOnMoveListener(payload -> {
                            try {
                                PluginResult result = new PluginResult(PluginResult.Status.OK, payload.toJSON());
                                result.setKeepCallback(true);
                                callbackContext.sendPluginResult(result);
                            } catch (JSONException e) {
                                callbackContext.error(e.getMessage());
                                e.printStackTrace();
                            }
                        }));
                    case ADD_ON_FLING_LISTENER:
                        activity.runOnUiThread(() -> mapCtrl.addOnFlingListener(payload -> {
                            try {
                                PluginResult result = new PluginResult(PluginResult.Status.OK, payload.toJSON());
                                result.setKeepCallback(true);
                                callbackContext.sendPluginResult(result);
                            } catch (JSONException e) {
                                callbackContext.error(e.getMessage());
                                e.printStackTrace();
                            }
                        }));
                    case ADD_ON_ROTATE_LISTENER:
                        activity.runOnUiThread(() -> mapCtrl.addOnRotateListener(payload -> {
                            try {
                                PluginResult result = new PluginResult(PluginResult.Status.OK, payload.toJSON());
                                result.setKeepCallback(true);
                                callbackContext.sendPluginResult(result);
                            } catch (JSONException e) {
                                callbackContext.error(e.getMessage());
                                e.printStackTrace();
                            }
                        }));
                    case ADD_ON_SCALE_LISTENER:
                        activity.runOnUiThread(() -> mapCtrl.addOnScaleListener(payload -> {
                            try {
                                PluginResult result = new PluginResult(PluginResult.Status.OK, payload.toJSON());
                                result.setKeepCallback(true);
                                callbackContext.sendPluginResult(result);
                            } catch (JSONException e) {
                                callbackContext.error(e.getMessage());
                                e.printStackTrace();
                            }
                        }));
                    default:
                        return false;
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
            callbackContext.error(t.getMessage());
        }
        return true;
    }

    static private JSONObject toJSONRegionState(OfflineController.OfflineRegionState state) {
        JSONObject progressMsg = new JSONObject();
        try {
            progressMsg.put("regionName", state.regionName);
            progressMsg.put("isComplete", state.isComplete);
            progressMsg.put("requiredResourceCount", state.requiredResourceCount);
            progressMsg.put("downloadState", state.downloadState == 1 ? "STATE_ACTIVE" : "STATE_INACTIVE");
            progressMsg.put("completeTileSize", state.completeTileSize);
            progressMsg.put("completeTileCount", state.completeTileCount);
            progressMsg.put("completeResourceSize", state.completeResourceSize);
            progressMsg.put("completedResourceCount", state.completedResourceCount);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return progressMsg;
    }

    static private OfflineController getOfflineController(@Nullable MapLayout mapLayout, @Nullable String styleUrl, Activity activity) throws Exception {
        if (mapLayout == null) {
            if (styleUrl == null || styleUrl.isEmpty()) {
                throw new Exception("When the Map is not displayed, you need to provide a style url");
            }
            return OfflineControllerPool.get(styleUrl) != null ? OfflineControllerPool.get(styleUrl) : OfflineControllerPool.create(activity, styleUrl);
        } else {
            return mapLayout.getMapCtrl().getOfflineController();
        }
    }

    public void onPause(boolean multitasking) {
        if (mapLayout != null) {
            mapLayout.getMapCtrl().getMapView().onStop();
        }
    }

    public void onResume(boolean multitasking) {
        if (mapLayout != null) {
            mapLayout.onResume();
        }
    }

    public void onDestroy() {
        if (mapLayout != null) {
            mapLayout.getMapCtrl().getMapView().onDestroy();
        }
        OfflineControllerPool.onDestroy();
    }
}
