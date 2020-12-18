/**
 * This file export the plugin interface for Cordova
 * It will be accessible through `window.Mapbox` or directly `Mapbox`
 * It also exports the types of arguments used in the API for development purpose.
 */

export enum MapEventType {
  OnMoveStart = 'OnMoveStart',
  OnMove = 'OnMove',
  OnMoveEnd = 'OnMoveEnd',
  OnRotateStart = 'OnRotateStart',
  OnRotate = 'OnRotate',
  OnRotateEnd = 'OnRotateEnd',
  OnScaleStart = 'OnScaleStart',
  OnScale = 'OnScale',
  OnScaleEnd = 'OnScaleEnd',
  OnFling = 'OnFling'
}

export type MapEventPayload = {
  type: MapEventType,
  latLngBounds: Bounds
}

export type LayoutPropertyName =
| "icon-image"
| "icon-offset"
| "icon-size"
| "icon-allow-overlap"
| "text-field"
| "text-size"
| "text-font"

export type MapEventListener = (payload: MapEventPayload) => void

export type Bounds = {
  sw: [number, number] // [Lng, Lat]
  ne: [number, number] // [Lng, Lat]
}

export type LayerType =
  //  | "fill"
  //  | "line"
  'symbol'
//  | "circle"
//  | "heatmap"
//  | "fill-extrusion"
//  | "raster"
//  | "hillshade"
//  | "background"

export type Expression = Array<string | boolean | number | Expression>

export type DownloadState = {
  regionName: string
  isComplete: boolean
  requiredResourceCount: number
  downloadState: 'STATE_ACTIVE' | 'STATE_INACTIVE'
  completeTileSize: number
  completeTileCount: number
  completeResourceSize: number
  completedResourceCount: number
}

export type Layer = {
  id: string
  source: string
  sourcelayer?: string
  type?: LayerType
  minzoom?: number
  filter?: Expression
  maxzoom?: number
  layout?: Partial<{
    'icon-image': string
    'icon-offset': [number, number]
    'icon-size': number | Expression
    'icon-allow-overlap': boolean
    'text-size': number | Expression
    'text-field': string
    'text-font': string[]
  }>
  paint?: Partial<{
    'text-color': string | Expression
    'text-halo-blur': number | Expression
    'text-halo-color': string | Expression
    'text-halo-width': number | Expression
  }>
}

export type CameraOptions = {
  center: LngLat
  zoom: number
  bearing: number
  pitch: number
  around: LngLat
}

export type CameraPosition = {
  target: Coords,
  zoom: number
  bearing: number
  tilt: number
}

// eslint-disable-next-line no-undef
export type SourceData<T extends GeoJSON.GeometryObject, P> =
  | {}
  // eslint-disable-next-line no-undef
  | GeoJSON.FeatureCollection<T, P>
  // eslint-disable-next-line no-undef
  | GeoJSON.Feature<T, P>

// eslint-disable-next-line no-undef
export type Source<T extends GeoJSON.GeometryObject, P> = {
  type: 'geojson'
  data: SourceData<T, P>
  cluster?: boolean
  clusterMaxZoom?: number
  clusterRadius?: number
}

export type LngLat = [number, number]

export type Coords = {
  lng: number
  lat: number
}

export type ScreenCoords = {
  x: number
  y: number
}

/**
 * The properties of an image used by the Mapbox map.
 * The path is relative to the app assets folder.
 * If you want to access an image from the `www` folder
 * you will set the path value as `www/myImage.png`
 */
export type ImageProperties = {
  height: number
  width: number
  path: string
}

export type MapOptions = {
  domContainer: HTMLElement
  style: string
  additionalDomElements?: HTMLElement[]
  cameraPosition?: any
  selectedFeatureLayerId?: string
  selectedFeatureSourceId?: string
  selectableFeaturePropType?: string
  hideAttribution?: boolean
  hideLogo?: boolean
  hideCompass?: boolean
  disableRotation?: boolean
  disableScroll?: boolean
  disableTilt?: boolean
  disableZoom?: boolean
  disablePitch?: boolean
}

export type MapboxContainerParams = {
  domContainer: HTMLElement
  additionalDomElements?: HTMLElement[]
}

export type DownloadParams = {
  regionName: string,
  styleUrl?: string,
  bounds: Bounds,
  minZoom: number,
  maxZoom: number,
}

export type OfflineRegionParams = {
  styleUrl?: string,
  regionName?: string
}

export interface Mapbox {
  /**
   * Display the map. Create it if needed.
   * @param options 
   * @param successCallback called when the map is displayed
   * @param errorCallback called in case of error
   * @param id the unique id of the map when multi maps is needed. Default to 0.
   */
  show(
    options: MapOptions,
    successCallback?: () => void,
    errorCallback?: (e: string) => void,
    id?: number,
  ): void
  /**
   * Colorize clickable HTML elements with for debug purpose.
   * @param isDebug
   * @param successCallback called on success
   * @param errorCallback called in case of error
   * @param id the unique id of the map when multi maps is needed. Default to 0.
   */
  setDebug(
    isDebug: boolean,
    successCallback?: () => void,
    errorCallback?: (e: string) => void,
    id?: number,
  ): void
  /**
   * Set a map as clickable. If false, the user won't be abble to move the map
   * with gesture.
   * @param isClickable 
   * @param successCallback called on success
   * @param errorCallback called in case of error
   * @param id the unique id of the map when multi maps is needed. Default to 0.
   */
  setClickable(
    isClickable: boolean,
    successCallback?: () => void,
    errorCallback?: (e: string) => void,
    id?: number,
  ): void 
  /**
   * Hide the map. The map is still in memory.
   * @param id the unique id of the map when multi maps is needed. Default to 0.
   * @param successCallback called on success
   * @param errorCallback called in case of error
   */
  hide(
    id?: number,
    successCallback?: () => void,
    errorCallback?: (e: string) => void,
  ): void 
  /**
   * Destroy a map and free the memory.
   * @param id the unique id of the map when multi maps is needed. Default to 0.
   * @param successCallback called on success
   * @param errorCallback called in case of error
   */
  destroy(
    id?: number,
    successCallback?: () => void,
    errorCallback?: (e: string) => void,
  ): void 
  /**
   * Set the HTML container of the Map. It will resize
   * the map to fit in the container and update the clickable
   * HTML elements overlaying the map.
   * @param params 
   * @param successCallback called on success
   * @param errorCallback called in case of error
   * @param id the unique id of the map when multi maps is needed. Default to 0.
   */
  setContainer(
    params: MapboxContainerParams,
    successCallback?: () => void,
    errorCallback?: (e: string) => void,
    id?: number
  ): void
  /**
   * Download a region delimited by [[Bounds]]()
   * @param options 
   * @param successCallback a success callback taking a [[DowlloadState]] in argument
   * @param errorCallback called in case of error
   * @param id the unique id of the map when multi maps is needed. Default to 0.
   */
  downloadRegion(
    options: DownloadParams,
    statusCallback: (state: DownloadState) => void,
    errorCallback?: (e: string | { reason: 'REGION_EXISTS' }) => void,
    id?: number,
  ): void
  /**
   * Get the availble names of offline region.
   * @param styleUrl the Mapbox style URL
   * @param successCallback a callback taking a string Array of region names.
   * @param errorCallback called in case of error
   * @param id the unique id of the map when multi maps is needed. Default to 0.
   */
  getOfflineRegionList(
    styleUrl?: string,
    successCallback?: (regionNames: string[]) => void,
    errorCallback?: (e: string) => void,
    id?: number,
  ): void 
  /**
   * Delete an offline region
   * @param params 
   * @param successCallback a callback taking the result object of the deletion
   * @param errorCallback called in case of error
   * @param id the unique id of the map when multi maps is needed. Default to 0.
   */
  deleteOfflineRegion(
    params?: OfflineRegionParams,
    successCallback?: (result: { isDelete: boolean }) => void,
    errorCallback?: (e: string) => void,
    id?: number,
  ): void
  /**
   * Pause the download of a region
   * @param options 
   * @param successCallback called on success
   * @param errorCallback called in case of error
   * @param id the unique id of the map when multi maps is needed. Default to 0.
   */
  pauseDownload(
    options?: OfflineRegionParams,
    successCallback?: () => void,
    errorCallback?: (e: string) => void,
    id?: number
  ): void
  /**
   * Resume the paused download of a region
   * @param options 
   * @param successCallback called on success
   * @param errorCallback called in case of error
   * @param id the unique id of the map when multi maps is needed. Default to 0.
   */
  resumeDownload(
    options?: OfflineRegionParams,
    successCallback?: () => void,
    errorCallback?: (e: string) => void,
    id?: number,
  ): void
  /**
   * Add a map click callback to the native map.
   * @param callback the click callback taking a collection of JSON feature geometry objects.
   * @param errorCallback called in case of error
   * @param id the unique id of the map when multi maps is needed. Default to 0.
   */
  addMapClickCallback(
    callback?: (featureCollection: GeoJSON.FeatureCollection<GeoJSON.GeometryObject>) => void,
    errorCallback?: (e: string) => void,
    id?: number
  ): void
  /**
   * Deselect the current feature of the map.
   * @param successCallback called on success
   * @param errorCallback called in case of error
   * @param id the unique id of the map when multi maps is needed. Default to 0.
   */
  deselect(
    callback?: () => void,
    errorCallback?: (e: string) => void,
    id?: number,
  ): void
  /**
   * Add an image to the map style. For instance, a marker image.
   * You must add the image before referencing it in a resource.
   * @param imageId 
   * @param image 
   * @param successCallback called on success
   * @param errorCallback called in case of error
   * @param id the unique id of the map when multi maps is needed. Default to 0.
   */
  addImage(
    imageId: string,
    image: ImageProperties,
    successCallback?: () => void,
    errorCallback?: (e: string) => void,
    id?: number,
  ): void
  /**
   * Remove an image from the map style.
   * @param imageId 
   * @param successCallback called on success
   * @param errorCallback called in case of error
   * @param id the unique id of the map when multi maps is needed. Default to 0.
   */
  removeImage(
    imageId: string,
    successCallback?: () => void,
    errorCallback?: (e: string) => void,
    id?: number,
  ): void
  /**
   * Add a layer to the map style
   * @param layer 
   * @param beforeId the layer will be added below
   * @param successCallback called on success
   * @param errorCallback called in case of error
   * @param id the unique id of the map when multi maps is needed. Default to 0.
   */
  addLayer(
    layer: Layer,
    beforeId?: number,
    successCallback?: () => void,
    errorCallback?: (e: string) => void,
    id?: number,
  ): void
  /**
   * Set a layout property for a layer
   * @param layerId 
   * @param name the property name
   * @param value the property value
   * @param successCallback called on success
   * @param errorCallback called in case of error
   * @param id the unique id of the map when multi maps is needed. Default to 0.
   */ 
  setLayoutProperty(
    layerId: string,
    name: LayoutPropertyName,
    value: any,
    successCallback?: () => void,
    errorCallback?: (_e: string) => void,
    id?: number,
  ): void 
  /**
   * Remove a layer from the map style
   * @param layerId 
   * @param successCallback called on success
   * @param errorCallback called in case of error
   * @param id the unique id of the map when multi maps is needed. Default to 0.
   */
  removeLayer(
    layerId: string,
    successCallback?: () => void,
    errorCallback?: (_e: string) => void,
    id?: number,
  ): void
  /**
   * Add a source to the map style
   * @param sourceId 
   * @param source 
   * @param successCallback called on success
   * @param errorCallback called in case of error
   * @param id the unique id of the map when multi maps is needed. Default to 0.
   */
  addSource(
    sourceId: string,
    source: Source<any, any>,
    successCallback?: () => void,
    errorCallback?: (e: string) => void,
    id?: number,
  ): void 
  /**
   * Remove a source from the map style
   * @param sourceId 
   * @param successCallback called on success
   * @param errorCallback called in case of error
   * @param id the unique id of the map when multi maps is needed. Default to 0.
   */
  removeSource(
    sourceId: string,
    successCallback?: () => void,
    errorCallback?: (e: string) => void,
    id?: number,
  ): void
  /**
   * Set the data of a source. Use this when you want to animate data.
   * @param sourceId 
   * @param geoJson 
   * @param successCallback called on success
   * @param errorCallback called in case of error
   * @param id the unique id of the map when multi maps is needed. Default to 0.
   */
  setGeoJson(
    sourceId: string,
    geoJson: SourceData<any, any>,
    successCallback?: () => void,
    errorCallback?: (e: string) => void,
    id?: number,
  ): void
  /**
   * Smoothly jump to a position on the map.
   * @param options 
   * @param successCallback called on success
   * @param errorCallback called in case of error
   * @param id the unique id of the map when multi maps is needed. Default to 0.
   */
  flyTo(
    options: {
      cameraPosition: Partial<CameraPosition> & { duration: number }
    },
    successCallback?: () => void,
    errorCallback?: (e: string) => void,
    id?: number
  ): void
  /**
   * Center the map on another [[LngLat]] coordinates. (not animated)
   * @param center the new coordinautes
   * @param successCallback called when animation start
   * @param errorCallback called in case of error
   * @param id the unique id of the map when multi maps is needed. Default to 0.
   */
  setCenter(
    center: LngLat,
    successCallback?: () => void,
    errorCallback?: (e: string) => void,
    id?: number,
  ): void
  /**
   * Get the [[Coords]] of the map center.
   * @param resultCallback takes a [[Coords]] argument.
   * @param errorCallback called in case of error
   * @param id the unique id of the map when multi maps is needed. Default to 0.
   */
  getCenter(
    resultCallback: (center: Coords) => void,
    errorCallback?: (_e: string) => void,
    id?: number,
  ): void
  /**
   * Animate a camera translation.
   * @param delta 
   * @param successCallback called when animation start
   * @param errorCallback called in case of error
   * @param id the unique id of the map when multi maps is needed. Default to 0.
   */
  scrollMap(
    delta: [number, number],
    successCallback: (center: Coords) => void,
    errorCallback?: (e: string) => void,
    id?: number,
  ): void 
  /**
   * Set the map pitch (not animated)
   * @param pitch 
   * @param successCallback called on success
   * @param errorCallback called in case of error
   * @param id the unique id of the map when multi maps is needed. Default to 0.
   */
  setPitch(
    pitch: number,
    successCallback?: () => void,
    errorCallback?: (e: string) => void,
    id?: number,
  ): void
  /**
   * Get the current map pitch
   * @param resultCallback takes a the current pitch as argument.
   * @param errorCallback called in case of error
   * @param id the unique id of the map when multi maps is needed. Default to 0.
   */
  getPitch(
    resultCallback?: (pitch: number) => void,
    errorCallback?: (e: string) => void,
    id?: number,
  ): void
  /**
   * Set the zoom of the map (not animated)
   * @param zoom 
   * @param options 
   * @param successCallback 
   * @param errorCallback 
   * @param id 
   */
  setZoom(
    zoom: number,
    options?: any,
    successCallback?: () => void,
    errorCallback?: (e: string) => void,
    id?: number,
  ): void
  /**
   * Return the current map zoom
   * @param resultCallback takes a the current zoom as argument.
   * @param errorCallback called in case of error
   * @param id the unique id of the map when multi maps is needed. Default to 0.
   */
  getZoom(
    resultCallback?: (zoom: number) => void,
    errorCallback?: (e: string) => void,
    id? : number,
  ): void
  /**
   * 
   * @param zoom 
   * @param options 
   * @param successCallback called when animation start
   * @param errorCallback called in case of error
   * @param id the unique id of the map when multi maps is needed. Default to 0.
   */
  zoomTo(
    zoom: number,
    options?: any,
    successCallback?: () => void,
    errorCallback?: (e: string) => void,
    id?: number,
  ): void
  /**
   * Get the current map bounds
   * @param resultCallback takes the map [[Bounds]] as argument
   * @param errorCallback called in case of error
   * @param id the unique id of the map when multi maps is needed. Default to 0.
   */
  getBounds(
    resultCallback?: (_bounds: Bounds) => void,
    errorCallback?: (_e: string) => void,
    id?: number
  ): void
  /**
   * Get the current map camera position
   * @param resultCallback takes the map [[CameraPosition]] as argument
   * @param errorCallback called in case of error
   * @param id the unique id of the map when multi maps is needed. Default to 0.
   */
  getCameraPosition(
    resultCallback: (cameraPosition: CameraPosition) => void,
    errorCallback?: (_e: string) => void,
    id?: number,
  ): void
  /**
   * Convert [[Coords]] to [[ScreenCoords]]
   * @param coords 
   * @param resultCallback takes the [[ScreenCoords]] as argument
   * @param errorCallback called in case of error
   * @param id the unique id of the map when multi maps is needed. Default to 0.
   */
  convertCoordinates(
    coords: {
      lat: number
      lng: number
    },
    resultCallback?: (_point: ScreenCoords) => void,
    errorCallback?: (_e: string) => void,
    id?: number,
  ): void
  /**
   * Convert [[ScreenCoords]] to [[Coords]]
   * @param point 
   * @param successCallback takes the [[Coords]] as argument
   * @param errorCallback called in case of error
   * @param id the unique id of the map when multi maps is needed. Default to 0.
   */
  convertPoint(
    point: ScreenCoords,
    successCallback?: (_coords: { lat: number; lng: number }) => void,
    errorCallback?: (_e: string) => void,
    id?: number,
  ): void
  /**
   * Adds a callback that's invoked when the map is flinged.
   * The user performs a bold move gesture and the maps move with more inertia.
   * @param listener 
   * @param id 
   */
  addOnFlingListener(listener: MapEventListener, id?: number): void
  /**
   * Adds a callback that's invoked when the map is moved.
   * Does not fire when the map is flinged
   * @param listener 
   * @param id 
   */
  addOnMoveListener(listener: MapEventListener, id?: number): void
  /**
   * Adds a callback that's invoked when the map is rotated.
   * @param listener 
   * @param id 
   */
  addOnRotateListener(listener: MapEventListener, id?: number): void
  /**
   * Adds a callback that's invoked when the map is scaled.
   * @param listener 
   * @param id 
   */
  addOnScaleListener(listener: MapEventListener, id?: number): void
  /**
   * This event is triggered when the map is about to start loading a new map style.
   * @param listener 
   * @param id 
   */
  addOnWillStartLoadingMapListener(listener: () => void, id?: number): void
  /**
   * This event is triggered when the map will start rendering the map.
   * @param listener 
   * @param id 
   */
  addOnWillStartRenderingMapListener(listener: () => void, id?: number): void
  /**
   * This event is triggered whenever the displayed map region is about to change without animation.
   * @param listener 
   * @param id 
   */
  addOnCameraWillChangeListener(listener: () => void, id?: number): void
  /**
   * This event is triggered whenever the displayed map region finished changing without an animation.
   * @param listener 
   * @param id 
   */
  addOnCameraDidChangeListener(listener: () => void, id?: number): void
  /**
   * Triggered when a style has finished loading.
   * @param listener 
   * @param id 
   */
  addOnDidFinishLoadingStyleListener(listener: () => void, id?: number): void
  /**
   * Triggered when a source changes.
   * @param listener 
   * @param id 
   */
  addOnSourceChangedListener(listener: () => void, id?: number): void
  /**
   * This event is triggered when the map will start rendering a frame. 
   * @param listener 
   * @param id 
   */
  addOnWillStartRenderingFrameListener(listener: () => void, id?: number): void
  /**
   * This event is triggered when the map finished rendering a frame.
   * @param listener 
   * @param id 
   */
  addOnDidFinishRenderingFrameListener(listener: () => void, id?: number): void
  /**
   * This is triggered when the map has successfully loaded a new map style. 
   * @param listener 
   * @param id 
   */
  addOnDidFinishLoadingMapListener(listener: () => void, id?: number): void
  /**
   * This event is triggered when the map is fully rendered.
   * @param listener 
   * @param id 
   */
  addOnDidFinishRenderingMapListener(listener: (fullyRendered: boolean) => void, id?: number): void
}

const { cordova } = window

const MAPBOX = 'Mapbox'

enum Command {
  ADD_IMAGE = 'ADD_IMAGE',
  ADD_LAYER = 'ADD_LAYER',
  ADD_MAP_CLICK_CALLBACK = 'ADD_MAP_CLICK_CALLBACK',
  ADD_SOURCE = 'ADD_SOURCE',
  ADD_ON_MOVE_LISTENER = 'ADD_ON_MOVE_LISTENER',
  ADD_ON_FLING_LISTENER = 'ADD_ON_FLING_LISTENER',
  ADD_ON_ROTATE_LISTENER = 'ADD_ON_ROTATE_LISTENER',
  ADD_ON_SCALE_LISTENER = 'ADD_ON_SCALE_LISTENER',
  ADD_ON_WILL_START_LOADING_MAP_LISTENER = 'ADD_ON_WILL_START_LOADING_MAP_LISTENER',
  ADD_ON_WILL_START_RENDERING_MAP_LISTENER = 'ADD_ON_WILL_START_RENDERING_MAP_LISTENER',
  ADD_ON_CAMERA_WILL_CHANGE_LISTENER = 'ADD_ON_CAMERA_WILL_CHANGE_LISTENER',
  ADD_ON_CAMERA_DID_CHANGE_LISTENER = 'ADD_ON_CAMERA_DID_CHANGE_LISTENER',
  ADD_ON_DID_FINISH_LOADING_STYLE_LISTENER = 'ADD_ON_DID_FINISH_LOADING_STYLE_LISTENER',
  ADD_ON_SOURCE_CHANGED_LISTENER = 'ADD_ON_SOURCE_CHANGED_LISTENER',
  ADD_ON_WILL_START_RENDERING_FRAME_LISTENER = 'ADD_ON_WILL_START_RENDERING_FRAME_LISTENER',
  ADD_ON_DID_FINISH_RENDERING_FRAME_LISTENER = 'ADD_ON_DID_FINISH_RENDERING_FRAME_LISTENER',
  ADD_ON_DID_FINISH_LOADING_MAP_LISTENER = 'ADD_ON_DID_FINISH_LOADING_MAP_LISTENER',
  ADD_ON_DID_FINISH_RENDERING_MAP_LISTENER = 'ADD_ON_DID_FINISH_RENDERING_MAP_LISTENER',
  CONVERT_COORDINATES = 'CONVERT_COORDINATES',
  CONVERT_POINT = 'CONVERT_POINT',
  DELETE_OFFLINE_REGION = 'DELETE_OFFLINE_REGION',
  DESTROY = 'DESTROY',
  DOWNLOAD_REGION = 'DOWNLOAD_REGION',
  FLY_TO = 'FLY_TO',
  GET_BOUNDS = 'GET_BOUNDS',
  GET_CAMERA_POSITION = 'GET_CAMERA_POSITION',
  GET_CENTER = 'GET_CENTER',
  GET_OFFLINE_REGION_LIST = 'GET_OFFLINE_REGION_LIST',
  GET_PITCH = 'GET_PITCH',
  GET_ZOOM = 'GET_ZOOM',
  HIDE = 'HIDE',
  PAUSE_DOWNLOAD = 'PAUSE_DOWNLOAD',
  RESUME_DOWNLOAD = 'RESUME_DOWNLOAD',
  REMOVE_IMAGE = 'REMOVE_IMAGE',
  REMOVE_SOURCE = 'REMOVE_SOURCE',
  REMOVE_LAYER = 'REMOVE_LAYER',
  RESIZE = 'RESIZE',
  SCROLL_MAP = 'SCROLL_MAP',
  SET_CENTER = 'SET_CENTER',
  SET_CLICKABLE = 'SET_CLICKABLE',
  SET_CONTAINER = 'SET_CONTAINER',
  SET_DEBUG = 'SET_DEBUG',
  SET_GEO_JSON = 'SET_GEO_JSON',
  SET_LAYOUT_PROPERTY = 'SET_LAYOUT_PROPERTY',
  SET_PITCH = 'SET_PITCH',
  SET_ZOOM = 'SET_ZOOM',
  SHOW = 'SHOW',
  DESELECT = 'DESELECT',
  ZOOM_TO = 'ZOOM_TO'
}


function getContainerChildrenOverlayElements(el: HTMLElement) {
  const children = getAllChildren(el)
  const elements = []

  for (let i = 0; i < children.length; i++) {
    elements.push(toOverlayElement(children[i]))
  }
  return elements
}

function getOverlayElements(el: HTMLElement) {
  const children = [el].concat(getAllChildren(el))
  const elements = []

  for (let i = 0; i < children.length; i++) {
    elements.push(toOverlayElement(children[i]))
  }
  return elements
}

function toOverlayElement(el: HTMLElement) {
  let elemId = el.getAttribute('data-pluginDomId')
  if (!elemId) {
    elemId = setRandomId()
    el.setAttribute('data-pluginDomId', elemId)
  }
  return {
    id: elemId,
    size: getDivRect(el),
  }
}

function setRandomId() {
  return `pmb${Math.floor(Math.random() * Date.now())}`
}

function getAllChildren(el: HTMLElement) {
  const list = []
  function crawl(root: HTMLElement) {
    let node = root
    while (node !== null) {
      if (node.nodeType === 1) {
        const style = window.getComputedStyle(node)
        const visibilityCSS = style.getPropertyValue('visibility')
        const displayCSS = style.getPropertyValue('display')
        const opacityCSS = style.getPropertyValue('opacity') ?? 1
        if (
          displayCSS !== 'none'
          && opacityCSS > 0
          && visibilityCSS !== 'hidden'
        ) {
          if (node.hasChildNodes()) {
            list.push(node)
            list.push(...getAllChildren(node))
          } else {
            list.push(node)
          }
        }
      }
      node = node.nextSibling as HTMLElement
    }
  }
  crawl(el.firstChild as HTMLElement)
  return list
}

function flatElements(elements: HTMLElement[]) {
  return elements.reduce((flatten, el) => {
    if (!el) return flatten
    return flatten.concat(getOverlayElements(el))
  }, [])
}

function getDivRect(
  el: HTMLElement,
): {
  top: number
  left: number
  width: number
  height: number
} {
  const pageRect = getPageRect()
  const rect = el.getBoundingClientRect()

  return {
    left: rect.left + pageRect.left,
    top: rect.top + pageRect.top,
    width: rect.width,
    height: rect.height,
  }
}

function getPageRect() {
  const doc = document.documentElement

  const pageWidth = window.innerWidth
    || document.documentElement.clientWidth
    || document.body.clientWidth
  const pageHeight = window.innerHeight
    || document.documentElement.clientHeight
    || document.body.clientHeight
  const pageLeft = (window.pageXOffset || doc.scrollLeft) - (doc.clientLeft || 0)
  const pageTop = (window.pageYOffset || doc.scrollTop) - (doc.clientTop || 0)

  return {
    width: pageWidth,
    height: pageHeight,
    left: pageLeft,
    top: pageTop,
  }
}


export const show: Mapbox['show'] = function(
  options,
  successCallback,
  errorCallback,
  id = 0,
): void {
  const nativeOptions = {
    ...options,
    HTMLs: getContainerChildrenOverlayElements(options.domContainer),
    rect: getDivRect(options.domContainer),
  }
  delete nativeOptions.domContainer // Prevent circular reference error

  if (nativeOptions.additionalDomElements) {
    nativeOptions.HTMLs.push(...flatElements(options.additionalDomElements))
    delete nativeOptions.additionalDomElements
  }
  cordova.exec(successCallback, errorCallback, MAPBOX, Command.SHOW, [id, nativeOptions])
}

export const setDebug: Mapbox['setDebug'] = function(
  debug,
  successCallback,
  errorCallback,
  id = 0,
): void {
  // Convert value to int
  cordova.exec(successCallback, errorCallback, MAPBOX, Command.SET_DEBUG, [
    id,
    Number(debug),
  ])
}

export const setClickable: Mapbox['setClickable'] = function(
  clickable,
  successCallback,
  errorCallback,
  id = 0,
): void {
  cordova.exec(successCallback, errorCallback, MAPBOX, Command.SET_CLICKABLE, [
    id,
    clickable,
  ])
}

export const hide: Mapbox['hide'] = function(
  id = 0,
  successCallback?: () => void,
  errorCallback?: (e: string) => void,
): void {
  cordova.exec(successCallback, errorCallback, MAPBOX, Command.HIDE, [id])
}

export const destroy: Mapbox['destroy'] = function(
  id,
  successCallback,
  errorCallback,
): void {
  cordova.exec(successCallback, errorCallback, MAPBOX, Command.DESTROY, [id])
}

export const setContainer: Mapbox['setContainer'] = function(
  params,
  successCallback,
  errorCallback,
  id = 0,
): void {
  const container = {
    HTMLs: getContainerChildrenOverlayElements(params.domContainer),
    rect: getDivRect(params.domContainer),
  }

  if (params.additionalDomElements) {
    container.HTMLs.push(...flatElements(params.additionalDomElements))
  }

  cordova.exec(successCallback, errorCallback, MAPBOX, Command.SET_CONTAINER, [
    id,
    container,
  ])
}

export const downloadRegion: Mapbox['downloadRegion'] = function(
  options,
  statusCallback,
  errorCallback,
  id = 0,
): void {
  cordova.exec(statusCallback, errorCallback, MAPBOX, Command.DOWNLOAD_REGION, [
    id,
    options,
  ])
}

export const getOfflineRegionList: Mapbox['getOfflineRegionList'] = function(
  styleUrl,
  _successCallback,
  _errorCallback,
  _id = 0,
): void {
  const _styleUrl = typeof arguments[0] === 'string' ? arguments[0] : null
  const successCallback = styleUrl === null ? arguments[0] : arguments[1]
  const errorCallback = styleUrl === null ? arguments[1] : arguments[2]
  const id = styleUrl === null ? arguments[2] || 0 : arguments[3] || 0

  cordova.exec(
    successCallback,
    errorCallback,
    MAPBOX,
    Command.GET_OFFLINE_REGION_LIST,
    [id, _styleUrl],
  )
}

export const deleteOfflineRegion: Mapbox['deleteOfflineRegion'] = function(
  options,
  _successCallback,
  _errorCallback,
  _id = 0,
): void {
  const _options = typeof arguments[0] === 'object' ? arguments[0] : null
  const successCallback = options === null ? arguments[0] : arguments[1]
  const errorCallback = options === null ? arguments[1] : arguments[2]
  const id = options === null ? arguments[2] || 0 : arguments[3] || 0

  cordova.exec(
    successCallback,
    errorCallback,
    MAPBOX,
    Command.DELETE_OFFLINE_REGION,
    [id, _options],
  )
}

export const pauseDownload: Mapbox['pauseDownload'] = function(
  options,
  _successCallback,
  _errorCallback,
  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  _id = 0,
): void {
  const _options = typeof arguments[0] === 'object' ? arguments[0] : null
  const successCallback = options === null ? arguments[0] : arguments[1]
  const errorCallback = options === null ? arguments[1] : arguments[2]
  const id = options === null ? arguments[2] || 0 : arguments[3] || 0

  cordova.exec(successCallback, errorCallback, MAPBOX, Command.PAUSE_DOWNLOAD, [
    id,
    _options,
  ])
}


export const resumeDownload: Mapbox['resumeDownload'] = function(
  options,
  _successCallback,
  _errorCallback,
  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  _id = 0,
): void {
  const _options = typeof arguments[0] === 'object' ? arguments[0] : null
  const successCallback = options === null ? arguments[0] : arguments[1]
  const errorCallback = options === null ? arguments[1] : arguments[2]
  const id = options === null ? arguments[2] || 0 : arguments[3] || 0

  cordova.exec(successCallback, errorCallback, MAPBOX, Command.RESUME_DOWNLOAD, [
    id,
    _options,
  ])
}

export const addMapClickCallback: Mapbox['addMapClickCallback'] = function(
  callback,
  errorCallback,
  id = 0,
): void {
  cordova.exec(callback, errorCallback, MAPBOX, Command.ADD_MAP_CLICK_CALLBACK, [
    id,
  ])
}

export const deselect: Mapbox['deselect'] = function(
  callback?: () => void,
  errorCallback?: (_e: string) => void,
  id = 0,
): void {
  cordova.exec(callback, errorCallback, MAPBOX, Command.DESELECT, [id])
}

export const addImage: Mapbox['addImage'] = function(
  imageId,
  image,
  successCallback,
  errorCallback,
  id = 0,
): void {
  cordova.exec(successCallback, errorCallback, MAPBOX, Command.ADD_IMAGE, [
    id,
    imageId,
    image,
  ])
}

export const removeImage: Mapbox['removeImage'] = function(
  imageId,
  successCallback,
  errorCallback,
  id = 0,
): void {
  cordova.exec(successCallback, errorCallback, MAPBOX, Command.REMOVE_IMAGE, [
    id,
    imageId,
  ])
}

export const addLayer: Mapbox['addLayer'] = function(
  layerObject,
  beforeId,
  successCallback,
  errorCallback,
  id = 0,
): void {
  cordova.exec(successCallback, errorCallback, MAPBOX, Command.ADD_LAYER, [
    id,
    layerObject,
    beforeId,
  ])
}

export const setLayoutProperty: Mapbox['setLayoutProperty'] = function(
  layerId,
  name,
  value,
  successCallback,
  errorCallback?,
  id = 0,
): void {
  cordova.exec(
    successCallback,
    errorCallback,
    MAPBOX,
    Command.SET_LAYOUT_PROPERTY,
    [id, layerId, name, value],
  )
}

export const removeLayer: Mapbox['removeLayer'] = function(
  layerId,
  successCallback,
  errorCallback,
  id = 0,
): void {
  cordova.exec(successCallback, errorCallback, MAPBOX, Command.REMOVE_LAYER, [
    id,
    layerId,
  ])
}

export const addSource: Mapbox['addSource'] = function(
  sourceId,
  source,
  successCallback,
  errorCallback,
  id = 0,
): void {
  cordova.exec(successCallback, errorCallback, MAPBOX, Command.ADD_SOURCE, [
    id,
    sourceId,
    source,
  ])
}

export const removeSource: Mapbox['removeSource'] = function(
  sourceId,
  successCallback,
  errorCallback,
  id = 0,
): void {
  cordova.exec(successCallback, errorCallback, MAPBOX, Command.REMOVE_SOURCE, [
    id,
    sourceId,
  ])
}

export const setGeoJson: Mapbox['setGeoJson'] = function(
  sourceId,
  geoJson,
  successCallback,
  errorCallback,
  id = 0,
): void {
  cordova.exec(successCallback, errorCallback, MAPBOX, Command.SET_GEO_JSON, [
    id,
    sourceId,
    geoJson,
  ])
}

export const flyTo: Mapbox['flyTo'] =  function(
  options,
  successCallback,
  errorCallback,
  id = 0,
): void {
  cordova.exec(successCallback, errorCallback, MAPBOX, Command.FLY_TO, [
    id,
    options,
  ])
}

export const setCenter: Mapbox['setCenter'] = function(
  center,
  successCallback,
  errorCallback,
  id = 0,
): void {
  cordova.exec(successCallback, errorCallback, MAPBOX, Command.SET_CENTER, [
    id,
    center,
  ])
}

export const getCenter: Mapbox['getCenter'] = function(
  resultCallback,
  errorCallback,
  id = 0,
): void {
  cordova.exec(resultCallback, errorCallback, MAPBOX, Command.GET_CENTER, [id])
}

export const scrollMap: Mapbox['scrollMap'] = function(
  delta: [number, number],
  successCallback,
  errorCallback,
  id = 0,
): void {
  cordova.exec(successCallback, errorCallback, MAPBOX, Command.SCROLL_MAP, [
    id,
    delta,
  ])
}

export const setPitch: Mapbox['setPitch'] = function(
  pitch,
  successCallback,
  errorCallback,
  id = 0,
): void {
  cordova.exec(successCallback, errorCallback, MAPBOX, Command.SET_PITCH, [
    id,
    pitch,
  ])
}

export const getPitch: Mapbox['getPitch'] = function(
  resultCallback,
  errorCallback,
  id = 0,
): void {
  cordova.exec(resultCallback, errorCallback, MAPBOX, Command.GET_PITCH, [id])
}

export const setZoom: Mapbox['setZoom'] = function(
  zoom,
  options,
  successCallback,
  errorCallback,
  id = 0,
): void {
  cordova.exec(successCallback, errorCallback, MAPBOX, Command.SET_ZOOM, [
    id,
    zoom,
    options,
  ])
}

export const getZoom: Mapbox['getZoom'] = function(
  resultCallback,
  errorCallback,
  id = 0,
): void {
  cordova.exec(resultCallback, errorCallback, MAPBOX, Command.GET_ZOOM, [id])
}

export const zoomTo: Mapbox['zoomTo'] = function(
  zoom,
  options,
  successCallback,
  errorCallback,
  id = 0,
): void {
  cordova.exec(successCallback, errorCallback, MAPBOX, Command.ZOOM_TO, [
    id,
    zoom,
    options,
  ])
}

export const getBounds: Mapbox['getBounds'] = function(
  resultCallback,
  errorCallback,
  id = 0,
): void {
  cordova.exec(resultCallback, errorCallback, MAPBOX, Command.GET_BOUNDS, [id])
}

export const getCameraPosition: Mapbox['getCameraPosition'] = function(
  resultCallback,
  errorCallback,
  id = 0,
): void {
  cordova.exec(
    resultCallback,
    errorCallback,
    MAPBOX,
    Command.GET_CAMERA_POSITION,
    [id],
  )
}

export const convertCoordinates: Mapbox['convertCoordinates'] = function(
  coords,
  resultCallback,
  errorCallback,
  id = 0,
): void {
  cordova.exec(
    resultCallback,
    errorCallback,
    MAPBOX,
    Command.CONVERT_COORDINATES,
    [id, coords],
  )
}

export const convertPoint: Mapbox['convertPoint'] = function(
  point,
  resultCallback,
  errorCallback,
  id = 0,
): void {
  cordova.exec(resultCallback, errorCallback, MAPBOX, Command.CONVERT_POINT, [
    id,
    point,
  ])
}

export const addOnFlingListener: Mapbox['addOnFlingListener'] = function (listener, id = 0) {
  cordova.exec(listener, null, MAPBOX, Command.ADD_ON_FLING_LISTENER, [id])
}

export const addOnMoveListener: Mapbox['addOnMoveListener'] = function (listener, id = 0) {
  cordova.exec(listener, null, MAPBOX, Command.ADD_ON_MOVE_LISTENER, [id])
}

export const addOnRotateListener: Mapbox['addOnRotateListener'] = function (listener, id = 0) {
  cordova.exec(listener, null, MAPBOX, Command.ADD_ON_ROTATE_LISTENER, [id])
}

export const addOnScaleListener: Mapbox['addOnScaleListener'] = function (listener, id = 0) {
  cordova.exec(listener, null, MAPBOX, Command.ADD_ON_SCALE_LISTENER, [id])
}

/**
 * This event is triggered when the map is about to start loading a new map style.
 */
export const addOnWillStartLoadingMapListener: Mapbox['addOnWillStartLoadingMapListener'] = function(listener, id = 0) {
  cordova.exec(
    listener,
    null,
    MAPBOX,
    Command.ADD_ON_WILL_START_LOADING_MAP_LISTENER,
    [id],
  )
}

/**
 * This event is triggered when the map will start rendering the map.
 */
export const addOnWillStartRenderingMapListener: Mapbox['addOnWillStartRenderingMapListener'] = function(listener, id = 0) {
  cordova.exec(
    listener,
    null,
    MAPBOX,
    Command.ADD_ON_WILL_START_RENDERING_MAP_LISTENER,
    [id],
  )
}

/**
 * This event is triggered whenever the displayed map region is about to change without animation.
 */
export const addOnCameraWillChangeListener: Mapbox['addOnCameraWillChangeListener'] = function(listener, id = 0) {
  cordova.exec(
    listener,
    null,
    MAPBOX,
    Command.ADD_ON_CAMERA_WILL_CHANGE_LISTENER,
    [id],
  )
}

/**
 * This event is triggered whenever the displayed map region finished changing without an animation.
 */
export const addOnCameraDidChangeListener: Mapbox['addOnCameraDidChangeListener'] = function(listener, id = 0) {
  cordova.exec(
    listener,
    null,
    MAPBOX,
    Command.ADD_ON_CAMERA_DID_CHANGE_LISTENER,
    [id],
  )
}

/**
 * Triggered when a style has finished loading.
 */
export const addOnDidFinishLoadingStyleListener: Mapbox['addOnDidFinishLoadingStyleListener'] = function(listener, id = 0) {
  cordova.exec(
    listener,
    null,
    MAPBOX,
    Command.ADD_ON_DID_FINISH_LOADING_STYLE_LISTENER,
    [id],
  )
}

/**
 * Triggered when a source changes.
 */
export const addOnSourceChangedListener: Mapbox['addOnSourceChangedListener'] = function(listener, id = 0) {
  cordova.exec(
    listener,
    null,
    MAPBOX,
    Command.ADD_ON_SOURCE_CHANGED_LISTENER,
    [id],
  )
}

/**
 * This event is triggered when the map will start rendering a frame.
 */
export const addOnWillStartRenderingFrameListener: Mapbox['addOnWillStartRenderingFrameListener'] = function(listener, id = 0) {
  cordova.exec(
    listener,
    null,
    MAPBOX,
    Command.ADD_ON_WILL_START_RENDERING_FRAME_LISTENER,
    [id],
  )
}

/**
 * This event is triggered when the map finished rendering a frame.
 */
export const addOnDidFinishRenderingFrameListener: Mapbox['addOnDidFinishRenderingFrameListener'] = function(listener, id = 0) {
  cordova.exec(
    listener,
    null,
    MAPBOX,
    Command.ADD_ON_DID_FINISH_RENDERING_FRAME_LISTENER,
    [id],
  )
}

/**
 * This is triggered when the map has successfully loaded a new map style.
 */
export const addOnDidFinishLoadingMapListener: Mapbox['addOnDidFinishLoadingMapListener'] = function(listener, id = 0) {
  cordova.exec(
    listener,
    null,
    MAPBOX,
    Command.ADD_ON_DID_FINISH_LOADING_MAP_LISTENER,
    [id],
  )
}

/**
 * This event is triggered when the map is fully rendered.
 */
export function addOnDidFinishRenderingMapListener(listener: (fully: boolean) => void, id = 0) {
  cordova.exec(
    listener,
    null,
    MAPBOX,
    Command.ADD_ON_DID_FINISH_RENDERING_MAP_LISTENER,
    [id],
  )
}