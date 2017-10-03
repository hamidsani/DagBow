package com.ah.dagbow.Fragments;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

/**
 * Created by HaMiD on 10/2/2017.
 */

public class SlidingLayout extends FrameLayout {
    private float xFraction = 0;

    public SlidingLayout(Context context) {
        super(context);
    }

    public SlidingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlidingLayout(Context context, AttributeSet attrs,
                                 int defStyle) {
        super(context, attrs, defStyle);
    }

    private ViewTreeObserver.OnPreDrawListener preDrawListener = null;


    public void setXFraction(float fraction) {
        this.xFraction = fraction;
        if (getWidth() == 0) {
            if (preDrawListener == null) {
                preDrawListener = new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        getViewTreeObserver().removeOnPreDrawListener(
                                preDrawListener);
                        setXFraction(xFraction);
                        return true;
                    }
                };
                getViewTreeObserver().addOnPreDrawListener(preDrawListener);
            }
            return;
        }
        float translationX = getWidth() * fraction;
        setTranslationX(translationX);
    }
}
