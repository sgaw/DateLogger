package com.playground.sgaw.sample.datelogger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Circular seek bar.
 */

public class CircularSeekBar extends View {

    public interface OnProgressChangedListener {
        void onProgressChanged(CircularSeekBar seekBar, int progress);
    }

    private Paint mPaint;
    private float mPointRadius;
    private OnProgressChangedListener mProgressChangedListener = null;

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

        // Draw progress
        float pointX = centerX + (float) (radius * Math.cos(mTheta));
        float pointY = centerY - (float) (radius * Math.sin(mTheta));
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
     * Set change listener
     */
    public void setProgressChangedListener(OnProgressChangedListener listener) {
        mProgressChangedListener = listener;
    }

    /**
     * @return progress normalized to a value from 0 to 100.
     */
    public int getProgress() {
        int temp = (int) (50.0 * -mTheta / Math.PI);
        if (temp < 0) {
            return 100 + temp;
        }
        return temp;
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

        if (mProgressChangedListener != null) {
            mProgressChangedListener.onProgressChanged(this, getProgress());
        }
    }
}
