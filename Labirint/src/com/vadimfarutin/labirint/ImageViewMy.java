package com.vadimfarutin.labirint;


import android.content.Context;
import android.graphics.*;
import android.view.View;
import android.widget.ImageView;

public class ImageViewMy extends ImageView {
    public ImageViewMy(Context cnt) {
        super(cnt);
    }

    static int shiftx = 0;
    static int shifty = 0;


    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);

        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        canvas.drawBitmap(bmp, 50 + shiftx, 50 + shifty, paint);
        shiftx = (shiftx + 10) % 200;
        shifty = (shifty + 10) % 200;
    }
}
