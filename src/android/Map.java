package com.dagatsoin.plugins.mapbox;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;

import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vikti on 24/06/2016.
 *
 * This file handle a single Map.
 * He has a visual responsibility only: hide, show, resize...
 * All map actions are handled by the controller and called from CDVMapbox.java to ensure
 * a decoupling and easily switch to GoogleMap or whatever in the futur.
 */
public class Map {

    private int mId;
    private CDVMapbox mPlugRef;
    private FrameLayout mLayersGroup;
    private JSONObject mMapDivLayoutJSON;
    private MapController mMapCtrl;

    private static CordovaWebView mCdvWebView;
    private static float mRetinaFactor;

    CallbackContext mMarkerCallbackContext;

    MapController getMapCtrl(){
        return mMapCtrl;
    }
    ViewGroup getViewGroup(){
        return mLayersGroup;
    }
    public int getId(){return mId;}

    /**
     * Create a map without any layout set
     * @param args original cordova arguments of the the show command
     * @param plugRef a reference to CDVMapbox instance
     * @param activity the main parent activity
     * @param callbackContext command callback context
     */
    public Map(int id, final CordovaArgs args, CDVMapbox plugRef, Activity activity, CallbackContext callbackContext) {
        mId = id;
        mPlugRef = plugRef;
        mCdvWebView = mPlugRef.webView;
        Context _context = mCdvWebView.getView().getContext();
        mRetinaFactor = Resources.getSystem().getDisplayMetrics().density;

        final JSONObject options;
        final JSONArray HTMLs;
        @Nullable final String selectedFeatureLayerId;
        @Nullable final String selectedFeatureSourceId;
        @Nullable final String selectableFeaturePropType;
        try {
            options = args.getJSONObject(1);
            HTMLs = options.isNull("HTMLs") ? new JSONArray() : options.getJSONArray("HTMLs");
            if(options.isNull("rect")) throw new JSONException("Map constructor need a rect in the JSONObject options argument.");
            selectedFeatureLayerId  = options.has("selectedFeatureLayerId") ? options.getString("selectedFeatureLayerId") : null;
            selectedFeatureSourceId  = options.has("selectedFeatureSourceId") ? options.getString("selectedFeatureSourceId") : null;
            selectableFeaturePropType  = options.has("selectableFeaturePropType") ? options.getString("selectableFeaturePropType") : null;
        } catch (JSONException e) {
            callbackContext.error(e.getMessage());
            e.printStackTrace();
            return;
        }

        // Draw the blocking touch zones to allow click on overlay DOM elements
        updateMapOverlay(HTMLs);

        // Create a controller (which instantiate the MGLMapbox view)
        // todo find an optimized way to pass the scroll view ?
        mMapCtrl = new MapController(
                options,
                activity,
                selectedFeatureLayerId,
                selectedFeatureSourceId,
                selectableFeaturePropType,
                mPlugRef.pluginLayout.getScrollView()
        );

        // The view container. Contains maps and addons views.
        mLayersGroup = new FrameLayout(_context);
        mLayersGroup.addView(mMapCtrl.getMapView());
    }


    //change to updateLauyoutWhenScroll
    private void updateMapOverlay(JSONArray HTMLs){

        JSONObject elemInfo, elemSize;
        String elemId;
        float divW, divH, divLeft, divTop;

        for (int i = 0; i < HTMLs.length(); i++) {
            try {
                elemInfo = HTMLs.getJSONObject(i);
                elemId = elemInfo.getString("id");
                elemSize = elemInfo.getJSONObject("size");

                divW = applyRetinaFactor(elemSize.getLong("width"));
                divH = applyRetinaFactor(elemSize.getLong("height"));
                divLeft = applyRetinaFactor(elemSize.getLong("left"));
                divTop = applyRetinaFactor(elemSize.getLong("top"));
                mPlugRef.pluginLayout.setHTMLElement(elemId, divLeft, divTop, divLeft + divW, divTop + divH);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void onScroll(int x, int y){
        mPlugRef.pluginLayout.setMapDrawingRect(
                mId,
                toRect(mMapDivLayoutJSON, x, y)
        );
    }

    private void updateMapViewLayout() {
        if (mPlugRef.pluginLayout == null) {
            return;
        }

        mPlugRef.pluginLayout.setMapDrawingRect(
                mId,
                toRect(mMapDivLayoutJSON, mCdvWebView.getView().getScrollX(), mCdvWebView.getView().getScrollY())
        );

        mPlugRef.pluginLayout.updateViewPosition();

        mLayersGroup.requestLayout(); //todo watch this line if nothing is resized

    }

    /**
     * Resize the map to fit the dimensions of a div.
     * This function takes also care of update the overlay DOM elements touch boxes.
     * @param args Cordova Args. Contain a div JSONObject
     * @param callbackContext
     * @throws JSONException
     */
    public void setContainer(CordovaArgs args, CallbackContext callbackContext){

        try {
            JSONObject options = args.getJSONObject(1);

            if(options.isNull("rect")){
                callbackContext.error("Map.setContainer(... Need a rect");
            }

            // update the map size
            mMapDivLayoutJSON = options.getJSONObject("rect");

            // update the map overlay DOM elements touch boxes
            JSONArray HTMLs = options.isNull("HTMLs") ? new JSONArray() : options.getJSONArray("HTMLs");
            mPlugRef.pluginLayout.clearHTMLElement();
            updateMapOverlay(HTMLs);

            // Finally, update the map view layout to take account of the new map dimension.
            updateMapViewLayout();

            this.sendNoResult(callbackContext);
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    private float applyRetinaFactor(long d) {
        return d * mRetinaFactor;
    }


    private RectF toRect(JSONObject rect, float... scroll) {
        float scrollX = scroll.length > 0 ? scroll[0] : 0;
        float scrollY = scroll.length > 1 ? scroll[1] : 0;

        try{
            float left = applyRetinaFactor(rect.getLong("left"));
            float width = applyRetinaFactor(rect.getLong("width"));
            float top = applyRetinaFactor(rect.getLong("top"));
            float height = applyRetinaFactor(rect.getLong("height"));

            return new RectF(
                left - scrollX,
                top - scrollY,
                left + width - scrollX,
                top + height - scrollY);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    //todo take in account all type of layer
    private FrameLayout.LayoutParams _toLayoutParams(RectF rect) {
        int screenW = Math.round(mCdvWebView.getView().getWidth());
        int screenH = Math.round(mCdvWebView.getView().getHeight());
        int left = Math.round(rect.left);
        int right = Math.round(rect.right);
        int top = Math.round(rect.top);
        int bottom = Math.round(rect.bottom);
        int width = Math.round(screenW - left - (screenW - right));
        int height = Math.round(screenH - top - (screenH - bottom));

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width, height);

        params.setMargins(left, top, width - right, height - bottom);

        return params;
    }

    private void sendNoResult(CallbackContext callbackContext) {
        PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
        pluginResult.setKeepCallback(true);
        callbackContext.sendPluginResult(pluginResult);
    }
}