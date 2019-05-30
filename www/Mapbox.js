function getContainerChildrenOverlayElements(mapDiv) {
    var children = getAllChildren(mapDiv);
    var elements = [];

    for (var i = 0; i < children.length; i++) {
        elements.push(toOverlayElement(children[i]));
    }
    return elements;
}

function getOverlayElements(element) {
    var children = [element].concat(getAllChildren(element));
    var elements = [];

    for (var i = 0; i < children.length; i++) {
        elements.push(toOverlayElement(children[i], false));
    }
    return elements;
}

function toOverlayElement(elem) {
    var elemId = elem.getAttribute("data-pluginDomId");
    if (!elemId) {
        elemId = setRandomId();
        elem.setAttribute("data-pluginDomId", elemId);
    }
    return {
        id: elemId,
        size: getDivRect(elem)
    }
}

function setRandomId() {
    return "pmb" + Math.floor(Math.random() * Date.now());
}

function getAllChildren(root) {
    var list = [];
    function crawl(node) {
        while (node !== null) {
            if (node.nodeType === 1) {
                var style, displayCSS, opacityCSS, visibilityCSS;
                style = window.getComputedStyle(node);
                visibilityCSS = style.getPropertyValue('visibility');
                displayCSS = style.getPropertyValue('display');
                opacityCSS = style.getPropertyValue('opacity');
                if (displayCSS !== "none" && opacityCSS > 0 && visibilityCSS != "hidden") {
                    if (node.hasChildNodes()) {
                        list.push(node);
                        list.push(...getAllChildren(node));
                    } else {
                        list.push(node);
                    }
                }
            }
            node = node.nextSibling;
        }
    }
    crawl(root.firstChild);
    return list;
}


function getDivRect(div) {
    if (!div) {
        return;
    }

    var pageRect = getPageRect();

    var rect = div.getBoundingClientRect();
    return {
        'left': rect.left + pageRect.left,
        'top': rect.top + pageRect.top,
        'width': rect.width,
        'height': rect.height
    };
}

function getPageRect() {
    var doc = document.documentElement;

    var pageWidth = window.innerWidth ||
            document.documentElement.clientWidth ||
            document.body.clientWidth,
        pageHeight = window.innerHeight ||
            document.documentElement.clientHeight ||
            document.body.clientHeight;
    var pageLeft = (window.pageXOffset || doc.scrollLeft) - (doc.clientLeft || 0);
    var pageTop = (window.pageYOffset || doc.scrollTop) - (doc.clientTop || 0);

    return {
        'width': pageWidth,
        'height': pageHeight,
        'left': pageLeft,
        'top': pageTop
    };
}

module.exports = {
     //todo
     /*
     addToggleTrackingCallback: function (callback, errorCallback, id) {
             id = id || 0;
             cordova.exec(callback, errorCallback, "Mapbox", "toggleTracking", [id])
         },

*/

    show: function (options, successCallback, errorCallback, id) {
        id = id || 0;
        options.HTMLs = getContainerChildrenOverlayElements(options.domContainer);
        options.rect = getDivRect(options.domContainer);
        delete options.domContainer; //Prevent circular reference error

        if (options.additionalDomElements) {
            options.HTMLs = options.HTMLs.concat(
                options.additionalDomElements.reduce(
                    function (flatten, el) {
                        return flatten.concat(getOverlayElements(el))
                    },
                    []
                )
            );
            delete options.additionalDomElements; //Prevent circular reference error
        }
        cordova.exec(successCallback, errorCallback, "Mapbox", "SHOW", [id, options]);
    },

    setDebug: function (debug, successCallback, errorCallback, id) {
        id = id || 0;
        cordova.exec(successCallback, errorCallback, "Mapbox", "SET_DEBUG", [id, debug])
    },

    setClickable: function (clickable, successCallback, errorCallback, id) {
        id = id || 0;
        cordova.exec(successCallback, errorCallback, "Mapbox", "SET_CLICKABLE", [id, clickable])
    },

    hide: function (id, successCallback, errorCallback) {
        id = id || 0;
        cordova.exec(successCallback, errorCallback, "Mapbox", "HIDE", [id]);
    },

    destroy: function (id, successCallback, errorCallback) {
        id = id || 0;
        cordova.exec(successCallback, errorCallback, "Mapbox", "DESTROY", [id]);
    },

    setContainer: function (container, successCallback, errorCallback, id) {
        id = id || 0;
        container.HTMLs = getContainerChildrenOverlayElements(container.domContainer);
        container.rect = getDivRect(container.domContainer);
        delete container.domContainer; //Prevent circular reference error
        
        if (container.additionalDomElements) {
            container.HTMLs = container.HTMLs.concat(
                container.additionalDomElements.reduce(
                    function (flatten, el) {
                        return flatten.concat(getOverlayElements(el))
                    },
                    []
                )
            );
            delete container.additionalDomElements; //Prevent circular reference error
        }

        cordova.exec(successCallback, errorCallback, "Mapbox", "SET_CONTAINER", [id, container])
    },

    downloadCurrentMap: function (id, statusCallback, errorCallback) {
        id = id || 0;
        cordova.exec(statusCallback, errorCallback, "Mapbox", "DOWNLOAD_CURRENT_MAP", [id]);
    },

    getOfflineRegionsList: function (id, successCallback, errorCallback) {
        id = id || 0;
        cordova.exec(successCallback, errorCallback, "Mapbox", "GET_OFFLINE_REGIONS_LIST", [id]);
    },

    deleteOfflineRegion: function (id, zoneId, successCallback, errorCallback) {
        id = id || 0;
        cordova.exec(successCallback, errorCallback, "Mapbox", "DELETE_OFFLINE_REGION", [id, zoneId]);
    },

    pauseDownload: function (id, successCallback, errorCallback) {
        id = id || 0;
        cordova.exec(successCallback, errorCallback, "Mapbox", "PAUSE_DOWNLOAD", [id]);
    },

    addMarkerCallback: function (id, callback, errorCallback) {
        id = id || 0;
        cordova.exec(callback, errorCallback, "Mapbox", "ADD_MARKER_CALLBACK", [id]);
    },

    //only handle marker for now
    addSource: function (sourceId, source, successCallback, errorCallback, id) {
        id = id || 0;
        cordova.exec(successCallback, errorCallback, "Mapbox", "ADD_MARKER", [id, sourceId, source]);
    },

    setMarkerLngLat: function (sourceId, coordinates, successCallback, errorCallback, id) {
        id = id || 0;
        cordova.exec(successCallback, errorCallback, "Mapbox", "MARKER__SET_LNG_LAT", [id, sourceId, coordinates])
    },

    setMarkerIcon: function (sourceId, imageProperties, successCallback, errorCallback, id) {
        id = id || 0;
        cordova.exec(successCallback, errorCallback, "Mapbox", "MARKER__SET_ICON", [id, sourceId, imageProperties])
    },

    removeSource: function (sourceId, successCallback, errorCallback, id) {
        id = id || 0;
        cordova.exec(successCallback, errorCallback, "Mapbox", "REMOVE_MARKER", [id, sourceId])
    },

    flyTo: function (options, successCallback, errorCallback, id) {
        id = id || 0;
        cordova.exec(successCallback, errorCallback, "Mapbox", "FLY_TO", [id, options]);
    },

    setCenter: function (center, successCallback, errorCallback, id) {
        id = id || 0;
        cordova.exec(successCallback, errorCallback, "Mapbox", "SET_CENTER", [id, center]);
    },

    getCenter: function (successCallback, errorCallback, id) {
        id = id || 0;
        cordova.exec(successCallback, errorCallback, "Mapbox", "GET_CENTER", [id]);
    },

    getNextPositions: function (delta, successCallback, errorCallback, id) {
        id = id || 0;
        cordova.exec(successCallback, errorCallback, "Mapbox", "NEXT_MARKERS_POSITIONS_PREDICATE", [id, delta]);
    },

    getMarkersPositions: function (successCallback, errorCallback, id) {
        id = id || 0;
        cordova.exec(successCallback, errorCallback, "Mapbox", "GET_MARKERS_POSITIONS", [id]);
    },

    scrollMap: function (delta, successCallback, errorCallback, id) {
        id = id || 0;
        cordova.exec(successCallback, errorCallback, "Mapbox", "SCROLL_MAP", [id, delta]);
    },

    setPitch: function (pitch, successCallback, errorCallback, id) {
        id = id || 0;
        cordova.exec(successCallback, errorCallback, "Mapbox", "SET_PITCH", [id, pitch]);
    },

    getPitch: function (successCallback, errorCallback, id) {
        id = id || 0;
        cordova.exec(successCallback, errorCallback, "Mapbox", "GET_PITCH", [id]);
    },

    getZoom: function (successCallback, errorCallback, id) {
        id = id || 0;
        cordova.exec(successCallback, errorCallback, "Mapbox", "GET_ZOOM", [id]);
    },

    setZoom: function (zoom, options, successCallback, errorCallback, id) {
        id = id || 0;
        cordova.exec(successCallback, errorCallback, "Mapbox", "SET_ZOOM", [id, zoom, options]);
    },

    zoomTo: function (zoom, options, successCallback, errorCallback, id) {
        id = id || 0;
        cordova.exec(successCallback, errorCallback, "Mapbox", "ZOOM_TO", [id, zoom, options]);
    },

    getBounds: function (successCallback, errorCallback, id) {
        id = id || 0;
        cordova.exec(successCallback, errorCallback, "Mapbox", "GET_BOUNDS", [id]);
    },

    getCameraPosition: function (successCallback, errorCallback, id) {
        id = id || 0;
        cordova.exec(successCallback, errorCallback, "Mapbox", "GET_CAMERA_POSITION", [id]);
    },

    convertCoordinates: function (coords, successCallback, errorCallback, id) {
        id = id || 0;
        cordova.exec(successCallback, errorCallback, "Mapbox", "CONVERT_COORDINATES", [id, coords]);
    },

    convertPoint: function (point, successCallback, errorCallback, id) {
        id = id || 0;
        cordova.exec(successCallback, errorCallback, "Mapbox", "CONVERT_POINT", [id, point]);
    },

    /**
     * This event is triggered when the map is about to start loading a new map style.
     */
    addOnWillStartLoadingMapListener: function(callback, id) {
        id = id || 0;
        cordova.exec(callback, null, "Mapbox", "ADD_ON_WILL_START_LOADING_MAP_LISTENER", [id])
    },

    /**
     * This event is triggered when the map will start rendering the map.
     */
    addOnWillStartRenderingMapListener: function(callback, id) {
        id = id || 0;
        cordova.exec(callback, null, "Mapbox", "ADD_ON_WILL_START_RENDERING_MAP_LISTENER", [id])
    },

    /**
     * This event is triggered whenever the displayed map region is about to change without an animation.
     */
    addOnCameraWillChangeListener: function(callback, id) {
        id = id || 0;
        cordova.exec(callback, null, "Mapbox", "ADD_ON_CAMERA_WILL_CHANGE_LISTENER", [id])
    },

    /**
     * This event is triggered whenever the displayed map region finished changing without an animation.
     */
    addOnCameraDidChangeListener: function(callback, id) {
        id = id || 0;
        cordova.exec(callback, null, "Mapbox", "ADD_ON_CAMERA_DID_CHANGE_LISTENER", [id])
    },

    /**
     * Triggered when a style has finished loading.
     */
    addOnDidFinishLoadingStyleListener: function(callback, id) {
        id = id || 0;
        cordova.exec(callback, null, "Mapbox", "ADD_ON_DID_FINISH_LOADING_STYLE_LISTENER", [id])
    },


    /**
     * Triggered when a source changes.
     */
    addOnSourceChangedListener: function(callback, id) {
        id = id || 0;
        cordova.exec(callback, null, "Mapbox", "ADD_ON_SOURCE_CHANGED_LISTENER", [id])
    },

    /**
     * This event is triggered when the map will start rendering a frame.
     */
    addOnWillStartRenderingFrameListener: function(callback, id) {
        id = id || 0;
        cordova.exec(callback, null, "Mapbox", "ADD_ON_WILL_START_RENDERING_FRAME_LISTENER", [id])
    },

    /**
     * This event is triggered when the map finished rendering a frame.
     */
    addOnDidFinishRenderingFrameListener: function(callback, id) {
        id = id || 0;
        cordova.exec(callback, null, "Mapbox", "ADD_ON_DID_FINISH_RENDERING_FRAME_LISTENER", [id])
    },

    /**
     * This is triggered when the map has successfully loaded a new map style.
     */
    addOnDidFinishLoadingMapListener: function(callback, id) {
        id = id || 0;
        cordova.exec(callback, null, "Mapbox", "ADD_ON_DID_FINISH_LOADING_MAP_LISTENER", [id])
    },

    /**
     * This event is triggered when the map is fully rendered.
     */
    addOnDidFinishRenderingMapListener: function(callback, id) {
        id = id || 0;
        cordova.exec(callback, null, "Mapbox", "ADD_ON_DID_FINISH_RENDERING_MAP_LISTENER", [id])
    }
};