package com.dagatsoin.plugins.mapbox;

import android.app.Activity;
import android.content.res.Resources;
import android.support.annotation.Nullable;
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
    private HashMap<String, OfflineRegion> mDownloadingOfflineRegionList = new HashMap<>();
    private HashMap<String, OfflineRegionState> mOfflineRegionStates = new HashMap<>();
    private final static String JSON_CHARSET = "UTF-8";
    private final static String JSON_FIELD_REGION_NAME = "FIELD_REGION_NAME";
    private boolean mIsReady = false;

    @Nullable
    OfflineRegionState getOfflineRegionDownloadState(String regionName) {
        return mOfflineRegionStates.get(regionName);
    }

    boolean getIsReady() {
        return mIsReady;
    }

    boolean isDownloading() {
        return !mDownloadingOfflineRegionList.isEmpty();
    }

    ArrayList<OfflineRegionState> getOfflineRegionStates() {
        return new ArrayList<>(mOfflineRegionStates.values());
    }

    OfflineController(Activity activity, String styleUrl) {
        mOfflineManager = OfflineManager.getInstance(activity);
        mStyleUrl = styleUrl;
        setOfflineRegionStatesFromDB();
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
            final Runnable onProgress
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
                    final OfflineRegionState offlineRegionState = new OfflineRegionState(regionName,null);
                    mOfflineRegionStates.put(regionName, offlineRegionState);
                    offlineRegion.setDownloadState(OfflineRegion.STATE_ACTIVE);
                    onProgress.run();
                    offlineRegion.setObserver(new OfflineRegion.OfflineRegionObserver() {
                        @Override
                        public void onStatusChanged(OfflineRegionStatus status) {
                            // Compute a percentage
                            offlineRegionState.hydrate(regionName, status);

                            if (status.isRequiredResourceCountPrecise()) {
                                onProgress.run();
                            }

                            if (status.isComplete()) {
                                // Download complete
                                mDownloadingOfflineRegionList.remove(regionName);
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
                            mDownloadingOfflineRegionList.remove(regionName);
                            mOfflineRegionStates.remove(regionName);
                        }

                        @Override
                        public void mapboxTileCountLimitExceeded(long limit) {
                            if (minZoom > maxZoom) {
                                Log.w(TAG, "Mapbox tile count limit exceeded: " + limit + ". Trying with lower max zoom.");
                                downloadRegion(regionName, bounds, minZoom, maxZoom - 1, onProgress);
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

    void pauseDownload(@Nullable String id) {
        if (id == null) {
            for (OfflineRegion offlineRegion : mDownloadingOfflineRegionList.values()) {
                // Catch an exception where the region is already deleted
                try {
                    offlineRegion.setDownloadState(OfflineRegion.STATE_INACTIVE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            OfflineRegion maybeRegion = mDownloadingOfflineRegionList.get(id);
            if (maybeRegion != null) {
                try {
                    maybeRegion.setDownloadState(OfflineRegion.STATE_INACTIVE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void resumeDownload(@Nullable String id) {
        if (id == null) {
            for (OfflineRegion offlineRegion: mDownloadingOfflineRegionList.values()) {
                // Catch an exception where the region is already deleted
                try {
                    offlineRegion.setDownloadState(OfflineRegion.STATE_ACTIVE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            OfflineRegion maybeRegion = mDownloadingOfflineRegionList.get(id);
            if (maybeRegion != null) {
                try {
                   maybeRegion.setDownloadState(OfflineRegion.STATE_ACTIVE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void setOfflineRegionStatesFromDB() {
        mOfflineManager.listOfflineRegions(new OfflineManager.ListOfflineRegionsCallback() {
            @Override
            public void onList(final OfflineRegion[] offlineRegions) {
                int nb = offlineRegions.length;
                // Clean the last ref array and add all of the region names to the list.
                mOfflineRegionStates.clear();
                for (OfflineRegion offlineRegion : offlineRegions) {
                    offlineRegion.getStatus(new OfflineRegion.OfflineRegionStatusCallback(){

                        @Override
                        public void onStatus(OfflineRegionStatus status) {
                            final String regionName = getRegionName(offlineRegion);
                            OfflineRegionState offlineRegionState = new OfflineRegionState(regionName, status);
                            mOfflineRegionStates.put(getRegionName(offlineRegion), offlineRegionState);
                            if (mOfflineRegionStates.size() == nb) {
                                mIsReady = true;
                            }
                        }

                        @Override
                        public void onError(String error) {
                            mIsReady = false;
                            Log.e(TAG, "Error while reading Mapbox local DB");
                        }
                    });
                }
            }

            @Override
            public void onError(String error) {
                mIsReady = false;
                Log.e(TAG, "Error while reading Mapbox local DB");
            }
        });
    }

    /**
     * Delete an offline region based on the regionName stored in the meta data
     * @param regionName the region name
     * @param onDelete on success callback
     * @param onError on error callback
     */
    void removeOfflineRegion(@Nullable final String regionName, final Runnable onDelete, final Runnable onError) {
        mOfflineManager.listOfflineRegions(new OfflineManager.ListOfflineRegionsCallback() {
            private OfflineRegion.OfflineRegionDeleteCallback callback = new OfflineRegion.OfflineRegionDeleteCallback() {
                @Override
                public void onDelete() {
                    mOfflineRegionStates.remove(regionName);
                    onDelete.run();
                }

                @Override
                public void onError(String error) {
                    Log.e(TAG, "Error while deleteing item in Mapbox local DB");
                    mOfflineRegionStates.remove(regionName);
                    onError.run();
                }
            };

            @Override
            public void onList(final OfflineRegion[] offlineRegions) {
                if (regionName != null) {
                    OfflineRegion selectedRegion = null;
                    for (OfflineRegion region : offlineRegions) {
                        if (getRegionName(region).equals(regionName)) {
                            selectedRegion = region;
                            break;
                        }
                    }

                    if (selectedRegion == null) {
                        onError.run();
                    } else {
                        selectedRegion.delete(callback);
                    }
                } else {
                    for (OfflineRegion region : offlineRegions) {
                        region.delete(callback);
                    }
                }
            }

            @Override
            public void onError(String error) {
                Log.e(TAG, "Error while deleteing item in Mapbox local DB");
                mOfflineRegionStates.remove(regionName);
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

    class OfflineRegionState {
        String regionName;
        boolean isComplete = false;
        long requiredResourceCount = 0;
        int downloadState = OfflineRegion.STATE_ACTIVE;
        long completeTileSize = 0;
        long completeTileCount = 0;
        long completeResourceSize = 0;
        long completedResourceCount = 0;

        OfflineRegionState(String _regionName, @Nullable OfflineRegionStatus status) {
            regionName = _regionName;
            if (status != null) {
                hydrate(_regionName, status);
            }
        }

        void hydrate(String _regionName, OfflineRegionStatus status) {
            regionName = _regionName;
            completedResourceCount = status.getCompletedResourceCount();
            completeResourceSize = status.getCompletedResourceSize();
            completeTileCount = status.getCompletedTileCount();
            completeTileSize = status.getCompletedTileSize();
            downloadState = status.getDownloadState();
            requiredResourceCount = status.getRequiredResourceCount();
            isComplete = status.isComplete();
        }
    }
}