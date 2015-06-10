package com.vadimfarutin.labirint;


import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class ImageViewMy extends ImageView {
    Bitmap bmp;
    public static int shiftx;
    public static int shifty;
    long starttime, curtime, res;
    int fl;

    public ImageViewMy(Context cnt) {
        super(cnt);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        fl = 0;
        shiftx = 0;
        shifty = 0;
    }

   /* String getSpotCoordinates() {
        int x = shiftx;// + bmp.getWidth()/2;
        int y = shifty;// + bmp.getHeight()/2;
        return "x="+String.valueOf(x)+", y="+String.valueOf(y);
    }*/

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bmp, shiftx, shifty, null);

    /*    Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        canvas.drawRect(shiftx, shifty, shiftx + bmp.getWidth(), shifty + bmp.getHeight(), paint);

        paint.setTextSize(15);
        canvas.drawText(getSpotCoordinates(),150,150,paint);*/

    }
    public void changexy(int x, int y)
    {
        shiftx += x;
        shifty += y;
    }
    public int getShiftx()
    {
        return shiftx;
    }
    public int getShifty()
    {
        return shifty;
    }

    public void winLose(int color)
    {
        if (Color.argb((color & (255 << 24)) >> 24,(color & (255 << 16)) >> 16,(color & (255 << 8 )) >> 8,color & 255) == Color.GREEN)
        {
            curtime = System.currentTimeMillis();
            res = curtime - starttime;
            fl = 1;
        }
        if (Color.argb((color & (255 << 24)) >> 24,(color & (255 << 16)) >> 16,(color & (255 << 8 )) >> 8,color & 255) == Color.BLACK)
        {
            curtime = System.currentTimeMillis();
            res = curtime - starttime;
            fl = 2;
        }
    }
}

