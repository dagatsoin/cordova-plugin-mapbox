package com.dagatsoin.plugins.mapbox;

import android.app.Activity;
import android.content.res.Resources;
import android.util.Log;

import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.offline.OfflineManager;
import com.mapbox.mapboxsdk.offline.OfflineRegion;
import com.mapbox.mapboxsdk.offline.OfflineRegionError;
import com.mapbox.mapboxsdk.offline.OfflineRegionStatus;
import com.mapbox.mapboxsdk.offline.OfflineTilePyramidRegionDefinition;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

class OfflineController {
    private final static String TAG = "MAP_CONTROLLER";
    private String mStyleUrl;
    private OfflineManager mOfflineManager;
    private HashMap<String, OfflineRegion> mDownloadingOfflineRegionList = new HashMap();
    private int mDownloadingProgress;
    private ArrayList<String> mOfflineRegionsNames = new ArrayList<>();
    private final static String JSON_CHARSET = "UTF-8";
    private final static String JSON_FIELD_REGION_NAME = "FIELD_REGION_NAME";

    int getDownloadingProgress() {
        return mDownloadingProgress;
    }

    boolean isDownloading() {
        return !mDownloadingOfflineRegionList.isEmpty();
    }

    ArrayList<String> getOfflineRegionsNames() {
        return mOfflineRegionsNames;
    }

    OfflineController(Activity activity, String styleUrl) {
        mOfflineManager = OfflineManager.getInstance(activity);
        mStyleUrl = styleUrl;
    }

    private float retinaFactor = Resources.getSystem().getDisplayMetrics().density;


    /**
     * Download a given region for offline use.
     */
    void downloadRegion(
            final String regionName,
            final LatLngBounds bounds,
            final int minZoom,
            final int maxZoom,
            final Runnable onStart,
            final Runnable onProgress,
            final Runnable onFinish
    ) {
        // Define the offline region
        OfflineTilePyramidRegionDefinition definition = new OfflineTilePyramidRegionDefinition(
                mStyleUrl,
                bounds,
                minZoom,
                maxZoom,
                retinaFactor
        );

        // Build a JSONObject using the user-defined offline region title,
        // convert it into string, and use it to create a metadata variable.
        // The metadata variable will later be passed to createOfflineRegion()
        try {
            JSONObject jsonObject = new JSONObject();
            // regionName is questId
            jsonObject.put(JSON_FIELD_REGION_NAME, regionName);
            String json = jsonObject.toString();
            final byte[] metadata = json.getBytes(JSON_CHARSET);
            // Create the offline region and launch the download
            mOfflineManager.createOfflineRegion(definition, metadata, new OfflineManager.CreateOfflineRegionCallback() {
                @Override
                public void onCreate(OfflineRegion offlineRegion) {
                    Log.d(TAG, "Offline region created: " + regionName);
                    mDownloadingOfflineRegionList.put(regionName, offlineRegion);
                    // Set up an observer to handle download progress and
                    // notify the user when the region is finished downloading
                    // Start the progression
                    onStart.run();
                    offlineRegion.setDownloadState(OfflineRegion.STATE_ACTIVE);
                    offlineRegion.setObserver(new OfflineRegion.OfflineRegionObserver() {
                        @Override
                        public void onStatusChanged(OfflineRegionStatus status) {
                            // Compute a percentage
                            double percentage = status.getRequiredResourceCount() >= 0 ?
                                    (100.0 * status.getCompletedResourceCount() / status.getRequiredResourceCount()) :
                                    0.0;

                            if (status.isComplete()) {
                                // Download complete
                                onFinish.run();
                                mDownloadingOfflineRegionList.remove(regionName);
                            } else if (status.isRequiredResourceCountPrecise()) {
                                // Switch to determinate state
                                mDownloadingProgress = ((int) Math.round(percentage));
                                onProgress.run();
                            }

                            // Log what is being currently downloaded
                            Log.d(TAG, String.format("%s/%s resources; %s bytes downloaded.",
                                    String.valueOf(status.getCompletedResourceCount()),
                                    String.valueOf(status.getRequiredResourceCount()),
                                    String.valueOf(status.getCompletedResourceSize())));
                        }

                        @Override
                        public void onError(OfflineRegionError error) {
                            Log.e(TAG, "Mapbox download map error: reason: " + error.getReason());
                            Log.e(TAG, "Mapbox download map error: message: " + error.getMessage());
                        }

                        @Override
                        public void mapboxTileCountLimitExceeded(long limit) {
                            if (minZoom > maxZoom) {
                                Log.w(TAG, "Mapbox tile count limit exceeded: " + limit + ". Trying with lower max zoom.");
                                downloadRegion(regionName, bounds, minZoom, maxZoom - 1, onStart, onProgress, onFinish);
                            }
                            Log.e(TAG, "Mapbox tile count limit exceeded: " + limit);
                        }
                    });
                }

                @Override
                public void onError(String error) {
                    Log.e(TAG, "Mapbox download map error: " + error);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Mapbox failed to encode metadata for offline map: " + e.getMessage());
        }
    }

    void pauseDownload(String id) {
        if (id.equals("")) {
            for (OfflineRegion offlineRegion : mDownloadingOfflineRegionList.values()) {
                offlineRegion.setDownloadState(OfflineRegion.STATE_INACTIVE);
            }
        } else {
            OfflineRegion maybeRegion = mDownloadingOfflineRegionList.get(id);
            if (maybeRegion != null) {
                maybeRegion.setDownloadState(OfflineRegion.STATE_INACTIVE);
            }
        }
    }

    void resumeDownload(String id) {
        if (id.equals("")) {
            for (OfflineRegion offlineRegion: mDownloadingOfflineRegionList.values()) {
                offlineRegion.setDownloadState(OfflineRegion.STATE_ACTIVE);
            }
        } else {
            OfflineRegion maybeRegion = mDownloadingOfflineRegionList.get(id);
            if (maybeRegion != null) {
                maybeRegion.setDownloadState(OfflineRegion.STATE_ACTIVE);
            }
        }

    }

    void getOfflineRegions(final Runnable callback) {
        mOfflineManager.listOfflineRegions(new OfflineManager.ListOfflineRegionsCallback() {
            @Override
            public void onList(final OfflineRegion[] offlineRegions) {
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

    /**
     * Delete an offline region based on the regionName stored in the meta data
     * @param regionName the region name
     * @param onDelete on success callback
     * @param onError on error callback
     */
    void removeOfflineRegion(final String regionName, final Runnable onDelete, final Runnable onError) {
        mOfflineManager.listOfflineRegions(new OfflineManager.ListOfflineRegionsCallback() {
            @Override
            public void onList(final OfflineRegion[] offlineRegions) {

                OfflineRegion selectedRegion = null;
                for (OfflineRegion region : offlineRegions) {
                    if(getRegionName(region).equals(regionName)) {
                        selectedRegion = region;
                        break;
                    }
                }

                if (selectedRegion == null) {
                    onError.run();
                } else {
                    selectedRegion.delete(new OfflineRegion.OfflineRegionDeleteCallback() {
                        @Override
                        public void onDelete() {
                            onDelete.run();
                        }

                        @Override
                        public void onError(String error) {
                            onError.run();
                        }
                    });
                }
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
}