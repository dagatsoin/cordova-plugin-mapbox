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
* [LayoutProperties](README.md#layoutproperties)
* [LayoutProperty](README.md#layoutproperty)
* [LngLat](README.md#lnglat)
* [MapEventListener](README.md#mapeventlistener)
* [MapEventPayload](README.md#mapeventpayload)
* [MapOptions](README.md#mapoptions)
* [MapboxContainerParams](README.md#mapboxcontainerparams)
* [OfflineRegionParams](README.md#offlineregionparams)
* [PaintProperties](README.md#paintproperties)
* [PickOne](README.md#pickone)
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

*Defined in [cordova-plugin-mapbox.ts:48](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L48)*

#### Type declaration:

Name | Type |
------ | ------ |
`ne` | [number, number] |
`sw` | [number, number] |

___

### CameraOptions

Ƭ  **CameraOptions**: { around: [LngLat](README.md#lnglat) ; bearing: number ; center: [LngLat](README.md#lnglat) ; pitch: number ; zoom: number  }

*Defined in [cordova-plugin-mapbox.ts:89](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L89)*

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

*Defined in [cordova-plugin-mapbox.ts:97](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L97)*

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

*Defined in [cordova-plugin-mapbox.ts:123](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L123)*

#### Type declaration:

Name | Type |
------ | ------ |
`lat` | number |
`lng` | number |

___

### DownloadParams

Ƭ  **DownloadParams**: { bounds: [Bounds](README.md#bounds) ; maxZoom: number ; minZoom: number ; regionName: string ; styleUrl?: string  }

*Defined in [cordova-plugin-mapbox.ts:168](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L168)*

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

*Defined in [cordova-plugin-mapbox.ts:66](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L66)*

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

*Defined in [cordova-plugin-mapbox.ts:64](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L64)*

___

### ImageProperties

Ƭ  **ImageProperties**: { height: number ; path: string ; width: number  }

*Defined in [cordova-plugin-mapbox.ts:139](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L139)*

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

Ƭ  **Layer**: { filter?: [Expression](README.md#expression) ; id: string ; layout?: Partial<[LayoutProperties](README.md#layoutproperties)\> ; maxzoom?: number ; minzoom?: number ; paint?: Partial<[PaintProperties](README.md#paintproperties)\> ; source: string ; sourcelayer?: string ; type?: [LayerType](README.md#layertype)  }

*Defined in [cordova-plugin-mapbox.ts:77](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L77)*

#### Type declaration:

Name | Type |
------ | ------ |
`filter?` | [Expression](README.md#expression) |
`id` | string |
`layout?` | Partial<[LayoutProperties](README.md#layoutproperties)\> |
`maxzoom?` | number |
`minzoom?` | number |
`paint?` | Partial<[PaintProperties](README.md#paintproperties)\> |
`source` | string |
`sourcelayer?` | string |
`type?` | [LayerType](README.md#layertype) |

___

### LayerType

Ƭ  **LayerType**: \"symbol\"

*Defined in [cordova-plugin-mapbox.ts:53](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L53)*

___

### LayoutProperties

Ƭ  **LayoutProperties**: { icon-allow-overlap: boolean ; icon-image: string ; icon-offset: [number, number] ; icon-size: number \| [Expression](README.md#expression) ; text-field: string ; text-font: string[] ; text-size: number \| [Expression](README.md#expression)  }

*Defined in [cordova-plugin-mapbox.ts:27](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L27)*

#### Type declaration:

Name | Type |
------ | ------ |
`icon-allow-overlap` | boolean |
`icon-image` | string |
`icon-offset` | [number, number] |
`icon-size` | number \| [Expression](README.md#expression) |
`text-field` | string |
`text-font` | string[] |
`text-size` | number \| [Expression](README.md#expression) |

___

### LayoutProperty

Ƭ  **LayoutProperty**: [PickOne](README.md#pickone)<[LayoutProperties](README.md#layoutproperties)\>

*Defined in [cordova-plugin-mapbox.ts:37](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L37)*

___

### LngLat

Ƭ  **LngLat**: [number, number]

*Defined in [cordova-plugin-mapbox.ts:121](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L121)*

___

### MapEventListener

Ƭ  **MapEventListener**: (payload: [MapEventPayload](README.md#mapeventpayload)) => void

*Defined in [cordova-plugin-mapbox.ts:46](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L46)*

___

### MapEventPayload

Ƭ  **MapEventPayload**: { latLngBounds: [Bounds](README.md#bounds) ; type: [MapEventType](enums/mapeventtype.md)  }

*Defined in [cordova-plugin-mapbox.ts:20](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L20)*

#### Type declaration:

Name | Type |
------ | ------ |
`latLngBounds` | [Bounds](README.md#bounds) |
`type` | [MapEventType](enums/mapeventtype.md) |

___

### MapOptions

Ƭ  **MapOptions**: { additionalDomElements?: HTMLElement[] ; cameraPosition?: any ; disablePitch?: boolean ; disableRotation?: boolean ; disableScroll?: boolean ; disableTilt?: boolean ; disableZoom?: boolean ; domContainer: HTMLElement ; hideAttribution?: boolean ; hideCompass?: boolean ; hideLogo?: boolean ; selectableFeaturePropType?: string ; selectedFeatureLayerId?: string ; selectedFeatureSourceId?: string ; style: string  }

*Defined in [cordova-plugin-mapbox.ts:145](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L145)*

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

*Defined in [cordova-plugin-mapbox.ts:163](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L163)*

#### Type declaration:

Name | Type |
------ | ------ |
`additionalDomElements?` | HTMLElement[] |
`domContainer` | HTMLElement |

___

### OfflineRegionParams

Ƭ  **OfflineRegionParams**: { regionName?: string ; styleUrl?: string  }

*Defined in [cordova-plugin-mapbox.ts:176](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L176)*

#### Type declaration:

Name | Type |
------ | ------ |
`regionName?` | string |
`styleUrl?` | string |

___

### PaintProperties

Ƭ  **PaintProperties**: { text-color: string \| [Expression](README.md#expression) ; text-halo-blur: number \| [Expression](README.md#expression) ; text-halo-color: string \| [Expression](README.md#expression) ; text-halo-width: number \| [Expression](README.md#expression)  }

*Defined in [cordova-plugin-mapbox.ts:39](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L39)*

#### Type declaration:

Name | Type |
------ | ------ |
`text-color` | string \| [Expression](README.md#expression) |
`text-halo-blur` | number \| [Expression](README.md#expression) |
`text-halo-color` | string \| [Expression](README.md#expression) |
`text-halo-width` | number \| [Expression](README.md#expression) |

___

### PickOne

Ƭ  **PickOne**<T\>: {}[keyof T]

*Defined in [cordova-plugin-mapbox.ts:25](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L25)*

#### Type parameters:

Name |
------ |
`T` |

___

### ScreenCoords

Ƭ  **ScreenCoords**: { x: number ; y: number  }

*Defined in [cordova-plugin-mapbox.ts:128](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L128)*

#### Type declaration:

Name | Type |
------ | ------ |
`x` | number |
`y` | number |

___

### Source

Ƭ  **Source**<T, P\>: { cluster?: boolean ; clusterMaxZoom?: number ; clusterRadius?: number ; data: [SourceData](README.md#sourcedata)<T, P\> ; type: \"geojson\"  }

*Defined in [cordova-plugin-mapbox.ts:113](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L113)*

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

*Defined in [cordova-plugin-mapbox.ts:105](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L105)*

#### Type parameters:

Name | Type |
------ | ------ |
`T` | GeoJSON.GeometryObject |
`P` | - |

## Variables

### MAPBOX

• `Const` **MAPBOX**: \"Mapbox\" = "Mapbox"

*Defined in [cordova-plugin-mapbox.ts:654](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L654)*

___

### cordova

•  **cordova**: Cordova

*Defined in [cordova-plugin-mapbox.ts:652](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L652)*

## Functions

### addImage

▸ `Const`**addImage**(`imageId`: string, `image`: [ImageProperties](README.md#imageproperties), `successCallback`: () => void, `errorCallback`: (e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:988](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L988)*

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

*Defined in [cordova-plugin-mapbox.ts:1010](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L1010)*

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

*Defined in [cordova-plugin-mapbox.ts:973](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L973)*

#### Parameters:

Name | Type |
------ | ------ |
`callback` | (featureCollection: FeatureCollection<GeoJSON.GeometryObject\>) => void |
`errorCallback` | (e: string) => void |

**Returns:** void

___

### addOnCameraDidChangeListener

▸ `Const`**addOnCameraDidChangeListener**(`listener`: () => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:1260](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L1260)*

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

*Defined in [cordova-plugin-mapbox.ts:1247](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L1247)*

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

*Defined in [cordova-plugin-mapbox.ts:1325](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L1325)*

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

*Defined in [cordova-plugin-mapbox.ts:1273](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L1273)*

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

*Defined in [cordova-plugin-mapbox.ts:1312](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L1312)*

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

*Defined in [cordova-plugin-mapbox.ts:1338](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L1338)*

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

*Defined in [cordova-plugin-mapbox.ts:1202](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L1202)*

#### Parameters:

Name | Type | Default value |
------ | ------ | ------ |
`listener` | [MapEventListener](README.md#mapeventlistener) | - |
`id` | number | 0 |

**Returns:** void

___

### addOnMoveListener

▸ `Const`**addOnMoveListener**(`listener`: [MapEventListener](README.md#mapeventlistener), `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:1206](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L1206)*

#### Parameters:

Name | Type | Default value |
------ | ------ | ------ |
`listener` | [MapEventListener](README.md#mapeventlistener) | - |
`id` | number | 0 |

**Returns:** void

___

### addOnRotateListener

▸ `Const`**addOnRotateListener**(`listener`: [MapEventListener](README.md#mapeventlistener), `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:1210](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L1210)*

#### Parameters:

Name | Type | Default value |
------ | ------ | ------ |
`listener` | [MapEventListener](README.md#mapeventlistener) | - |
`id` | number | 0 |

**Returns:** void

___

### addOnScaleListener

▸ `Const`**addOnScaleListener**(`listener`: [MapEventListener](README.md#mapeventlistener), `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:1214](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L1214)*

#### Parameters:

Name | Type | Default value |
------ | ------ | ------ |
`listener` | [MapEventListener](README.md#mapeventlistener) | - |
`id` | number | 0 |

**Returns:** void

___

### addOnSourceChangedListener

▸ `Const`**addOnSourceChangedListener**(`listener`: () => void, `id?`: number): void

*Defined in [cordova-plugin-mapbox.ts:1286](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L1286)*

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

*Defined in [cordova-plugin-mapbox.ts:1221](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L1221)*

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

*Defined in [cordova-plugin-mapbox.ts:1299](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L1299)*

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

*Defined in [cordova-plugin-mapbox.ts:1234](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L1234)*

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

*Defined in [cordova-plugin-mapbox.ts:1047](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L1047)*

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

*Defined in [cordova-plugin-mapbox.ts:1178](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L1178)*

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

*Defined in [cordova-plugin-mapbox.ts:1192](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L1192)*

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

*Defined in [cordova-plugin-mapbox.ts:925](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L925)*

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

*Defined in [cordova-plugin-mapbox.ts:981](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L981)*

#### Parameters:

Name | Type |
------ | ------ |
`callback?` | () => void |
`errorCallback?` | (\_e: string) => void |

**Returns:** void

___

### destroy

▸ `Const`**destroy**(`successCallback`: () => void, `errorCallback`: (e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:869](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L869)*

#### Parameters:

Name | Type |
------ | ------ |
`successCallback` | () => void |
`errorCallback` | (e: string) => void |

**Returns:** void

___

### downloadRegion

▸ `Const`**downloadRegion**(`options`: [DownloadParams](README.md#downloadparams), `statusCallback`: (state: [DownloadState](README.md#downloadstate)) => void, `errorCallback`: (e: string \| { reason: \"REGION\_EXISTS\"  }) => void): void

*Defined in [cordova-plugin-mapbox.ts:895](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L895)*

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

*Defined in [cordova-plugin-mapbox.ts:775](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L775)*

#### Parameters:

Name | Type |
------ | ------ |
`elements` | HTMLElement[] |

**Returns:** any[]

___

### flyTo

▸ `Const`**flyTo**(`options`: Partial<[CameraPosition](README.md#cameraposition)\> & { duration: number  }, `successCallback`: () => void, `errorCallback`: (e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:1081](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L1081)*

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

*Defined in [cordova-plugin-mapbox.ts:745](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L745)*

#### Parameters:

Name | Type |
------ | ------ |
`el` | HTMLElement |

**Returns:** any[]

___

### getBounds

▸ `Const`**getBounds**(`resultCallback`: (\_bounds: [Bounds](README.md#bounds)) => void, `errorCallback`: (\_e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:1158](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L1158)*

#### Parameters:

Name | Type |
------ | ------ |
`resultCallback` | (\_bounds: [Bounds](README.md#bounds)) => void |
`errorCallback` | (\_e: string) => void |

**Returns:** void

___

### getCameraPosition

▸ `Const`**getCameraPosition**(`resultCallback`: (cameraPosition: [CameraPosition](README.md#cameraposition)) => void, `errorCallback`: (\_e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:1165](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L1165)*

#### Parameters:

Name | Type |
------ | ------ |
`resultCallback` | (cameraPosition: [CameraPosition](README.md#cameraposition)) => void |
`errorCallback` | (\_e: string) => void |

**Returns:** void

___

### getCenter

▸ `Const`**getCenter**(`resultCallback`: (center: [Coords](README.md#coords)) => void, `errorCallback`: (\_e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:1101](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L1101)*

#### Parameters:

Name | Type |
------ | ------ |
`resultCallback` | (center: [Coords](README.md#coords)) => void |
`errorCallback` | (\_e: string) => void |

**Returns:** void

___

### getContainerChildrenOverlayElements

▸ **getContainerChildrenOverlayElements**(`el`: HTMLElement): any[]

*Defined in [cordova-plugin-mapbox.ts:709](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L709)*

#### Parameters:

Name | Type |
------ | ------ |
`el` | HTMLElement |

**Returns:** any[]

___

### getDivRect

▸ **getDivRect**(`el`: HTMLElement): object

*Defined in [cordova-plugin-mapbox.ts:782](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L782)*

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

*Defined in [cordova-plugin-mapbox.ts:905](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L905)*

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

*Defined in [cordova-plugin-mapbox.ts:719](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L719)*

#### Parameters:

Name | Type |
------ | ------ |
`el` | HTMLElement |

**Returns:** any[]

___

### getPageRect

▸ **getPageRect**(): object

*Defined in [cordova-plugin-mapbox.ts:801](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L801)*

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

*Defined in [cordova-plugin-mapbox.ts:1128](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L1128)*

#### Parameters:

Name | Type |
------ | ------ |
`resultCallback` | (pitch: number) => void |
`errorCallback` | (e: string) => void |

**Returns:** void

___

### getZoom

▸ `Const`**getZoom**(`resultCallback`: (zoom: number) => void, `errorCallback`: (e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:1143](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L1143)*

#### Parameters:

Name | Type |
------ | ------ |
`resultCallback` | (zoom: number) => void |
`errorCallback` | (e: string) => void |

**Returns:** void

___

### hide

▸ `Const`**hide**(`successCallback?`: () => void, `errorCallback?`: (e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:862](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L862)*

#### Parameters:

Name | Type |
------ | ------ |
`successCallback?` | () => void |
`errorCallback?` | (e: string) => void |

**Returns:** void

___

### pauseDownload

▸ `Const`**pauseDownload**(`options`: [OfflineRegionParams](README.md#offlineregionparams), `_successCallback`: () => void, `_errorCallback`: (e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:944](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L944)*

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

*Defined in [cordova-plugin-mapbox.ts:1000](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L1000)*

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

*Defined in [cordova-plugin-mapbox.ts:1037](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L1037)*

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

*Defined in [cordova-plugin-mapbox.ts:1059](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L1059)*

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

*Defined in [cordova-plugin-mapbox.ts:959](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L959)*

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

*Defined in [cordova-plugin-mapbox.ts:1108](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L1108)*

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

*Defined in [cordova-plugin-mapbox.ts:1091](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L1091)*

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

*Defined in [cordova-plugin-mapbox.ts:852](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L852)*

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

*Defined in [cordova-plugin-mapbox.ts:876](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L876)*

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

*Defined in [cordova-plugin-mapbox.ts:841](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L841)*

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

*Defined in [cordova-plugin-mapbox.ts:1069](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L1069)*

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

▸ `Const`**setLayoutProperty**(`layerId`: string, `property`: [PickOne](README.md#pickone)<[LayoutProperties](README.md#layoutproperties)\>, `successCallback`: () => void, `errorCallback?`: (\_e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:1022](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L1022)*

#### Parameters:

Name | Type |
------ | ------ |
`layerId` | string |
`property` | [PickOne](README.md#pickone)<[LayoutProperties](README.md#layoutproperties)\> |
`successCallback` | () => void |
`errorCallback?` | (\_e: string) => void |

**Returns:** void

___

### setPitch

▸ `Const`**setPitch**(`pitch`: number, `successCallback`: () => void, `errorCallback`: (e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:1118](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L1118)*

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

*Defined in [cordova-plugin-mapbox.ts:741](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L741)*

**Returns:** string

___

### setZoom

▸ `Const`**setZoom**(`zoom`: number, `successCallback`: () => void, `errorCallback`: (e: string) => void): void

*Defined in [cordova-plugin-mapbox.ts:1135](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L1135)*

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

*Defined in [cordova-plugin-mapbox.ts:822](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L822)*

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

*Defined in [cordova-plugin-mapbox.ts:729](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L729)*

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

*Defined in [cordova-plugin-mapbox.ts:1150](https://github.com/dagatsoin/cordova-plugin-mapbox/blob/801e8e0/src/js/cordova-plugin-mapbox.ts#L1150)*

#### Parameters:

Name | Type |
------ | ------ |
`zoom` | number |
`successCallback` | () => void |
`errorCallback` | (e: string) => void |

**Returns:** void
