package com.dagatsoin.plugins.mapbox;

import android.app.Activity;

import java.util.HashMap;

public class OfflineControllerPool {
    private static HashMap<String, OfflineController> mList = new HashMap<>();

    static OfflineController create(Activity activity, String styleUrl) {
        OfflineController controller = new OfflineController(activity, styleUrl);
        mList.put(styleUrl, controller);
        return controller;
    }

    static OfflineController get(String styleUrl) {
        return mList.get(styleUrl);
    }

    static void remove(String styleUrl) {
        mList.remove(styleUrl);
    }

    static void onDestroy() {
        for (HashMap.Entry<String, OfflineController> entry : mList.entrySet()) {
            final OfflineController controller = entry.getValue();
            controller.pauseDownload(null);
        }
    }
}
