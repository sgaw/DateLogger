package com.playground.sgaw.sample.datelogger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Circular seek bar.
 */

public class CircularSeekBar extends View {
    private Paint mPaint;
    private float mPointRadius;

    // Store progress in polar coordinates.
    private double mTheta; // Selected seek position, in radians.

    public CircularSeekBar(Context context) {
        this(context, null);
    }

    public CircularSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#1A237E"));
        mPaint.setStyle(Paint.Style.STROKE);
        float strokeWidth = 5f * getResources().getDisplayMetrics().density;
        mPaint.setStrokeWidth(strokeWidth);
        mPointRadius = strokeWidth;

        mTheta = 0.0;
    }

    @Override
    public void onDraw(Canvas canvas) {

        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();

        int smallestWidth = (canvasWidth < canvasHeight) ? canvasWidth : canvasHeight;

        float centerX = canvasWidth * 0.5f;
        float centerY = canvasHeight * 0.5f;

        float radius = smallestWidth * 0.5f;

        // Draw circle
        canvas.drawCircle(centerX, centerY, radius, mPaint);

        // Draw point
        float pointX = centerX + (float) (radius * Math.cos(mTheta));
        float pointY = centerY - (float) (radius * Math.sin(mTheta));
        Log.d("BAR", String.format("onDraw center = (%.2f, %.2f) (theta, x, y) = (%.2f, %.2f, %.2f)",
                centerX, centerY, mTheta, centerX, centerY));


        canvas.drawCircle(pointX, pointY, mPointRadius, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                updateTheta(event.getX(), event.getY());
                invalidate();
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * @return progress normalized to a value from 0 to 1.0.
     */
    public double getProgress() {
        return mTheta / (0.5 * Math.PI);
    }

    private void updateTheta(float x, float y) {
        int canvasWidth = getWidth();
        int canvasHeight = getHeight();

        float centerX = canvasWidth * 0.5f;
        float centerY = canvasHeight * 0.5f;

        // Map the input point to polar coordinates
        float cartesianX = x - centerX;
        float cartesianY = -(y - centerY);
        mTheta = Math.atan2(cartesianY, cartesianX);
        Log.d("BAR", String.format("updateTheta(%.2f, %.2f) = %.2f", x, y, mTheta));
    }
}
