package com.playground.sgaw.sample.datelogger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Circular button that displays text.
 *
 * Adapter from https://caster.io/episodes/custom-views-and-viewgroups-part-1/
 */

public class CircularButton extends View implements IDateView {
    private Paint mCircularPaint;
    private TextPaint mTextPaint;

    private SimpleDateFormat mFormat;
    private String mText = "Hello World";

    public CircularButton(Context context) {
        this(context, null);
    }

    public CircularButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    @Override
    public void setValue(Date value) {
        mText = mFormat.format(value);
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas); // No-op call

        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();

        int smallestWidth = (canvasWidth < canvasHeight) ? canvasWidth : canvasHeight;

        float centerX = canvasWidth * 0.5f;
        float centerY = canvasHeight * 0.5f;

        float radius = smallestWidth * 0.5f;
        canvas.drawCircle(centerX, centerY, radius, mCircularPaint);

        float textOffsetX = mTextPaint.measureText(mText) * 0.5f;
        float textOffsetY = mTextPaint.getFontMetrics().ascent * 0.4f;
        canvas.drawText(mText, centerX - textOffsetX, centerY - textOffsetY, mTextPaint);

    }

    private void init(Context context) {
        mFormat = new SimpleDateFormat("MMM d");

        mCircularPaint = new Paint();
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);

        // TODO: Use attributes
        mCircularPaint.setColor(Color.parseColor("#880E4F"));
        mTextPaint.setColor(ContextCompat.getColor(context, android.R.color.white));
        // TODO: Use dimension/attribute
        mTextPaint.setTextSize(32f * getResources().getDisplayMetrics().scaledDensity);
    }
}
