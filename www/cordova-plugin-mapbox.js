"use strict";
/**
 * This file export the plugin interface for Cordova
 * It will be accessible through `window.Mapbox` or directly `Mapbox`
 * It also exports the types of arguments used in the API for development purpose.
 */
var __assign = (this && this.__assign) || function () {
    __assign = Object.assign || function(t) {
        for (var s, i = 1, n = arguments.length; i < n; i++) {
            s = arguments[i];
            for (var p in s) if (Object.prototype.hasOwnProperty.call(s, p))
                t[p] = s[p];
        }
        return t;
    };
    return __assign.apply(this, arguments);
};
exports.__esModule = true;
exports.addOnDidFinishRenderingMapListener = exports.addOnDidFinishLoadingMapListener = exports.addOnDidFinishRenderingFrameListener = exports.addOnWillStartRenderingFrameListener = exports.addOnSourceChangedListener = exports.addOnDidFinishLoadingStyleListener = exports.addOnCameraDidChangeListener = exports.addOnCameraWillChangeListener = exports.addOnWillStartRenderingMapListener = exports.addOnWillStartLoadingMapListener = exports.addOnScaleListener = exports.addOnRotateListener = exports.addOnMoveListener = exports.addOnFlingListener = exports.convertPoint = exports.convertCoordinates = exports.getCameraPosition = exports.getBounds = exports.zoomTo = exports.getZoom = exports.setZoom = exports.getPitch = exports.setPitch = exports.scrollMap = exports.getCenter = exports.setCenter = exports.flyTo = exports.setGeoJson = exports.removeSource = exports.addSource = exports.removeLayer = exports.setLayoutProperty = exports.addLayer = exports.removeImage = exports.addImage = exports.deselect = exports.addMapClickCallback = exports.resumeDownload = exports.pauseDownload = exports.deleteOfflineRegion = exports.getOfflineRegionList = exports.downloadRegion = exports.setContainer = exports.destroy = exports.hide = exports.setClickable = exports.setDebug = exports.show = exports.MapEventType = void 0;
var MapEventType;
(function (MapEventType) {
    MapEventType["OnMoveStart"] = "OnMoveStart";
    MapEventType["OnMove"] = "OnMove";
    MapEventType["OnMoveEnd"] = "OnMoveEnd";
    MapEventType["OnRotateStart"] = "OnRotateStart";
    MapEventType["OnRotate"] = "OnRotate";
    MapEventType["OnRotateEnd"] = "OnRotateEnd";
    MapEventType["OnScaleStart"] = "OnScaleStart";
    MapEventType["OnScale"] = "OnScale";
    MapEventType["OnScaleEnd"] = "OnScaleEnd";
    MapEventType["OnFling"] = "OnFling";
})(MapEventType = exports.MapEventType || (exports.MapEventType = {}));
var cordova = window.cordova;
var MAPBOX = 'Mapbox';
var Command;
(function (Command) {
    Command["ADD_IMAGE"] = "ADD_IMAGE";
    Command["ADD_LAYER"] = "ADD_LAYER";
    Command["ADD_MAP_CLICK_CALLBACK"] = "ADD_MAP_CLICK_CALLBACK";
    Command["ADD_SOURCE"] = "ADD_SOURCE";
    Command["ADD_ON_MOVE_LISTENER"] = "ADD_ON_MOVE_LISTENER";
    Command["ADD_ON_FLING_LISTENER"] = "ADD_ON_FLING_LISTENER";
    Command["ADD_ON_ROTATE_LISTENER"] = "ADD_ON_ROTATE_LISTENER";
    Command["ADD_ON_SCALE_LISTENER"] = "ADD_ON_SCALE_LISTENER";
    Command["ADD_ON_WILL_START_LOADING_MAP_LISTENER"] = "ADD_ON_WILL_START_LOADING_MAP_LISTENER";
    Command["ADD_ON_WILL_START_RENDERING_MAP_LISTENER"] = "ADD_ON_WILL_START_RENDERING_MAP_LISTENER";
    Command["ADD_ON_CAMERA_WILL_CHANGE_LISTENER"] = "ADD_ON_CAMERA_WILL_CHANGE_LISTENER";
    Command["ADD_ON_CAMERA_DID_CHANGE_LISTENER"] = "ADD_ON_CAMERA_DID_CHANGE_LISTENER";
    Command["ADD_ON_DID_FINISH_LOADING_STYLE_LISTENER"] = "ADD_ON_DID_FINISH_LOADING_STYLE_LISTENER";
    Command["ADD_ON_SOURCE_CHANGED_LISTENER"] = "ADD_ON_SOURCE_CHANGED_LISTENER";
    Command["ADD_ON_WILL_START_RENDERING_FRAME_LISTENER"] = "ADD_ON_WILL_START_RENDERING_FRAME_LISTENER";
    Command["ADD_ON_DID_FINISH_RENDERING_FRAME_LISTENER"] = "ADD_ON_DID_FINISH_RENDERING_FRAME_LISTENER";
    Command["ADD_ON_DID_FINISH_LOADING_MAP_LISTENER"] = "ADD_ON_DID_FINISH_LOADING_MAP_LISTENER";
    Command["ADD_ON_DID_FINISH_RENDERING_MAP_LISTENER"] = "ADD_ON_DID_FINISH_RENDERING_MAP_LISTENER";
    Command["CONVERT_COORDINATES"] = "CONVERT_COORDINATES";
    Command["CONVERT_POINT"] = "CONVERT_POINT";
    Command["DELETE_OFFLINE_REGION"] = "DELETE_OFFLINE_REGION";
    Command["DESTROY"] = "DESTROY";
    Command["DOWNLOAD_REGION"] = "DOWNLOAD_REGION";
    Command["FLY_TO"] = "FLY_TO";
    Command["GET_BOUNDS"] = "GET_BOUNDS";
    Command["GET_CAMERA_POSITION"] = "GET_CAMERA_POSITION";
    Command["GET_CENTER"] = "GET_CENTER";
    Command["GET_OFFLINE_REGION_LIST"] = "GET_OFFLINE_REGION_LIST";
    Command["GET_PITCH"] = "GET_PITCH";
    Command["GET_ZOOM"] = "GET_ZOOM";
    Command["HIDE"] = "HIDE";
    Command["PAUSE_DOWNLOAD"] = "PAUSE_DOWNLOAD";
    Command["RESUME_DOWNLOAD"] = "RESUME_DOWNLOAD";
    Command["REMOVE_IMAGE"] = "REMOVE_IMAGE";
    Command["REMOVE_SOURCE"] = "REMOVE_SOURCE";
    Command["REMOVE_LAYER"] = "REMOVE_LAYER";
    Command["RESIZE"] = "RESIZE";
    Command["SCROLL_MAP"] = "SCROLL_MAP";
    Command["SET_CENTER"] = "SET_CENTER";
    Command["SET_CLICKABLE"] = "SET_CLICKABLE";
    Command["SET_CONTAINER"] = "SET_CONTAINER";
    Command["SET_DEBUG"] = "SET_DEBUG";
    Command["SET_GEO_JSON"] = "SET_GEO_JSON";
    Command["SET_LAYOUT_PROPERTY"] = "SET_LAYOUT_PROPERTY";
    Command["SET_PITCH"] = "SET_PITCH";
    Command["SET_ZOOM"] = "SET_ZOOM";
    Command["SHOW"] = "SHOW";
    Command["DESELECT"] = "DESELECT";
    Command["ZOOM_TO"] = "ZOOM_TO";
})(Command || (Command = {}));
function getContainerChildrenOverlayElements(el) {
    var children = getAllChildren(el);
    var elements = [];
    for (var i = 0; i < children.length; i++) {
        elements.push(toOverlayElement(children[i]));
    }
    return elements;
}
function getOverlayElements(el) {
    var children = [el].concat(getAllChildren(el));
    var elements = [];
    for (var i = 0; i < children.length; i++) {
        elements.push(toOverlayElement(children[i]));
    }
    return elements;
}
function toOverlayElement(el) {
    var elemId = el.getAttribute('data-pluginDomId');
    if (!elemId) {
        elemId = setRandomId();
        el.setAttribute('data-pluginDomId', elemId);
    }
    return {
        id: elemId,
        size: getDivRect(el)
    };
}
function setRandomId() {
    return "pmb" + Math.floor(Math.random() * Date.now());
}
function getAllChildren(el) {
    var list = [];
    function crawl(root) {
        var _a;
        var node = root;
        while (node !== null) {
            if (node.nodeType === 1) {
                var style = window.getComputedStyle(node);
                var visibilityCSS = style.getPropertyValue('visibility');
                var displayCSS = style.getPropertyValue('display');
                var opacityCSS = (_a = style.getPropertyValue('opacity')) !== null && _a !== void 0 ? _a : 1;
                if (displayCSS !== 'none'
                    && opacityCSS > 0
                    && visibilityCSS !== 'hidden') {
                    if (node.hasChildNodes()) {
                        list.push(node);
                        list.push.apply(list, getAllChildren(node));
                    }
                    else {
                        list.push(node);
                    }
                }
            }
            node = node.nextSibling;
        }
    }
    crawl(el.firstChild);
    return list;
}
function flatElements(elements) {
    return elements.reduce(function (flatten, el) {
        if (!el)
            return flatten;
        return flatten.concat(getOverlayElements(el));
    }, []);
}
function getDivRect(el) {
    var pageRect = getPageRect();
    var rect = el.getBoundingClientRect();
    return {
        left: rect.left + pageRect.left,
        top: rect.top + pageRect.top,
        width: rect.width,
        height: rect.height
    };
}
function getPageRect() {
    var doc = document.documentElement;
    var pageWidth = window.innerWidth
        || document.documentElement.clientWidth
        || document.body.clientWidth;
    var pageHeight = window.innerHeight
        || document.documentElement.clientHeight
        || document.body.clientHeight;
    var pageLeft = (window.pageXOffset || doc.scrollLeft) - (doc.clientLeft || 0);
    var pageTop = (window.pageYOffset || doc.scrollTop) - (doc.clientTop || 0);
    return {
        width: pageWidth,
        height: pageHeight,
        left: pageLeft,
        top: pageTop
    };
}
var show = function (options, successCallback, errorCallback) {
    var _a;
    var nativeOptions = __assign(__assign({}, options), { HTMLs: getContainerChildrenOverlayElements(options.domContainer), rect: getDivRect(options.domContainer) });
    delete nativeOptions.domContainer; // Prevent circular reference error
    if (nativeOptions.additionalDomElements) {
        (_a = nativeOptions.HTMLs).push.apply(_a, flatElements(options.additionalDomElements));
        delete nativeOptions.additionalDomElements;
    }
    cordova.exec(successCallback, errorCallback, MAPBOX, Command.SHOW, [nativeOptions]);
};
exports.show = show;
var setDebug = function (debug, successCallback, errorCallback) {
    // Convert value to int
    cordova.exec(successCallback, errorCallback, MAPBOX, Command.SET_DEBUG, [
        Number(debug),
    ]);
};
exports.setDebug = setDebug;
var setClickable = function (clickable, successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, MAPBOX, Command.SET_CLICKABLE, [
        clickable,
    ]);
};
exports.setClickable = setClickable;
var hide = function (successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, MAPBOX, Command.HIDE, []);
};
exports.hide = hide;
var destroy = function (successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, MAPBOX, Command.DESTROY, []);
};
exports.destroy = destroy;
var setContainer = function (params, successCallback, errorCallback) {
    var _a;
    var container = {
        HTMLs: getContainerChildrenOverlayElements(params.domContainer),
        rect: getDivRect(params.domContainer)
    };
    if (params.additionalDomElements) {
        (_a = container.HTMLs).push.apply(_a, flatElements(params.additionalDomElements));
    }
    cordova.exec(successCallback, errorCallback, MAPBOX, Command.SET_CONTAINER, [
        container,
    ]);
};
exports.setContainer = setContainer;
var downloadRegion = function (options, statusCallback, errorCallback) {
    cordova.exec(statusCallback, errorCallback, MAPBOX, Command.DOWNLOAD_REGION, [
        options,
    ]);
};
exports.downloadRegion = downloadRegion;
var getOfflineRegionList = function (styleUrl, _successCallback, _errorCallback) {
    var _styleUrl = typeof arguments[0] === 'string' ? arguments[0] : null;
    var successCallback = styleUrl === null ? arguments[0] : arguments[1];
    var errorCallback = styleUrl === null ? arguments[1] : arguments[2];
    var id = styleUrl === null ? arguments[2] || 0 : arguments[3] || 0;
    cordova.exec(successCallback, errorCallback, MAPBOX, Command.GET_OFFLINE_REGION_LIST, [_styleUrl]);
};
exports.getOfflineRegionList = getOfflineRegionList;
var deleteOfflineRegion = function (options, _successCallback, _errorCallback) {
    var _options = typeof arguments[0] === 'object' ? arguments[0] : null;
    var successCallback = options === null ? arguments[0] : arguments[1];
    var errorCallback = options === null ? arguments[1] : arguments[2];
    cordova.exec(successCallback, errorCallback, MAPBOX, Command.DELETE_OFFLINE_REGION, [_options]);
};
exports.deleteOfflineRegion = deleteOfflineRegion;
var pauseDownload = function (options, _successCallback, _errorCallback) {
    var _options = typeof arguments[0] === 'object' ? arguments[0] : null;
    var successCallback = options === null ? arguments[0] : arguments[1];
    var errorCallback = options === null ? arguments[1] : arguments[2];
    cordova.exec(successCallback, errorCallback, MAPBOX, Command.PAUSE_DOWNLOAD, [
        _options,
    ]);
};
exports.pauseDownload = pauseDownload;
var resumeDownload = function (options, _successCallback, _errorCallback) {
    var _options = typeof arguments[0] === 'object' ? arguments[0] : null;
    var successCallback = options === null ? arguments[0] : arguments[1];
    var errorCallback = options === null ? arguments[1] : arguments[2];
    cordova.exec(successCallback, errorCallback, MAPBOX, Command.RESUME_DOWNLOAD, [
        _options,
    ]);
};
exports.resumeDownload = resumeDownload;
var addMapClickCallback = function (callback, errorCallback) {
    cordova.exec(callback, errorCallback, MAPBOX, Command.ADD_MAP_CLICK_CALLBACK, []);
};
exports.addMapClickCallback = addMapClickCallback;
var deselect = function (callback, errorCallback) {
    cordova.exec(callback, errorCallback, MAPBOX, Command.DESELECT, []);
};
exports.deselect = deselect;
var addImage = function (imageId, image, successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, MAPBOX, Command.ADD_IMAGE, [
        imageId,
        image,
    ]);
};
exports.addImage = addImage;
var removeImage = function (imageId, successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, MAPBOX, Command.REMOVE_IMAGE, [
        imageId,
    ]);
};
exports.removeImage = removeImage;
var addLayer = function (layerObject, beforeId, successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, MAPBOX, Command.ADD_LAYER, [
        layerObject,
        beforeId,
    ]);
};
exports.addLayer = addLayer;
var setLayoutProperty = function (layerId, name, value, successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, MAPBOX, Command.SET_LAYOUT_PROPERTY, [layerId, name, value]);
};
exports.setLayoutProperty = setLayoutProperty;
var removeLayer = function (layerId, successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, MAPBOX, Command.REMOVE_LAYER, [
        layerId,
    ]);
};
exports.removeLayer = removeLayer;
var addSource = function (sourceId, source, successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, MAPBOX, Command.ADD_SOURCE, [
        sourceId,
        source,
    ]);
};
exports.addSource = addSource;
var removeSource = function (sourceId, successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, MAPBOX, Command.REMOVE_SOURCE, [
        sourceId,
    ]);
};
exports.removeSource = removeSource;
var setGeoJson = function (sourceId, geoJson, successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, MAPBOX, Command.SET_GEO_JSON, [
        sourceId,
        geoJson,
    ]);
};
exports.setGeoJson = setGeoJson;
var flyTo = function (options, successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, MAPBOX, Command.FLY_TO, [
        options,
    ]);
};
exports.flyTo = flyTo;
var setCenter = function (center, successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, MAPBOX, Command.SET_CENTER, [
        center,
    ]);
};
exports.setCenter = setCenter;
var getCenter = function (resultCallback, errorCallback) {
    cordova.exec(resultCallback, errorCallback, MAPBOX, Command.GET_CENTER, []);
};
exports.getCenter = getCenter;
var scrollMap = function (delta, successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, MAPBOX, Command.SCROLL_MAP, [
        delta,
    ]);
};
exports.scrollMap = scrollMap;
var setPitch = function (pitch, successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, MAPBOX, Command.SET_PITCH, [
        pitch,
    ]);
};
exports.setPitch = setPitch;
var getPitch = function (resultCallback, errorCallback) {
    cordova.exec(resultCallback, errorCallback, MAPBOX, Command.GET_PITCH, []);
};
exports.getPitch = getPitch;
var setZoom = function (zoom, options, successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, MAPBOX, Command.SET_ZOOM, [
        zoom,
        options,
    ]);
};
exports.setZoom = setZoom;
var getZoom = function (resultCallback, errorCallback) {
    cordova.exec(function (value) { return resultCallback(Number(value)); }, errorCallback, MAPBOX, Command.GET_ZOOM, []);
};
exports.getZoom = getZoom;
var zoomTo = function (zoom, options, successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, MAPBOX, Command.ZOOM_TO, [
        zoom,
        options,
    ]);
};
exports.zoomTo = zoomTo;
var getBounds = function (resultCallback, errorCallback) {
    cordova.exec(resultCallback, errorCallback, MAPBOX, Command.GET_BOUNDS, []);
};
exports.getBounds = getBounds;
var getCameraPosition = function (resultCallback, errorCallback) {
    cordova.exec(resultCallback, errorCallback, MAPBOX, Command.GET_CAMERA_POSITION, []);
};
exports.getCameraPosition = getCameraPosition;
var convertCoordinates = function (coords, resultCallback, errorCallback) {
    cordova.exec(resultCallback, errorCallback, MAPBOX, Command.CONVERT_COORDINATES, [coords]);
};
exports.convertCoordinates = convertCoordinates;
var convertPoint = function (point, resultCallback, errorCallback) {
    cordova.exec(resultCallback, errorCallback, MAPBOX, Command.CONVERT_POINT, [
        point,
    ]);
};
exports.convertPoint = convertPoint;
var addOnFlingListener = function (listener, id) {
    if (id === void 0) { id = 0; }
    cordova.exec(listener, null, MAPBOX, Command.ADD_ON_FLING_LISTENER, []);
};
exports.addOnFlingListener = addOnFlingListener;
var addOnMoveListener = function (listener, id) {
    if (id === void 0) { id = 0; }
    cordova.exec(listener, null, MAPBOX, Command.ADD_ON_MOVE_LISTENER, []);
};
exports.addOnMoveListener = addOnMoveListener;
var addOnRotateListener = function (listener, id) {
    if (id === void 0) { id = 0; }
    cordova.exec(listener, null, MAPBOX, Command.ADD_ON_ROTATE_LISTENER, []);
};
exports.addOnRotateListener = addOnRotateListener;
var addOnScaleListener = function (listener, id) {
    if (id === void 0) { id = 0; }
    cordova.exec(listener, null, MAPBOX, Command.ADD_ON_SCALE_LISTENER, []);
};
exports.addOnScaleListener = addOnScaleListener;
/**
 * This event is triggered when the map is about to start loading a new map style.
 */
var addOnWillStartLoadingMapListener = function (listener, id) {
    if (id === void 0) { id = 0; }
    cordova.exec(listener, null, MAPBOX, Command.ADD_ON_WILL_START_LOADING_MAP_LISTENER, []);
};
exports.addOnWillStartLoadingMapListener = addOnWillStartLoadingMapListener;
/**
 * This event is triggered when the map will start rendering the map.
 */
var addOnWillStartRenderingMapListener = function (listener, id) {
    if (id === void 0) { id = 0; }
    cordova.exec(listener, null, MAPBOX, Command.ADD_ON_WILL_START_RENDERING_MAP_LISTENER, []);
};
exports.addOnWillStartRenderingMapListener = addOnWillStartRenderingMapListener;
/**
 * This event is triggered whenever the displayed map region is about to change without animation.
 */
var addOnCameraWillChangeListener = function (listener, id) {
    if (id === void 0) { id = 0; }
    cordova.exec(listener, null, MAPBOX, Command.ADD_ON_CAMERA_WILL_CHANGE_LISTENER, []);
};
exports.addOnCameraWillChangeListener = addOnCameraWillChangeListener;
/**
 * This event is triggered whenever the displayed map region finished changing without an animation.
 */
var addOnCameraDidChangeListener = function (listener, id) {
    if (id === void 0) { id = 0; }
    cordova.exec(listener, null, MAPBOX, Command.ADD_ON_CAMERA_DID_CHANGE_LISTENER, []);
};
exports.addOnCameraDidChangeListener = addOnCameraDidChangeListener;
/**
 * Triggered when a style has finished loading.
 */
var addOnDidFinishLoadingStyleListener = function (listener, id) {
    if (id === void 0) { id = 0; }
    cordova.exec(listener, null, MAPBOX, Command.ADD_ON_DID_FINISH_LOADING_STYLE_LISTENER, []);
};
exports.addOnDidFinishLoadingStyleListener = addOnDidFinishLoadingStyleListener;
/**
 * Triggered when a source changes.
 */
var addOnSourceChangedListener = function (listener, id) {
    if (id === void 0) { id = 0; }
    cordova.exec(listener, null, MAPBOX, Command.ADD_ON_SOURCE_CHANGED_LISTENER, []);
};
exports.addOnSourceChangedListener = addOnSourceChangedListener;
/**
 * This event is triggered when the map will start rendering a frame.
 */
var addOnWillStartRenderingFrameListener = function (listener, id) {
    if (id === void 0) { id = 0; }
    cordova.exec(listener, null, MAPBOX, Command.ADD_ON_WILL_START_RENDERING_FRAME_LISTENER, []);
};
exports.addOnWillStartRenderingFrameListener = addOnWillStartRenderingFrameListener;
/**
 * This event is triggered when the map finished rendering a frame.
 */
var addOnDidFinishRenderingFrameListener = function (listener, id) {
    if (id === void 0) { id = 0; }
    cordova.exec(listener, null, MAPBOX, Command.ADD_ON_DID_FINISH_RENDERING_FRAME_LISTENER, []);
};
exports.addOnDidFinishRenderingFrameListener = addOnDidFinishRenderingFrameListener;
/**
 * This is triggered when the map has successfully loaded a new map style.
 */
var addOnDidFinishLoadingMapListener = function (listener, id) {
    if (id === void 0) { id = 0; }
    cordova.exec(listener, null, MAPBOX, Command.ADD_ON_DID_FINISH_LOADING_MAP_LISTENER, []);
};
exports.addOnDidFinishLoadingMapListener = addOnDidFinishLoadingMapListener;
/**
 * This event is triggered when the map is fully rendered.
 */
function addOnDidFinishRenderingMapListener(listener, id) {
    if (id === void 0) { id = 0; }
    cordova.exec(listener, null, MAPBOX, Command.ADD_ON_DID_FINISH_RENDERING_MAP_LISTENER, []);
}
exports.addOnDidFinishRenderingMapListener = addOnDidFinishRenderingMapListener;
