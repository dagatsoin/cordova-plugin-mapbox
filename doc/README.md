**[cordova-plugin-mapbox](README.md)**

> Globals

# cordova-plugin-mapbox

## Index

### Enumerations

* [Command](enums/command.md)
* [MapEventType](enums/mapeventtype.md)

### Interfaces

* [Mapbox](interfaces/mapbox.md)

### Type aliases

* [Bounds](README.md#bounds)
* [CameraOptions](README.md#cameraoptions)
* [CameraPosition](README.md#cameraposition)
* [Coords](README.md#coords)
* [DownloadParams](README.md#downloadparams)
* [DownloadState](README.md#downloadstate)
* [Expression](README.md#expression)
* [ImageProperties](README.md#imageproperties)
* [Layer](README.md#layer)
* [LayerType](README.md#layertype)
* [LayoutPropertyName](README.md#layoutpropertyname)
* [LngLat](README.md#lnglat)
* [MapEventListener](README.md#mapeventlistener)
* [MapEventPayload](README.md#mapeventpayload)
* [MapOptions](README.md#mapoptions)
* [MapboxContainerParams](README.md#mapboxcontainerparams)
* [OfflineRegionParams](README.md#offlineregionparams)
* [ScreenCoords](README.md#screencoords)
* [Source](README.md#source)
* [SourceData](README.md#sourcedata)

### Variables

* [MAPBOX](README.md#mapbox)
* [cordova](README.md#cordova)

### Functions

* [addImage](README.md#addimage)
* [addLayer](README.md#addlayer)
* [addMapClickCallback](README.md#addmapclickcallback)
* [addOnCameraDidChangeListener](README.md#addoncameradidchangelistener)
* [addOnCameraWillChangeListener](README.md#addoncamerawillchangelistener)
* [addOnDidFinishLoadingMapListener](README.md#addondidfinishloadingmaplistener)
* [addOnDidFinishLoadingStyleListener](README.md#addondidfinishloadingstylelistener)
* [addOnDidFinishRenderingFrameListener](README.md#addondidfinishrenderingframelistener)
* [addOnDidFinishRenderingMapListener](README.md#addondidfinishrenderingmaplistener)
* [addOnFlingListener](README.md#addonflinglistener)
* [addOnMoveListener](README.md#addonmovelistener)
* [addOnRotateListener](README.md#addonrotatelistener)
* [addOnScaleListener](README.md#addonscalelistener)
* [addOnSourceChangedListener](README.md#addonsourcechangedlistener)
* [addOnWillStartLoadingMapListener](README.md#addonwillstartloadingmaplistener)
* [addOnWillStartRenderingFrameListener](README.md#addonwillstartrenderingframelistener)
* [addOnWillStartRenderingMapListener](README.md#addonwillstartrenderingmaplistener)
* [addSource](README.md#addsource)
* [convertCoordinates](README.md#convertcoordinates)
* [convertPoint](README.md#convertpoint)
* [deleteOfflineRegion](README.md#deleteofflineregion)
* [deselect](README.md#deselect)
* [destroy](README.md#destroy)
* [downloadRegion](README.md#downloadregion)
* [flatElements](README.md#flatelements)
* [flyTo](README.md#flyto)
* [getAllChildren](README.md#getallchildren)
* [getBounds](README.md#getbounds)
* [getCameraPosition](README.md#getcameraposition)
* [getCenter](README.md#getcenter)
* [getContainerChildrenOverlayElements](README.md#getcontainerchildrenoverlayelements)
* [getDivRect](README.md#getdivrect)
* [getOfflineRegionList](README.md#getofflineregionlist)
* [getOverlayElements](README.md#getoverlayelements)
* [getPageRect](README.md#getpagerect)
* [getPitch](README.md#getpitch)
* [getZoom](README.md#getzoom)
* [hide](README.md#hide)
* [pauseDownload](README.md#pausedownload)
* [removeImage](README.md#removeimage)
* [removeLayer](README.md#removelayer)
* [removeSource](README.md#removesource)
* [resumeDownload](README.md#resumedownload)
* [scrollMap](README.md#scrollmap)
* [setCenter](README.md#setcenter)
* [setClickable](README.md#setclickable)
* [setContainer](README.md#setcontainer)
* [setDebug](README.md#setdebug)
* [setGeoJson](README.md#setgeojson)
* [setLayoutProperty](README.md#setlayoutproperty)
* [setPitch](README.md#setpitch)
* [setRandomId](README.md#setrandomid)
* [setZoom](README.md#setzoom)
* [show](README.md#show)
* [toOverlayElement](README.md#tooverlayelement)
* [zoomTo](README.md#zoomto)

## Type aliases

### Bounds

Ƭ  **Bounds**: { ne: [number, number] ; sw: [number, number]  }

*Defined in [cordova-plugin-mapbox.ts:36](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L36)*

#### Type declaration:

Name | Type |
------ | ------ |
`ne` | [number, number] |
`sw` | [number, number] |

___

### CameraOptions

Ƭ  **CameraOptions**: { around: [LngLat](README.md#lnglat) ; bearing: number ; center: [LngLat](README.md#lnglat) ; pitch: number ; zoom: number  }

*Defined in [cordova-plugin-mapbox.ts:90](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L90)*

#### Type declaration:

Name | Type |
------ | ------ |
`around` | [LngLat](README.md#lnglat) |
`bearing` | number |
`center` | [LngLat](README.md#lnglat) |
`pitch` | number |
`zoom` | number |

___

### CameraPosition

Ƭ  **CameraPosition**: { bearing: number ; target: [Coords](README.md#coords) ; tilt: number ; zoom: number  }

*Defined in [cordova-plugin-mapbox.ts:98](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L98)*

#### Type declaration:

Name | Type |
------ | ------ |
`bearing` | number |
`target` | [Coords](README.md#coords) |
`tilt` | number |
`zoom` | number |

___

### Coords

Ƭ  **Coords**: { lat: number ; lng: number  }

*Defined in [cordova-plugin-mapbox.ts:124](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L124)*

#### Type declaration:

Name | Type |
------ | ------ |
`lat` | number |
`lng` | number |

___

### DownloadParams

Ƭ  **DownloadParams**: { bounds: [Bounds](README.md#bounds) ; maxZoom: number ; minZoom: number ; regionName: string ; styleUrl?: string  }

*Defined in [cordova-plugin-mapbox.ts:169](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L169)*

#### Type declaration:

Name | Type |
------ | ------ |
`bounds` | [Bounds](README.md#bounds) |
`maxZoom` | number |
`minZoom` | number |
`regionName` | string |
`styleUrl?` | string |

___

### DownloadState

Ƭ  **DownloadState**: { completeResourceSize: number ; completeTileCount: number ; completeTileSize: number ; completedResourceCount: number ; downloadState: \"STATE\_ACTIVE\" \| \"STATE\_INACTIVE\" ; isComplete: boolean ; regionName: string ; requiredResourceCount: number  }

*Defined in [cordova-plugin-mapbox.ts:54](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L54)*

#### Type declaration:

Name | Type |
------ | ------ |
`completeResourceSize` | number |
`completeTileCount` | number |
`completeTileSize` | number |
`completedResourceCount` | number |
`downloadState` | \"STATE\_ACTIVE\" \| \"STATE\_INACTIVE\" |
`isComplete` | boolean |
`regionName` | string |
`requiredResourceCount` | number |

___

### Expression

Ƭ  **Expression**: Array<string \| boolean \| number \| [Expression](README.md#expression)\>

*Defined in [cordova-plugin-mapbox.ts:52](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L52)*

___

### ImageProperties

Ƭ  **ImageProperties**: { height: number ; path: string ; width: number  }

*Defined in [cordova-plugin-mapbox.ts:140](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L140)*

The properties of an image used by the Mapbox map.
The path is relative to the app assets folder.
If you want to access an image from the `www` folder
you will set the path value as `www/myImage.png`

#### Type declaration:

Name | Type |
------ | ------ |
`height` | number |
`path` | string |
`width` | number |

___

### Layer

Ƭ  **Layer**: { filter?: [Expression](README.md#expression) ; id: string ; layout?: Partial<{ icon-allow-overlap: boolean ; icon-image: string ; icon-offset: [number, number] ; icon-size: number \| [Expression](README.md#expression) ; text-field: string ; text-font: string[] ; text-size: number \| [Expression](README.md#expression)  }\> ; maxzoom?: number ; minzoom?: number ; paint?: Partial<{ text-color: string \| [Expression](README.md#expression) ; text-halo-blur: number \| [Expression](README.md#expression) ; text-halo-color: string \| [Expression](README.md#expression) ; text-halo-width: number \| [Expression](README.md#expression)  }\> ; source: string ; sourcelayer?: string ; type?: [LayerType](README.md#layertype)  }

*Defined in [cordova-plugin-mapbox.ts:65](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L65)*

#### Type declaration:

Name | Type |
------ | ------ |
`filter?` | [Expression](README.md#expression) |
`id` | string |
`layout?` | Partial<{ icon-allow-overlap: boolean ; icon-image: string ; icon-offset: [number, number] ; icon-size: number \| [Expression](README.md#expression) ; text-field: string ; text-font: string[] ; text-size: number \| [Expression](README.md#expression)  }\> |
`maxzoom?` | number |
`minzoom?` | number |
`paint?` | Partial<{ text-color: string \| [Expression](README.md#expression) ; text-halo-blur: number \| [Expression](README.md#expression) ; text-halo-color: string \| [Expression](README.md#expression) ; text-halo-width: number \| [Expression](README.md#expression)  }\> |
`source` | string |
`sourcelayer?` | string |
`type?` | [LayerType](README.md#layertype) |

___

### LayerType

Ƭ  **LayerType**: \"symbol\"

*Defined in [cordova-plugin-mapbox.ts:41](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L41)*

___

### LayoutPropertyName

Ƭ  **LayoutPropertyName**: \"icon-image\" \| \"icon-offset\" \| \"icon-size\" \| \"icon-allow-overlap\" \| \"text-field\" \| \"text-size\" \| \"text-font\"

*Defined in [cordova-plugin-mapbox.ts:25](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L25)*

___

### LngLat

Ƭ  **LngLat**: [number, number]

*Defined in [cordova-plugin-mapbox.ts:122](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L122)*

___

### MapEventListener

Ƭ  **MapEventListener**: (payload: [MapEventPayload](README.md#mapeventpayload)) => void

*Defined in [cordova-plugin-mapbox.ts:34](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L34)*

___

### MapEventPayload

Ƭ  **MapEventPayload**: { latLngBounds: [Bounds](README.md#bounds) ; type: [MapEventType](enums/mapeventtype.md)  }

*Defined in [cordova-plugin-mapbox.ts:20](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L20)*

#### Type declaration:

Name | Type |
------ | ------ |
`latLngBounds` | [Bounds](README.md#bounds) |
`type` | [MapEventType](enums/mapeventtype.md) |

___

### MapOptions

Ƭ  **MapOptions**: { additionalDomElements?: HTMLElement[] ; cameraPosition?: any ; disablePitch?: boolean ; disableRotation?: boolean ; disableScroll?: boolean ; disableTilt?: boolean ; disableZoom?: boolean ; domContainer: HTMLElement ; hideAttribution?: boolean ; hideCompass?: boolean ; hideLogo?: boolean ; selectableFeaturePropType?: string ; selectedFeatureLayerId?: string ; selectedFeatureSourceId?: string ; style: string  }

*Defined in [cordova-plugin-mapbox.ts:146](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L146)*

#### Type declaration:

Name | Type |
------ | ------ |
`additionalDomElements?` | HTMLElement[] |
`cameraPosition?` | any |
`disablePitch?` | boolean |
`disableRotation?` | boolean |
`disableScroll?` | boolean |
`disableTilt?` | boolean |
`disableZoom?` | boolean |
`domContainer` | HTMLElement |
`hideAttribution?` | boolean |
`hideCompass?` | boolean |
`hideLogo?` | boolean |
`selectableFeaturePropType?` | string |
`selectedFeatureLayerId?` | string |
`selectedFeatureSourceId?` | string |
`style` | string |

___

### MapboxContainerParams

Ƭ  **MapboxContainerParams**: { additionalDomElements?: HTMLElement[] ; domContainer: HTMLElement  }

*Defined in [cordova-plugin-mapbox.ts:164](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L164)*

#### Type declaration:

Name | Type |
------ | ------ |
`additionalDomElements?` | HTMLElement[] |
`domContainer` | HTMLElement |

___

### OfflineRegionParams

Ƭ  **OfflineRegionParams**: { regionName?: string ; styleUrl?: string  }

*Defined in [cordova-plugin-mapbox.ts:177](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L177)*

#### Type declaration:

Name | Type |
------ | ------ |
`regionName?` | string |
`styleUrl?` | string |

___

### ScreenCoords

Ƭ  **ScreenCoords**: { x: number ; y: number  }

*Defined in [cordova-plugin-mapbox.ts:129](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L129)*

#### Type declaration:

Name | Type |
------ | ------ |
`x` | number |
`y` | number |

___

### Source

Ƭ  **Source**<T, P\>: { cluster?: boolean ; clusterMaxZoom?: number ; clusterRadius?: number ; data: [SourceData](README.md#sourcedata)<T, P\> ; type: \"geojson\"  }

*Defined in [cordova-plugin-mapbox.ts:114](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L114)*

#### Type parameters:

Name | Type |
------ | ------ |
`T` | GeoJSON.GeometryObject |
`P` | - |

#### Type declaration:

Name | Type |
------ | ------ |
`cluster?` | boolean |
`clusterMaxZoom?` | number |
`clusterRadius?` | number |
`data` | [SourceData](README.md#sourcedata)<T, P\> |
`type` | \"geojson\" |

___

### SourceData

Ƭ  **SourceData**<T, P\>: {} \| FeatureCollection<T, P\> \| Feature<T, P\>

*Defined in [cordova-plugin-mapbox.ts:106](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L106)*

#### Type parameters:

Name | Type |
------ | ------ |
`T` | GeoJSON.GeometryObject |
`P` | - |

## Variables

### MAPBOX

• `Const` **MAPBOX**: \"Mapbox\" = "Mapbox"

*Defined in [cordova-plugin-mapbox.ts:656](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L656)*

___

### cordova

•  **cordova**: Cordova

*Defined in [cordova-plugin-mapbox.ts:654](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L654)*

## Functions

### addImage

▸ `Const`**addImage**(`imageId`: string, `image`: [ImageProperties](README.md#imageproperties), `successCallback`: () => void, `errorCallback`: (e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:990](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L990)*

#### Parameters:

Name | Type |
------ | ------ |
`imageId` | string |
`image` | [ImageProperties](README.md#imageproperties) |
`successCallback` | () => void |
`errorCallback` | (e: string) => void |

**Returns:** void

___

### addLayer

▸ `Const`**addLayer**(`layerObject`: [Layer](README.md#layer), `beforeId`: number, `successCallback`: () => void, `errorCallback`: (e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:1012](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L1012)*

#### Parameters:

Name | Type |
------ | ------ |
`layerObject` | [Layer](README.md#layer) |
`beforeId` | number |
`successCallback` | () => void |
`errorCallback` | (e: string) => void |

**Returns:** void

___

### addMapClickCallback

▸ `Const`**addMapClickCallback**(`callback`: (featureCollection: FeatureCollection<GeoJSON.GeometryObject\>) => void, `errorCallback`: (e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:975](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L975)*

#### Parameters:

Name | Type |
------ | ------ |
`callback` | (featureCollection: FeatureCollection<GeoJSON.GeometryObject\>) => void |
`errorCallback` | (e: string) => void |

**Returns:** void

___

### addOnCameraDidChangeListener

▸ `Const`**addOnCameraDidChangeListener**(`listener`: () => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:1263](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L1263)*

This event is triggered whenever the displayed map region finished changing without an animation.

#### Parameters:

Name | Type | Default value |
------ | ------ | ------ |
`listener` | () => void | - |
`id` | number | 0 |

**Returns:** void

___

### addOnCameraWillChangeListener

▸ `Const`**addOnCameraWillChangeListener**(`listener`: () => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:1250](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L1250)*

This event is triggered whenever the displayed map region is about to change without animation.

#### Parameters:

Name | Type | Default value |
------ | ------ | ------ |
`listener` | () => void | - |
`id` | number | 0 |

**Returns:** void

___

### addOnDidFinishLoadingMapListener

▸ `Const`**addOnDidFinishLoadingMapListener**(`listener`: () => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:1328](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L1328)*

This is triggered when the map has successfully loaded a new map style.

#### Parameters:

Name | Type | Default value |
------ | ------ | ------ |
`listener` | () => void | - |
`id` | number | 0 |

**Returns:** void

___

### addOnDidFinishLoadingStyleListener

▸ `Const`**addOnDidFinishLoadingStyleListener**(`listener`: () => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:1276](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L1276)*

Triggered when a style has finished loading.

#### Parameters:

Name | Type | Default value |
------ | ------ | ------ |
`listener` | () => void | - |
`id` | number | 0 |

**Returns:** void

___

### addOnDidFinishRenderingFrameListener

▸ `Const`**addOnDidFinishRenderingFrameListener**(`listener`: () => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:1315](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L1315)*

This event is triggered when the map finished rendering a frame.

#### Parameters:

Name | Type | Default value |
------ | ------ | ------ |
`listener` | () => void | - |
`id` | number | 0 |

**Returns:** void

___

### addOnDidFinishRenderingMapListener

▸ **addOnDidFinishRenderingMapListener**(`listener`: (fully: boolean) => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:1341](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L1341)*

This event is triggered when the map is fully rendered.

#### Parameters:

Name | Type | Default value |
------ | ------ | ------ |
`listener` | (fully: boolean) => void | - |
`id` | number | 0 |

**Returns:** void

___

### addOnFlingListener

▸ `Const`**addOnFlingListener**(`listener`: [MapEventListener](README.md#mapeventlistener), `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:1205](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L1205)*

#### Parameters:

Name | Type | Default value |
------ | ------ | ------ |
`listener` | [MapEventListener](README.md#mapeventlistener) | - |
`id` | number | 0 |

**Returns:** void

___

### addOnMoveListener

▸ `Const`**addOnMoveListener**(`listener`: [MapEventListener](README.md#mapeventlistener), `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:1209](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L1209)*

#### Parameters:

Name | Type | Default value |
------ | ------ | ------ |
`listener` | [MapEventListener](README.md#mapeventlistener) | - |
`id` | number | 0 |

**Returns:** void

___

### addOnRotateListener

▸ `Const`**addOnRotateListener**(`listener`: [MapEventListener](README.md#mapeventlistener), `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:1213](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L1213)*

#### Parameters:

Name | Type | Default value |
------ | ------ | ------ |
`listener` | [MapEventListener](README.md#mapeventlistener) | - |
`id` | number | 0 |

**Returns:** void

___

### addOnScaleListener

▸ `Const`**addOnScaleListener**(`listener`: [MapEventListener](README.md#mapeventlistener), `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:1217](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L1217)*

#### Parameters:

Name | Type | Default value |
------ | ------ | ------ |
`listener` | [MapEventListener](README.md#mapeventlistener) | - |
`id` | number | 0 |

**Returns:** void

___

### addOnSourceChangedListener

▸ `Const`**addOnSourceChangedListener**(`listener`: () => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:1289](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L1289)*

Triggered when a source changes.

#### Parameters:

Name | Type | Default value |
------ | ------ | ------ |
`listener` | () => void | - |
`id` | number | 0 |

**Returns:** void

___

### addOnWillStartLoadingMapListener

▸ `Const`**addOnWillStartLoadingMapListener**(`listener`: () => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:1224](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L1224)*

This event is triggered when the map is about to start loading a new map style.

#### Parameters:

Name | Type | Default value |
------ | ------ | ------ |
`listener` | () => void | - |
`id` | number | 0 |

**Returns:** void

___

### addOnWillStartRenderingFrameListener

▸ `Const`**addOnWillStartRenderingFrameListener**(`listener`: () => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:1302](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L1302)*

This event is triggered when the map will start rendering a frame.

#### Parameters:

Name | Type | Default value |
------ | ------ | ------ |
`listener` | () => void | - |
`id` | number | 0 |

**Returns:** void

___

### addOnWillStartRenderingMapListener

▸ `Const`**addOnWillStartRenderingMapListener**(`listener`: () => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:1237](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L1237)*

This event is triggered when the map will start rendering the map.

#### Parameters:

Name | Type | Default value |
------ | ------ | ------ |
`listener` | () => void | - |
`id` | number | 0 |

**Returns:** void

___

### addSource

▸ `Const`**addSource**(`sourceId`: string, `source`: [Source](README.md#source)<any, any\>, `successCallback`: () => void, `errorCallback`: (e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:1050](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L1050)*

#### Parameters:

Name | Type |
------ | ------ |
`sourceId` | string |
`source` | [Source](README.md#source)<any, any\> |
`successCallback` | () => void |
`errorCallback` | (e: string) => void |

**Returns:** void

___

### convertCoordinates

▸ `Const`**convertCoordinates**(`coords`: { lat: number ; lng: number  }, `resultCallback`: (\_point: [ScreenCoords](README.md#screencoords)) => void, `errorCallback`: (\_e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:1181](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L1181)*

#### Parameters:

Name | Type |
------ | ------ |
`coords` | { lat: number ; lng: number  } |
`resultCallback` | (\_point: [ScreenCoords](README.md#screencoords)) => void |
`errorCallback` | (\_e: string) => void |

**Returns:** void

___

### convertPoint

▸ `Const`**convertPoint**(`point`: [ScreenCoords](README.md#screencoords), `resultCallback`: (\_coords: { lat: number ; lng: number  }) => void, `errorCallback`: (\_e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:1195](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L1195)*

#### Parameters:

Name | Type |
------ | ------ |
`point` | [ScreenCoords](README.md#screencoords) |
`resultCallback` | (\_coords: { lat: number ; lng: number  }) => void |
`errorCallback` | (\_e: string) => void |

**Returns:** void

___

### deleteOfflineRegion

▸ `Const`**deleteOfflineRegion**(`options`: [OfflineRegionParams](README.md#offlineregionparams), `_successCallback`: (isDeleted: boolean) => void, `_errorCallback`: (e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:927](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L927)*

#### Parameters:

Name | Type |
------ | ------ |
`options` | [OfflineRegionParams](README.md#offlineregionparams) |
`_successCallback` | (isDeleted: boolean) => void |
`_errorCallback` | (e: string) => void |

**Returns:** void

___

### deselect

▸ `Const`**deselect**(`callback?`: () => void, `errorCallback?`: (\_e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:983](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L983)*

#### Parameters:

Name | Type |
------ | ------ |
`callback?` | () => void |
`errorCallback?` | (\_e: string) => void |

**Returns:** void

___

### destroy

▸ `Const`**destroy**(`successCallback`: () => void, `errorCallback`: (e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:871](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L871)*

#### Parameters:

Name | Type |
------ | ------ |
`successCallback` | () => void |
`errorCallback` | (e: string) => void |

**Returns:** void

___

### downloadRegion

▸ `Const`**downloadRegion**(`options`: [DownloadParams](README.md#downloadparams), `statusCallback`: (state: [DownloadState](README.md#downloadstate)) => void, `errorCallback`: (e: string \| { reason: \"REGION\_EXISTS\"  }) => void): void

*Defined in [cordova-plugin-mapbox.ts:897](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L897)*

#### Parameters:

Name | Type |
------ | ------ |
`options` | [DownloadParams](README.md#downloadparams) |
`statusCallback` | (state: [DownloadState](README.md#downloadstate)) => void |
`errorCallback` | (e: string \| { reason: \"REGION\_EXISTS\"  }) => void |

**Returns:** void

___

### flatElements

▸ **flatElements**(`elements`: HTMLElement[]): any[]

*Defined in [cordova-plugin-mapbox.ts:777](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L777)*

#### Parameters:

Name | Type |
------ | ------ |
`elements` | HTMLElement[] |

**Returns:** any[]

___

### flyTo

▸ `Const`**flyTo**(`options`: Partial<[CameraPosition](README.md#cameraposition)\> & { duration: number  }, `successCallback`: () => void, `errorCallback`: (e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:1084](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L1084)*

#### Parameters:

Name | Type |
------ | ------ |
`options` | Partial<[CameraPosition](README.md#cameraposition)\> & { duration: number  } |
`successCallback` | () => void |
`errorCallback` | (e: string) => void |

**Returns:** void

___

### getAllChildren

▸ **getAllChildren**(`el`: HTMLElement): any[]

*Defined in [cordova-plugin-mapbox.ts:747](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L747)*

#### Parameters:

Name | Type |
------ | ------ |
`el` | HTMLElement |

**Returns:** any[]

___

### getBounds

▸ `Const`**getBounds**(`resultCallback`: (\_bounds: [Bounds](README.md#bounds)) => void, `errorCallback`: (\_e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:1161](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L1161)*

#### Parameters:

Name | Type |
------ | ------ |
`resultCallback` | (\_bounds: [Bounds](README.md#bounds)) => void |
`errorCallback` | (\_e: string) => void |

**Returns:** void

___

### getCameraPosition

▸ `Const`**getCameraPosition**(`resultCallback`: (cameraPosition: [CameraPosition](README.md#cameraposition)) => void, `errorCallback`: (\_e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:1168](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L1168)*

#### Parameters:

Name | Type |
------ | ------ |
`resultCallback` | (cameraPosition: [CameraPosition](README.md#cameraposition)) => void |
`errorCallback` | (\_e: string) => void |

**Returns:** void

___

### getCenter

▸ `Const`**getCenter**(`resultCallback`: (center: [Coords](README.md#coords)) => void, `errorCallback`: (\_e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:1104](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L1104)*

#### Parameters:

Name | Type |
------ | ------ |
`resultCallback` | (center: [Coords](README.md#coords)) => void |
`errorCallback` | (\_e: string) => void |

**Returns:** void

___

### getContainerChildrenOverlayElements

▸ **getContainerChildrenOverlayElements**(`el`: HTMLElement): any[]

*Defined in [cordova-plugin-mapbox.ts:711](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L711)*

#### Parameters:

Name | Type |
------ | ------ |
`el` | HTMLElement |

**Returns:** any[]

___

### getDivRect

▸ **getDivRect**(`el`: HTMLElement): object

*Defined in [cordova-plugin-mapbox.ts:784](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L784)*

#### Parameters:

Name | Type |
------ | ------ |
`el` | HTMLElement |

**Returns:** object

Name | Type |
------ | ------ |
`height` | number |
`left` | number |
`top` | number |
`width` | number |

___

### getOfflineRegionList

▸ `Const`**getOfflineRegionList**(`styleUrl`: string, `_successCallback`: (regionNames: string[]) => void, `_errorCallback`: (e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:907](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L907)*

#### Parameters:

Name | Type |
------ | ------ |
`styleUrl` | string |
`_successCallback` | (regionNames: string[]) => void |
`_errorCallback` | (e: string) => void |

**Returns:** void

___

### getOverlayElements

▸ **getOverlayElements**(`el`: HTMLElement): any[]

*Defined in [cordova-plugin-mapbox.ts:721](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L721)*

#### Parameters:

Name | Type |
------ | ------ |
`el` | HTMLElement |

**Returns:** any[]

___

### getPageRect

▸ **getPageRect**(): object

*Defined in [cordova-plugin-mapbox.ts:803](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L803)*

**Returns:** object

Name | Type |
------ | ------ |
`height` | number |
`left` | number |
`top` | number |
`width` | number |

___

### getPitch

▸ `Const`**getPitch**(`resultCallback`: (pitch: number) => void, `errorCallback`: (e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:1131](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L1131)*

#### Parameters:

Name | Type |
------ | ------ |
`resultCallback` | (pitch: number) => void |
`errorCallback` | (e: string) => void |

**Returns:** void

___

### getZoom

▸ `Const`**getZoom**(`resultCallback`: (zoom: number) => void, `errorCallback`: (e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:1146](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L1146)*

#### Parameters:

Name | Type |
------ | ------ |
`resultCallback` | (zoom: number) => void |
`errorCallback` | (e: string) => void |

**Returns:** void

___

### hide

▸ `Const`**hide**(`successCallback?`: () => void, `errorCallback?`: (e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:864](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L864)*

#### Parameters:

Name | Type |
------ | ------ |
`successCallback?` | () => void |
`errorCallback?` | (e: string) => void |

**Returns:** void

___

### pauseDownload

▸ `Const`**pauseDownload**(`options`: [OfflineRegionParams](README.md#offlineregionparams), `_successCallback`: () => void, `_errorCallback`: (e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:946](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L946)*

#### Parameters:

Name | Type |
------ | ------ |
`options` | [OfflineRegionParams](README.md#offlineregionparams) |
`_successCallback` | () => void |
`_errorCallback` | (e: string) => void |

**Returns:** void

___

### removeImage

▸ `Const`**removeImage**(`imageId`: string, `successCallback`: () => void, `errorCallback`: (e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:1002](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L1002)*

#### Parameters:

Name | Type |
------ | ------ |
`imageId` | string |
`successCallback` | () => void |
`errorCallback` | (e: string) => void |

**Returns:** void

___

### removeLayer

▸ `Const`**removeLayer**(`layerId`: string, `successCallback`: () => void, `errorCallback`: (\_e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:1040](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L1040)*

#### Parameters:

Name | Type |
------ | ------ |
`layerId` | string |
`successCallback` | () => void |
`errorCallback` | (\_e: string) => void |

**Returns:** void

___

### removeSource

▸ `Const`**removeSource**(`sourceId`: string, `successCallback`: () => void, `errorCallback`: (e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:1062](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L1062)*

#### Parameters:

Name | Type |
------ | ------ |
`sourceId` | string |
`successCallback` | () => void |
`errorCallback` | (e: string) => void |

**Returns:** void

___

### resumeDownload

▸ `Const`**resumeDownload**(`options`: [OfflineRegionParams](README.md#offlineregionparams), `_successCallback`: () => void, `_errorCallback`: (e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:961](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L961)*

#### Parameters:

Name | Type |
------ | ------ |
`options` | [OfflineRegionParams](README.md#offlineregionparams) |
`_successCallback` | () => void |
`_errorCallback` | (e: string) => void |

**Returns:** void

___

### scrollMap

▸ `Const`**scrollMap**(`delta`: [number, number], `successCallback`: (center: [Coords](README.md#coords)) => void, `errorCallback`: (e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:1111](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L1111)*

#### Parameters:

Name | Type |
------ | ------ |
`delta` | [number, number] |
`successCallback` | (center: [Coords](README.md#coords)) => void |
`errorCallback` | (e: string) => void |

**Returns:** void

___

### setCenter

▸ `Const`**setCenter**(`center`: [LngLat](README.md#lnglat), `successCallback`: () => void, `errorCallback`: (e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:1094](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L1094)*

#### Parameters:

Name | Type |
------ | ------ |
`center` | [LngLat](README.md#lnglat) |
`successCallback` | () => void |
`errorCallback` | (e: string) => void |

**Returns:** void

___

### setClickable

▸ `Const`**setClickable**(`clickable`: boolean, `successCallback`: () => void, `errorCallback`: (e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:854](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L854)*

#### Parameters:

Name | Type |
------ | ------ |
`clickable` | boolean |
`successCallback` | () => void |
`errorCallback` | (e: string) => void |

**Returns:** void

___

### setContainer

▸ `Const`**setContainer**(`params`: [MapboxContainerParams](README.md#mapboxcontainerparams), `successCallback`: () => void, `errorCallback`: (e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:878](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L878)*

#### Parameters:

Name | Type |
------ | ------ |
`params` | [MapboxContainerParams](README.md#mapboxcontainerparams) |
`successCallback` | () => void |
`errorCallback` | (e: string) => void |

**Returns:** void

___

### setDebug

▸ `Const`**setDebug**(`debug`: boolean, `successCallback`: () => void, `errorCallback`: (e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:843](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L843)*

#### Parameters:

Name | Type |
------ | ------ |
`debug` | boolean |
`successCallback` | () => void |
`errorCallback` | (e: string) => void |

**Returns:** void

___

### setGeoJson

▸ `Const`**setGeoJson**(`sourceId`: string, `geoJson`: [SourceData](README.md#sourcedata)<any, any\>, `successCallback`: () => void, `errorCallback`: (e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:1072](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L1072)*

#### Parameters:

Name | Type |
------ | ------ |
`sourceId` | string |
`geoJson` | [SourceData](README.md#sourcedata)<any, any\> |
`successCallback` | () => void |
`errorCallback` | (e: string) => void |

**Returns:** void

___

### setLayoutProperty

▸ `Const`**setLayoutProperty**(`layerId`: string, `name`: [LayoutPropertyName](README.md#layoutpropertyname), `value`: any, `successCallback`: () => void, `errorCallback?`: (\_e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:1024](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L1024)*

#### Parameters:

Name | Type |
------ | ------ |
`layerId` | string |
`name` | [LayoutPropertyName](README.md#layoutpropertyname) |
`value` | any |
`successCallback` | () => void |
`errorCallback?` | (\_e: string) => void |

**Returns:** void

___

### setPitch

▸ `Const`**setPitch**(`pitch`: number, `successCallback`: () => void, `errorCallback`: (e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:1121](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L1121)*

#### Parameters:

Name | Type |
------ | ------ |
`pitch` | number |
`successCallback` | () => void |
`errorCallback` | (e: string) => void |

**Returns:** void

___

### setRandomId

▸ **setRandomId**(): string

*Defined in [cordova-plugin-mapbox.ts:743](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L743)*

**Returns:** string

___

### setZoom

▸ `Const`**setZoom**(`zoom`: number, `successCallback`: () => void, `errorCallback`: (e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:1138](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L1138)*

#### Parameters:

Name | Type |
------ | ------ |
`zoom` | number |
`successCallback` | () => void |
`errorCallback` | (e: string) => void |

**Returns:** void

___

### show

▸ `Const`**show**(`options`: [MapOptions](README.md#mapoptions), `successCallback`: () => void, `errorCallback`: (e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:824](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L824)*

#### Parameters:

Name | Type |
------ | ------ |
`options` | [MapOptions](README.md#mapoptions) |
`successCallback` | () => void |
`errorCallback` | (e: string) => void |

**Returns:** void

___

### toOverlayElement

▸ **toOverlayElement**(`el`: HTMLElement): object

*Defined in [cordova-plugin-mapbox.ts:731](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L731)*

#### Parameters:

Name | Type |
------ | ------ |
`el` | HTMLElement |

**Returns:** object

Name | Type |
------ | ------ |
`id` | string |
`size` | { height: number ; left: number ; top: number ; width: number  } |

___

### zoomTo

▸ `Const`**zoomTo**(`zoom`: number, `successCallback`: () => void, `errorCallback`: (e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:1153](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/796af70/src/js/cordova-plugin-mapbox.ts#L1153)*

#### Parameters:

Name | Type |
------ | ------ |
`zoom` | number |
`successCallback` | () => void |
`errorCallback` | (e: string) => void |

**Returns:** void
