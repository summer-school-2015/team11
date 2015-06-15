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
    int shiftx;
    int shifty;
    int w, h;
    int textSize1, textSize2, textSize3, textSpeed;
    long starttime, curtime, res, pauseStart, startDelay;
    int endGame;
    Paint  paint;
    boolean wasPaused, isStartTimeSet;
    public ImageViewMy(Context cnt)
    {
        super(cnt);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        endGame = 0;
        shiftx = 0;
        shifty = 0;
        wasPaused = false;
        isStartTimeSet = false;
        textSize1 = 70;
        textSize2 = 70;
        textSize3 = 70;
        textSpeed = 5;
        res = 0;
        paint = new Paint();
        paint.setColor(Color.RED);
    }
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        curtime = System.currentTimeMillis();
        if (curtime - startDelay < 3000) {
            switch ((int)(curtime - startDelay) / 1000) {
                case 0:
                    textSize1 += textSpeed;
                    paint.setTextSize(textSize1);
                    canvas.drawText(Long.toString(3 - (curtime - startDelay) / 1000), (w - (textSize1 / 2)) / 2, h / 2, paint);
                    break;
                case 1:
                    textSize2 += textSpeed;
                    paint.setTextSize(textSize2);
                    canvas.drawText(Long.toString(3 - (curtime - startDelay) / 1000), (w - (textSize2 / 2)) / 2, h / 2, paint);
                    break;
                case 2:
                    textSize3 += textSpeed;
                    paint.setTextSize(textSize3);
                    canvas.drawText(Long.toString(3 - (curtime - startDelay) / 1000), (w - (textSize3 / 2)) / 2, h / 2, paint);
                    break;
            }
        }
        else
            if (!isStartTimeSet)
            {
                isStartTimeSet = true;
                starttime = System.currentTimeMillis();
            }
        canvas.drawBitmap(bmp, shiftx, shifty, null);
    }
    void changexy(int x, int y)
    {
        shiftx += x;
        shifty += y;
    }
    int getShiftx()
    {
        return shiftx;
    }
    int getShifty()
    {
        return shifty;
    }
    void winLose(int color)
    {
        if (Color.argb((color & (255 << 24)) >> 24,(color & (255 << 16)) >> 16,(color & (255 << 8 )) >> 8,color & 255) == Color.GREEN)
            endGame = 1;
        if (Color.argb((color & (255 << 24)) >> 24,(color & (255 << 16)) >> 16,(color & (255 << 8 )) >> 8,color & 255) == Color.BLACK)
            endGame = 2;
    }
}

