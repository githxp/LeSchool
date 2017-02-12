package com.hxp.leschool.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Dimension;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.hxp.leschool.R;

/**
 * Created by hxp on 17-2-12.
 */

public class Custom extends View {

    int x;
    int y;
    int r;

    public Custom(Context context) {
        super(context);

    }

    public Custom(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Custom);
        x = typedArray.getDimensionPixelSize(R.styleable.Custom_x, 10);
        y = typedArray.getDimensionPixelSize(R.styleable.Custom_y, 10);
        r = typedArray.getDimensionPixelSize(R.styleable.Custom_r, 10);
        Log.d("fragment", "x:" + x + "y:" + y + "r:" + r);
        typedArray.recycle();
    }

    public Custom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Custom(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = 10;
        int height = 10;
        if (widthMode == MeasureSpec.AT_MOST) {
            width = 300;
        }
        if (heightMode == MeasureSpec.AT_MOST) {
            height = 300;
        }
        Log.d("fragment", "w1:" + width + " h1:" + height);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5);
        canvas.drawCircle(getMeasuredHeight(), getMeasuredWidth(), r, paint);
        Log.d("fragment", "h2:" + getMeasuredHeight() + " w2:" + getMeasuredWidth());
    }
}
