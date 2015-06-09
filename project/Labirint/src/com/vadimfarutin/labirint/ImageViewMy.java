package com.vadimfarutin.labirint;


import android.content.Context;
import android.graphics.*;
import android.view.View;
import android.widget.ImageView;

public class ImageViewMy extends ImageView {
    public ImageViewMy(Context cnt) {
        super(cnt);
    }

    public static int shiftx = 0;
    public static int shifty = 0;
    public Bitmap bmp;
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        canvas.drawBitmap(bmp, 50 + shiftx, 50 + shifty, null);

    }
    public void changexy(int x, int y)
    {
        shiftx += x;
        shifty += y;
    }
}

