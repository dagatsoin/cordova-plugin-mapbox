package com.dagatsoin.plugins.mapbox;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.util.Base64;
import android.view.animation.BounceInterpolator;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.annotation.Symbol;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

class BouncingSymbol {
    private Activity activity;
    private boolean isSelected;
    private String id;
    private LatLng latLng;
    private Symbol symbol;
    private String iconFilePath;
    private Style style;
    private SymbolManager symbolManager;
    private static final int DEFAULT_BOUNCE_HEIGHT = -10;
    private static final long DEFAULT_ANIMATION_DURATION = 800;
    private TimeInterpolator interpolator = new BounceInterpolator();
    private ValueAnimator animator;
    private boolean animationHasStarted;
    private float retinaFactor = Resources.getSystem().getDisplayMetrics().density;

    BouncingSymbol(
            String _id,
            LatLng _latLng,
            JSONObject properties,
            Activity _activity,
            Style _style,
            SymbolManager _symbolManager
    ) {
        id = _id;
        activity = _activity;
        style = _style;
        symbolManager = _symbolManager;

        SymbolOptions options = new SymbolOptions().withLatLng(_latLng);

        try {
            style.addImage(id, createImage(properties.getJSONObject("image")));
            options = options.withIconImage(id);
        } catch (JSONException | IOException | SVGParseException e) {
            e.printStackTrace();
        }

        symbol = symbolManager.create(options);
    }

    public void select() {
        isSelected = true;
        animate(false);
    }

    public void deselect() {
        isSelected = false;
        animate(true);
    }

    private void animate(boolean isReverse) {
        // MapBox crashes when using symbolManager.update(Symbol). Luckily, another method works but
        // uses a list.
       ArrayList<Symbol> list = new ArrayList<>();
        list.add(symbol);
       /*  if (animator != null) {
            animator.cancel();
        }
        animator = isReverse
            ? ValueAnimator.ofFloat(1, 0)
            : ValueAnimator.ofFloat(0, 1);
        animator.setDuration(DEFAULT_ANIMATION_DURATION);
        animator.setInterpolator(interpolator);
        animator.start();

        animator.addUpdateListener(valueAnimator -> {
            if (!animationHasStarted) {
                animationHasStarted = true;
            }
            final float factor = (float)(valueAnimator.getAnimatedValue());
            final double y = DEFAULT_BOUNCE_HEIGHT * factor;
            symbol.setIconOffset(new PointF(0, Math.round(y)));
            symbol.setIconSize((float) (1 + .25 * factor));
            symbolManager.update(list);
        });*/
        symbol.setIconSize((float) (1 + (isReverse ? 0 : .25)));
        symbolManager.update(list);
    }

    public Symbol get() {
        return symbol;
    }

    public String getId() {
        return id;
    }

    public void setIcon(JSONObject imageObject) {
        ArrayList<Symbol> list = new ArrayList<>();
        list.add(symbol);
        String newFilePath = toFilePath(imageObject);

        if (!newFilePath.equals(iconFilePath) && !newFilePath.isEmpty()) {
            try {
                style.addImage(id, createImage(imageObject));
            } catch (JSONException | IOException | SVGParseException e) {
                e.printStackTrace();
            }
            symbol.setIconImage(iconFilePath);
            symbolManager.update((list));
            iconFilePath = newFilePath;
        }
    }

    void setLatLng(LatLng _latLng) {
        latLng = _latLng;
        symbol.setLatLng(latLng);
    }

    private String toFilePath(JSONObject imageObject) {
        try {
            String filePath = "";
            if (imageObject.has("url")) filePath = imageObject.getString("url");
            if (imageObject.has("image")) filePath = imageObject.getString("image");
            return filePath;
        } catch(JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    private int applyRetinaFactor(long d) {
        return Math.round(d * retinaFactor);
    }

    private BitmapDrawable createSVG(SVG svg, int width, int height) {
        if (width == 0)
            width = applyRetinaFactor((int) Math.ceil(svg.getDocumentWidth()));
        if (height == 0)
            height = applyRetinaFactor((int) Math.ceil(svg.getDocumentHeight()));
        Bitmap newBM = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas bmCanvas = new Canvas(newBM);
        svg.renderToCanvas(bmCanvas);
        return new BitmapDrawable(activity.getApplicationContext().getResources(), newBM);
    }

    /**
     * Creates icon for symbol from url or local file
     *
     * @param imageObject The properties.image part of a JSON marker
     * @return an icon with a custom image
     */
    // Thanks @anothar
    private Bitmap createImage(JSONObject imageObject) throws JSONException, IOException, SVGParseException {
        InputStream stream = null;
        BitmapDrawable bitmapDrawable;
        Bitmap bitmap = null;
        Context ctx = activity.getApplicationContext();
        AssetManager am = ctx.getResources().getAssets();

        try {
            if (imageObject != null) {
                if (imageObject.has("url")) {
                    String stringURI = imageObject.getString("url");

                    final Uri uri = Uri.parse(stringURI);
                    final String uriPath = uri.getPath() != null ? uri.getPath() : "";
                    final boolean isAsset = uriPath.contains("/android_asset/");
                    final String filesDir = activity.getFilesDir().getPath();
                    final boolean doesContainFilesDirInPath = uriPath.contains(filesDir);
                    final int startIndex = isAsset
                        ? "/android_asset/".length()
                        : doesContainFilesDirInPath
                            ? filesDir.length()
                            : 0;
                    final int endIndex = uriPath.lastIndexOf('/') + 1;
                    String path = isAsset
                        ? uriPath.substring(startIndex, endIndex)
                        : "www/" + uriPath.substring(startIndex, endIndex);
                    final String fileName = uri.getLastPathSegment();

                    // We first look in the current asset bundle.
                    final File iconFile = new File(activity.getFilesDir(), path + fileName);

                    if (iconFile.exists()) {
                        stream = new FileInputStream(iconFile);
                    }
                    // If file does not exists we get the original version in the initial asset bundle with AssetsManager
                    else {
                        try {
                            stream = am.open(path + fileName);
                        } catch (IOException e) {
                            throw new JSONException("File not found: " + uri);
                        }
                    }

                    if (fileName != null && fileName.endsWith(".svg")) {
                        bitmapDrawable = createSVG(SVG.getFromInputStream(stream), imageObject.has("width") ? applyRetinaFactor(imageObject.getInt("width")) : 0,
                                imageObject.has("height") ? applyRetinaFactor(imageObject.getInt("height")) : 0);
                    } else {
                        bitmapDrawable = new BitmapDrawable(ctx.getResources(), stream);
                    }
                } else if (imageObject.has("data")) {
                    byte[] decodedBytes = Base64.decode(imageObject.getString("data"), 0);
                    bitmapDrawable = new BitmapDrawable(ctx.getResources(), BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length));

                } else if (imageObject.has("svg")) {
                    bitmapDrawable = createSVG(SVG.getFromString(imageObject.getString("svg")), imageObject.has("width") ? applyRetinaFactor(imageObject.getInt("width")) : 0,
                            imageObject.has("height") ? applyRetinaFactor(imageObject.getInt("height")) : 0);
                } else {
                    throw new JSONException("Not found image data");
                }
                if (imageObject.has("width") && imageObject.has("height")) {
                    bitmap = new BitmapDrawable(ctx.getResources(),
                        Bitmap.createScaledBitmap(bitmapDrawable.getBitmap(),
                                applyRetinaFactor(imageObject.getInt("width")),
                                applyRetinaFactor(imageObject.getInt("height")),
                                true
                        )).getBitmap();
                } else {
                    bitmap = bitmapDrawable.getBitmap();
                }
            }
        } finally {
            if (stream != null)
                stream.close();
        }
        return bitmap;
    }
}
