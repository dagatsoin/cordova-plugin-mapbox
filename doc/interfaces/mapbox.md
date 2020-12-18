**[cordova-plugin-mapbox](../README.md)**

> [Globals](../README.md) / Mapbox

# Interface: Mapbox

## Hierarchy

* **Mapbox**

## Index

### Methods

* [addImage](mapbox.md#addimage)
* [addLayer](mapbox.md#addlayer)
* [addMapClickCallback](mapbox.md#addmapclickcallback)
* [addOnCameraDidChangeListener](mapbox.md#addoncameradidchangelistener)
* [addOnCameraWillChangeListener](mapbox.md#addoncamerawillchangelistener)
* [addOnDidFinishLoadingMapListener](mapbox.md#addondidfinishloadingmaplistener)
* [addOnDidFinishLoadingStyleListener](mapbox.md#addondidfinishloadingstylelistener)
* [addOnDidFinishRenderingFrameListener](mapbox.md#addondidfinishrenderingframelistener)
* [addOnDidFinishRenderingMapListener](mapbox.md#addondidfinishrenderingmaplistener)
* [addOnFlingListener](mapbox.md#addonflinglistener)
* [addOnMoveListener](mapbox.md#addonmovelistener)
* [addOnRotateListener](mapbox.md#addonrotatelistener)
* [addOnScaleListener](mapbox.md#addonscalelistener)
* [addOnSourceChangedListener](mapbox.md#addonsourcechangedlistener)
* [addOnWillStartLoadingMapListener](mapbox.md#addonwillstartloadingmaplistener)
* [addOnWillStartRenderingFrameListener](mapbox.md#addonwillstartrenderingframelistener)
* [addOnWillStartRenderingMapListener](mapbox.md#addonwillstartrenderingmaplistener)
* [addSource](mapbox.md#addsource)
* [convertCoordinates](mapbox.md#convertcoordinates)
* [convertPoint](mapbox.md#convertpoint)
* [deleteOfflineRegion](mapbox.md#deleteofflineregion)
* [deselect](mapbox.md#deselect)
* [destroy](mapbox.md#destroy)
* [downloadRegion](mapbox.md#downloadregion)
* [flyTo](mapbox.md#flyto)
* [getBounds](mapbox.md#getbounds)
* [getCameraPosition](mapbox.md#getcameraposition)
* [getCenter](mapbox.md#getcenter)
* [getOfflineRegionList](mapbox.md#getofflineregionlist)
* [getPitch](mapbox.md#getpitch)
* [getZoom](mapbox.md#getzoom)
* [hide](mapbox.md#hide)
* [pauseDownload](mapbox.md#pausedownload)
* [removeImage](mapbox.md#removeimage)
* [removeLayer](mapbox.md#removelayer)
* [removeSource](mapbox.md#removesource)
* [resumeDownload](mapbox.md#resumedownload)
* [scrollMap](mapbox.md#scrollmap)
* [setCenter](mapbox.md#setcenter)
* [setClickable](mapbox.md#setclickable)
* [setContainer](mapbox.md#setcontainer)
* [setDebug](mapbox.md#setdebug)
* [setGeoJson](mapbox.md#setgeojson)
* [setLayoutProperty](mapbox.md#setlayoutproperty)
* [setPitch](mapbox.md#setpitch)
* [setZoom](mapbox.md#setzoom)
* [show](mapbox.md#show)
* [zoomTo](mapbox.md#zoomto)

## Methods

### addImage

▸ **addImage**(`imageId`: string, `image`: [ImageProperties](../README.md#imageproperties), `successCallback?`: () => void, `errorCallback?`: (e: string) => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:356](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L356)*

Add an image to the map style. For instance, a marker image.
You must add the image before referencing it in a resource.

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`imageId` | string |  |
`image` | [ImageProperties](../README.md#imageproperties) |  |
`successCallback?` | () => void | called on success |
`errorCallback?` | (e: string) => void | called in case of error |
`id?` | number | the unique id of the map when multi maps is needed. Default to 0.  |

**Returns:** void

___

### addLayer

▸ **addLayer**(`layer`: [Layer](../README.md#layer), `beforeId?`: number, `successCallback?`: () => void, `errorCallback?`: (e: string) => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:384](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L384)*

Add a layer to the map style

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`layer` | [Layer](../README.md#layer) |  |
`beforeId?` | number | the layer will be added below |
`successCallback?` | () => void | called on success |
`errorCallback?` | (e: string) => void | called in case of error |
`id?` | number | the unique id of the map when multi maps is needed. Default to 0.  |

**Returns:** void

___

### addMapClickCallback

▸ **addMapClickCallback**(`callback?`: (featureCollection: FeatureCollection<GeoJSON.GeometryObject\>) => void, `errorCallback?`: (e: string) => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:331](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L331)*

Add a map click callback to the native map.

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`callback?` | (featureCollection: FeatureCollection<GeoJSON.GeometryObject\>) => void | the click callback taking a collection of JSON feature geometry objects. |
`errorCallback?` | (e: string) => void | called in case of error |
`id?` | number | the unique id of the map when multi maps is needed. Default to 0.  |

**Returns:** void

___

### addOnCameraDidChangeListener

▸ **addOnCameraDidChangeListener**(`listener`: () => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:681](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L681)*

This event is triggered whenever the displayed map region finished changing without an animation.

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`listener` | () => void |  |
`id?` | number |   |

**Returns:** void

___

### addOnCameraWillChangeListener

▸ **addOnCameraWillChangeListener**(`listener`: () => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:675](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L675)*

This event is triggered whenever the displayed map region is about to change without animation.

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`listener` | () => void |  |
`id?` | number |   |

**Returns:** void

___

### addOnDidFinishLoadingMapListener

▸ **addOnDidFinishLoadingMapListener**(`listener`: () => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:711](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L711)*

This is triggered when the map has successfully loaded a new map style.

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`listener` | () => void |  |
`id?` | number |   |

**Returns:** void

___

### addOnDidFinishLoadingStyleListener

▸ **addOnDidFinishLoadingStyleListener**(`listener`: () => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:687](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L687)*

Triggered when a style has finished loading.

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`listener` | () => void |  |
`id?` | number |   |

**Returns:** void

___

### addOnDidFinishRenderingFrameListener

▸ **addOnDidFinishRenderingFrameListener**(`listener`: () => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:705](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L705)*

This event is triggered when the map finished rendering a frame.

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`listener` | () => void |  |
`id?` | number |   |

**Returns:** void

___

### addOnDidFinishRenderingMapListener

▸ **addOnDidFinishRenderingMapListener**(`listener`: (fullyRendered: boolean) => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:717](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L717)*

This event is triggered when the map is fully rendered.

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`listener` | (fullyRendered: boolean) => void |  |
`id?` | number |   |

**Returns:** void

___

### addOnFlingListener

▸ **addOnFlingListener**(`listener`: [MapEventListener](../README.md#mapeventlistener), `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:638](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L638)*

Adds a callback that's invoked when the map is flinged.
The user performs a bold move gesture and the maps move with more inertia.

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`listener` | [MapEventListener](../README.md#mapeventlistener) |  |
`id?` | number |   |

**Returns:** void

___

### addOnMoveListener

▸ **addOnMoveListener**(`listener`: [MapEventListener](../README.md#mapeventlistener), `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:645](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L645)*

Adds a callback that's invoked when the map is moved.
Does not fire when the map is flinged

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`listener` | [MapEventListener](../README.md#mapeventlistener) |  |
`id?` | number |   |

**Returns:** void

___

### addOnRotateListener

▸ **addOnRotateListener**(`listener`: [MapEventListener](../README.md#mapeventlistener), `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:651](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L651)*

Adds a callback that's invoked when the map is rotated.

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`listener` | [MapEventListener](../README.md#mapeventlistener) |  |
`id?` | number |   |

**Returns:** void

___

### addOnScaleListener

▸ **addOnScaleListener**(`listener`: [MapEventListener](../README.md#mapeventlistener), `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:657](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L657)*

Adds a callback that's invoked when the map is scaled.

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`listener` | [MapEventListener](../README.md#mapeventlistener) |  |
`id?` | number |   |

**Returns:** void

___

### addOnSourceChangedListener

▸ **addOnSourceChangedListener**(`listener`: () => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:693](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L693)*

Triggered when a source changes.

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`listener` | () => void |  |
`id?` | number |   |

**Returns:** void

___

### addOnWillStartLoadingMapListener

▸ **addOnWillStartLoadingMapListener**(`listener`: () => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:663](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L663)*

This event is triggered when the map is about to start loading a new map style.

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`listener` | () => void |  |
`id?` | number |   |

**Returns:** void

___

### addOnWillStartRenderingFrameListener

▸ **addOnWillStartRenderingFrameListener**(`listener`: () => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:699](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L699)*

This event is triggered when the map will start rendering a frame.

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`listener` | () => void |  |
`id?` | number |   |

**Returns:** void

___

### addOnWillStartRenderingMapListener

▸ **addOnWillStartRenderingMapListener**(`listener`: () => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:669](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L669)*

This event is triggered when the map will start rendering the map.

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`listener` | () => void |  |
`id?` | number |   |

**Returns:** void

___

### addSource

▸ **addSource**(`sourceId`: string, `source`: [Source](../README.md#source)<any, any\>, `successCallback?`: () => void, `errorCallback?`: (e: string) => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:429](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L429)*

Add a source to the map style

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`sourceId` | string |  |
`source` | [Source](../README.md#source)<any, any\> |  |
`successCallback?` | () => void | called on success |
`errorCallback?` | (e: string) => void | called in case of error |
`id?` | number | the unique id of the map when multi maps is needed. Default to 0.  |

**Returns:** void

___

### convertCoordinates

▸ **convertCoordinates**(`coords`: { lat: number ; lng: number  }, `resultCallback?`: (\_point: [ScreenCoords](../README.md#screencoords)) => void, `errorCallback?`: (\_e: string) => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:610](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L610)*

Convert [Coords](../README.md#coords) to [ScreenCoords](../README.md#screencoords)

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`coords` | { lat: number ; lng: number  } |  |
`resultCallback?` | (\_point: [ScreenCoords](../README.md#screencoords)) => void | takes the [ScreenCoords](../README.md#screencoords) as argument |
`errorCallback?` | (\_e: string) => void | called in case of error |
`id?` | number | the unique id of the map when multi maps is needed. Default to 0.  |

**Returns:** void

___

### convertPoint

▸ **convertPoint**(`point`: [ScreenCoords](../README.md#screencoords), `successCallback?`: (\_coords: { lat: number ; lng: number  }) => void, `errorCallback?`: (\_e: string) => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:626](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L626)*

Convert [ScreenCoords](../README.md#screencoords) to [Coords](../README.md#coords)

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`point` | [ScreenCoords](../README.md#screencoords) |  |
`successCallback?` | (\_coords: { lat: number ; lng: number  }) => void | takes the [Coords](../README.md#coords) as argument |
`errorCallback?` | (\_e: string) => void | called in case of error |
`id?` | number | the unique id of the map when multi maps is needed. Default to 0.  |

**Returns:** void

___

### deleteOfflineRegion

▸ **deleteOfflineRegion**(`params?`: [OfflineRegionParams](../README.md#offlineregionparams), `successCallback?`: (result: { isDelete: boolean  }) => void, `errorCallback?`: (e: string) => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:293](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L293)*

Delete an offline region

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`params?` | [OfflineRegionParams](../README.md#offlineregionparams) |  |
`successCallback?` | (result: { isDelete: boolean  }) => void | a callback taking the result object of the deletion |
`errorCallback?` | (e: string) => void | called in case of error |
`id?` | number | the unique id of the map when multi maps is needed. Default to 0.  |

**Returns:** void

___

### deselect

▸ **deselect**(`callback?`: () => void, `errorCallback?`: (e: string) => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:342](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L342)*

Deselect the current feature of the map.

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`callback?` | () => void | - |
`errorCallback?` | (e: string) => void | called in case of error |
`id?` | number | the unique id of the map when multi maps is needed. Default to 0.  |

**Returns:** void

___

### destroy

▸ **destroy**(`id?`: number, `successCallback?`: () => void, `errorCallback?`: (e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:240](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L240)*

Destroy a map and free the memory.

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`id?` | number | the unique id of the map when multi maps is needed. Default to 0. |
`successCallback?` | () => void | called on success |
`errorCallback?` | (e: string) => void | called in case of error  |

**Returns:** void

___

### downloadRegion

▸ **downloadRegion**(`options`: [DownloadParams](../README.md#downloadparams), `statusCallback`: (state: [DownloadState](../README.md#downloadstate)) => void, `errorCallback?`: (e: string \| { reason: \"REGION\_EXISTS\"  }) => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:267](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L267)*

Download a region delimited by [Bounds](../README.md#bounds)()

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`options` | [DownloadParams](../README.md#downloadparams) |  |
`statusCallback` | (state: [DownloadState](../README.md#downloadstate)) => void | - |
`errorCallback?` | (e: string \| { reason: \"REGION\_EXISTS\"  }) => void | called in case of error |
`id?` | number | the unique id of the map when multi maps is needed. Default to 0.  |

**Returns:** void

___

### flyTo

▸ **flyTo**(`options`: { cameraPosition: Partial<[CameraPosition](../README.md#cameraposition)\> & { duration: number  }  }, `successCallback?`: () => void, `errorCallback?`: (e: string) => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:471](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L471)*

Smoothly jump to a position on the map.

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`options` | { cameraPosition: Partial<[CameraPosition](../README.md#cameraposition)\> & { duration: number  }  } |  |
`successCallback?` | () => void | called on success |
`errorCallback?` | (e: string) => void | called in case of error |
`id?` | number | the unique id of the map when multi maps is needed. Default to 0.  |

**Returns:** void

___

### getBounds

▸ **getBounds**(`resultCallback?`: (\_bounds: [Bounds](../README.md#bounds)) => void, `errorCallback?`: (\_e: string) => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:587](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L587)*

Get the current map bounds

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`resultCallback?` | (\_bounds: [Bounds](../README.md#bounds)) => void | takes the map [Bounds](../README.md#bounds) as argument |
`errorCallback?` | (\_e: string) => void | called in case of error |
`id?` | number | the unique id of the map when multi maps is needed. Default to 0.  |

**Returns:** void

___

### getCameraPosition

▸ **getCameraPosition**(`resultCallback`: (cameraPosition: [CameraPosition](../README.md#cameraposition)) => void, `errorCallback?`: (\_e: string) => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:598](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L598)*

Get the current map camera position

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`resultCallback` | (cameraPosition: [CameraPosition](../README.md#cameraposition)) => void | takes the map [CameraPosition](../README.md#cameraposition) as argument |
`errorCallback?` | (\_e: string) => void | called in case of error |
`id?` | number | the unique id of the map when multi maps is needed. Default to 0.  |

**Returns:** void

___

### getCenter

▸ **getCenter**(`resultCallback`: (center: [Coords](../README.md#coords)) => void, `errorCallback?`: (\_e: string) => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:498](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L498)*

Get the [Coords](../README.md#coords) of the map center.

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`resultCallback` | (center: [Coords](../README.md#coords)) => void | takes a [Coords](../README.md#coords) argument. |
`errorCallback?` | (\_e: string) => void | called in case of error |
`id?` | number | the unique id of the map when multi maps is needed. Default to 0.  |

**Returns:** void

___

### getOfflineRegionList

▸ **getOfflineRegionList**(`styleUrl?`: string, `successCallback?`: (regionNames: string[]) => void, `errorCallback?`: (e: string) => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:280](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L280)*

Get the availble names of offline region.

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`styleUrl?` | string | the Mapbox style URL |
`successCallback?` | (regionNames: string[]) => void | a callback taking a string Array of region names. |
`errorCallback?` | (e: string) => void | called in case of error |
`id?` | number | the unique id of the map when multi maps is needed. Default to 0.  |

**Returns:** void

___

### getPitch

▸ **getPitch**(`resultCallback?`: (pitch: number) => void, `errorCallback?`: (e: string) => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:535](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L535)*

Get the current map pitch

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`resultCallback?` | (pitch: number) => void | takes a the current pitch as argument. |
`errorCallback?` | (e: string) => void | called in case of error |
`id?` | number | the unique id of the map when multi maps is needed. Default to 0.  |

**Returns:** void

___

### getZoom

▸ **getZoom**(`resultCallback?`: (zoom: number) => void, `errorCallback?`: (e: string) => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:561](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L561)*

Return the current map zoom

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`resultCallback?` | (zoom: number) => void | takes a the current zoom as argument. |
`errorCallback?` | (e: string) => void | called in case of error |
`id?` | number | the unique id of the map when multi maps is needed. Default to 0.  |

**Returns:** void

___

### hide

▸ **hide**(`id?`: number, `successCallback?`: () => void, `errorCallback?`: (e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:229](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L229)*

Hide the map. The map is still in memory.

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`id?` | number | the unique id of the map when multi maps is needed. Default to 0. |
`successCallback?` | () => void | called on success |
`errorCallback?` | (e: string) => void | called in case of error  |

**Returns:** void

___

### pauseDownload

▸ **pauseDownload**(`options?`: [OfflineRegionParams](../README.md#offlineregionparams), `successCallback?`: () => void, `errorCallback?`: (e: string) => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:306](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L306)*

Pause the download of a region

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`options?` | [OfflineRegionParams](../README.md#offlineregionparams) |  |
`successCallback?` | () => void | called on success |
`errorCallback?` | (e: string) => void | called in case of error |
`id?` | number | the unique id of the map when multi maps is needed. Default to 0.  |

**Returns:** void

___

### removeImage

▸ **removeImage**(`imageId`: string, `successCallback?`: () => void, `errorCallback?`: (e: string) => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:370](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L370)*

Remove an image from the map style.

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`imageId` | string |  |
`successCallback?` | () => void | called on success |
`errorCallback?` | (e: string) => void | called in case of error |
`id?` | number | the unique id of the map when multi maps is needed. Default to 0.  |

**Returns:** void

___

### removeLayer

▸ **removeLayer**(`layerId`: string, `successCallback?`: () => void, `errorCallback?`: (\_e: string) => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:415](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L415)*

Remove a layer from the map style

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`layerId` | string |  |
`successCallback?` | () => void | called on success |
`errorCallback?` | (\_e: string) => void | called in case of error |
`id?` | number | the unique id of the map when multi maps is needed. Default to 0.  |

**Returns:** void

___

### removeSource

▸ **removeSource**(`sourceId`: string, `successCallback?`: () => void, `errorCallback?`: (e: string) => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:443](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L443)*

Remove a source from the map style

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`sourceId` | string |  |
`successCallback?` | () => void | called on success |
`errorCallback?` | (e: string) => void | called in case of error |
`id?` | number | the unique id of the map when multi maps is needed. Default to 0.  |

**Returns:** void

___

### resumeDownload

▸ **resumeDownload**(`options?`: [OfflineRegionParams](../README.md#offlineregionparams), `successCallback?`: () => void, `errorCallback?`: (e: string) => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:319](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L319)*

Resume the paused download of a region

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`options?` | [OfflineRegionParams](../README.md#offlineregionparams) |  |
`successCallback?` | () => void | called on success |
`errorCallback?` | (e: string) => void | called in case of error |
`id?` | number | the unique id of the map when multi maps is needed. Default to 0.  |

**Returns:** void

___

### scrollMap

▸ **scrollMap**(`delta`: [number, number], `successCallback`: (center: [Coords](../README.md#coords)) => void, `errorCallback?`: (e: string) => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:510](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L510)*

Animate a camera translation.

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`delta` | [number, number] |  |
`successCallback` | (center: [Coords](../README.md#coords)) => void | called when animation start |
`errorCallback?` | (e: string) => void | called in case of error |
`id?` | number | the unique id of the map when multi maps is needed. Default to 0.  |

**Returns:** void

___

### setCenter

▸ **setCenter**(`center`: [LngLat](../README.md#lnglat), `successCallback?`: () => void, `errorCallback?`: (e: string) => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:486](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L486)*

Center the map on another [LngLat](../README.md#lnglat) coordinates. (not animated)

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`center` | [LngLat](../README.md#lnglat) | the new coordinautes |
`successCallback?` | () => void | called when animation start |
`errorCallback?` | (e: string) => void | called in case of error |
`id?` | number | the unique id of the map when multi maps is needed. Default to 0.  |

**Returns:** void

___

### setClickable

▸ **setClickable**(`isClickable`: boolean, `successCallback?`: () => void, `errorCallback?`: (e: string) => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:217](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L217)*

Set a map as clickable. If false, the user won't be abble to move the map
with gesture.

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`isClickable` | boolean |  |
`successCallback?` | () => void | called on success |
`errorCallback?` | (e: string) => void | called in case of error |
`id?` | number | the unique id of the map when multi maps is needed. Default to 0.  |

**Returns:** void

___

### setContainer

▸ **setContainer**(`params`: [MapboxContainerParams](../README.md#mapboxcontainerparams), `successCallback?`: () => void, `errorCallback?`: (e: string) => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:254](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L254)*

Set the HTML container of the Map. It will resize
the map to fit in the container and update the clickable
HTML elements overlaying the map.

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`params` | [MapboxContainerParams](../README.md#mapboxcontainerparams) |  |
`successCallback?` | () => void | called on success |
`errorCallback?` | (e: string) => void | called in case of error |
`id?` | number | the unique id of the map when multi maps is needed. Default to 0.  |

**Returns:** void

___

### setDebug

▸ **setDebug**(`isDebug`: boolean, `successCallback?`: () => void, `errorCallback?`: (e: string) => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:203](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L203)*

Colorize clickable HTML elements with for debug purpose.

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`isDebug` | boolean |  |
`successCallback?` | () => void | called on success |
`errorCallback?` | (e: string) => void | called in case of error |
`id?` | number | the unique id of the map when multi maps is needed. Default to 0.  |

**Returns:** void

___

### setGeoJson

▸ **setGeoJson**(`sourceId`: string, `geoJson`: [SourceData](../README.md#sourcedata)<any, any\>, `successCallback?`: () => void, `errorCallback?`: (e: string) => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:457](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L457)*

Set the data of a source. Use this when you want to animate data.

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`sourceId` | string |  |
`geoJson` | [SourceData](../README.md#sourcedata)<any, any\> |  |
`successCallback?` | () => void | called on success |
`errorCallback?` | (e: string) => void | called in case of error |
`id?` | number | the unique id of the map when multi maps is needed. Default to 0.  |

**Returns:** void

___

### setLayoutProperty

▸ **setLayoutProperty**(`layerId`: string, `name`: [LayoutPropertyName](../README.md#layoutpropertyname), `value`: any, `successCallback?`: () => void, `errorCallback?`: (\_e: string) => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:400](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L400)*

Set a layout property for a layer

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`layerId` | string |  |
`name` | [LayoutPropertyName](../README.md#layoutpropertyname) | the property name |
`value` | any | the property value |
`successCallback?` | () => void | called on success |
`errorCallback?` | (\_e: string) => void | called in case of error |
`id?` | number | the unique id of the map when multi maps is needed. Default to 0.  |

**Returns:** void

___

### setPitch

▸ **setPitch**(`pitch`: number, `successCallback?`: () => void, `errorCallback?`: (e: string) => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:523](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L523)*

Set the map pitch (not animated)

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`pitch` | number |  |
`successCallback?` | () => void | called on success |
`errorCallback?` | (e: string) => void | called in case of error |
`id?` | number | the unique id of the map when multi maps is needed. Default to 0.  |

**Returns:** void

___

### setZoom

▸ **setZoom**(`zoom`: number, `options?`: any, `successCallback?`: () => void, `errorCallback?`: (e: string) => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:548](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L548)*

Set the zoom of the map (not animated)

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`zoom` | number |  |
`options?` | any |  |
`successCallback?` | () => void |  |
`errorCallback?` | (e: string) => void |  |
`id?` | number |   |

**Returns:** void

___

### show

▸ **show**(`options`: [MapOptions](../README.md#mapoptions), `successCallback?`: () => void, `errorCallback?`: (e: string) => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:190](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L190)*

Display the map. Create it if needed.

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`options` | [MapOptions](../README.md#mapoptions) |  |
`successCallback?` | () => void | called when the map is displayed |
`errorCallback?` | (e: string) => void | called in case of error |
`id?` | number | the unique id of the map when multi maps is needed. Default to 0.  |

**Returns:** void

___

### zoomTo

▸ **zoomTo**(`zoom`: number, `options?`: any, `successCallback?`: () => void, `errorCallback?`: (e: string) => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:574](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/bce9504/src/js/cordova-plugin-mapbox.ts#L574)*

#### Parameters:

Name | Type | Description |
------ | ------ | ------ |
`zoom` | number |  |
`options?` | any |  |
`successCallback?` | () => void | called when animation start |
`errorCallback?` | (e: string) => void | called in case of error |
`id?` | number | the unique id of the map when multi maps is needed. Default to 0.  |

**Returns:** void
