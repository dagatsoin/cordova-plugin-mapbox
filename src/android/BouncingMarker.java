package com.dagatsoin.plugins.mapbox;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.animation.LinearInterpolator;

import com.mapbox.mapboxsdk.annotations.Marker;

class BouncingMarker {
    private Marker _marker;
    private static final int DEFAULT_BOUNCE_HEIGHT = 10;
    private static final long DEFAULT_ANIMATION_DURATION = 300;
    private TimeInterpolator interpolator = new LinearInterpolator();
    private ValueAnimator animator;
    private boolean animationHasStarted;

    BouncingMarker(Marker marker) {
        this._marker = marker;
    }

    public void bounce() {
        if (animator != null) {
            animator.cancel();
        }
        final int height = _marker.getIcon().getBitmap().getHeight();
        animator = ValueAnimator.ofFloat(0, 1);
        animator.setDuration(DEFAULT_ANIMATION_DURATION);
        animator.setInterpolator(interpolator);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(1);
        animator.start();
        animator.addUpdateListener(valueAnimator -> {
            if (!animationHasStarted) {
                animationHasStarted = true;
            }
            final double y = (DEFAULT_BOUNCE_HEIGHT * Math.sin((float)(valueAnimator.getAnimatedValue()) * 90 * 0.0174532925));
            _marker.setTopOffsetPixels((int)Math.round(y));
        });
    }

    public Marker getMarker() {
        return _marker;
    }

    public void setMarker(Marker m) {
        this._marker = m;
    }
}
