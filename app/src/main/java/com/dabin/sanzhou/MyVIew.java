package com.dabin.sanzhou;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Dabin on 2017/10/21.
 */

public class MyVIew extends View{
    public MyVIew(Context context) {
        super(context);
    }

    public MyVIew(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyVIew(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        RectF shanxing = new RectF(0, 0, 300, 300);

    }
}
