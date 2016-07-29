package com.playground.sgaw.sample.datelogger;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * Circular seek bar.
 */

public class CircularSeekBar extends CircularButton {

    public CircularSeekBar(Context context) {
        this(context, null);
    }

    public CircularSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#1A237E"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5f * getResources().getDisplayMetrics().density);
        setCircularPaint(paint);
    }
}
