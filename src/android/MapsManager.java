package com.dagatsoin.plugins.mapbox;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.mapbox.mapboxsdk.maps.MapView;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;

/**
 * Created by vikti on 24/06/2016.
 */
class MapsManager {
    private static SparseArray<Map> mMaps = new SparseArray<>();
    private static CDVMapbox mPlugRef;
    private static Activity mActivity;

    
    static void init(final CDVMapbox plugin, Activity activity) {
        mPlugRef = plugin;
        mActivity = activity;
    }

    static Map createMap(CordovaArgs args, int id, CallbackContext callbackContext){
        Map map = new Map(id, args, mPlugRef, mActivity, callbackContext);
        mMaps.put(id, map);
        return map;
    }

    static Map getMap(int id){
        return mMaps.get(id);
    }

    static int getCount(){
        return mMaps.size();
    }

    static void removeMap(int mapId){
        mMaps.delete(mapId);
    }

    static void onPause() {
        for( int i = 0; i < mMaps.size(); i++){
            mMaps.get(i).getMapCtrl().getMapView().onStop();
        }
    }

    static void onResume() {
        for( int i = 0; i < mMaps.size(); i++){
            Map map = mMaps.get(i);
            if (map != null) {
                ViewGroup viewGroup = map.getViewGroup();
                mMaps.get(i).getMapCtrl().getMapView().onStart();
                mPlugRef.mapsGroup.removeView(viewGroup);
                if (viewGroup.getParent() != null)
                    ((ViewGroup) viewGroup.getParent()).removeView(viewGroup);
                mPlugRef.mapsGroup.addView(viewGroup);
            }
        }
    }

    static void onDestroy() {
        for( int i = 0; i < mMaps.size(); i++){
            mMaps.get(i).getMapCtrl().getMapView().onDestroy();
            mMaps.clear();
        }
    }
}