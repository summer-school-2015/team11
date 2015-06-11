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
    public int w, h;

    long starttime, curtime, res;
    int fl;
    Paint  paint;
    public ImageViewMy(Context cnt) {
        super(cnt);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        fl = 0;
        shiftx = 0;
        shifty = 0;
        paint = new Paint();
        paint.setTextSize(500);
        paint.setColor(Color.RED);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        curtime = System.currentTimeMillis();
        if (curtime - starttime < 3000) {
            canvas.drawText(Long.toString(3 - (curtime - starttime) / 1000), w / 2, h / 2, paint);
        }
        canvas.drawBitmap(bmp, shiftx, shifty, null);

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

