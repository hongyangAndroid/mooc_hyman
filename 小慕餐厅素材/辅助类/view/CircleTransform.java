package com.imooc.res.ui.view;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.squareup.picasso.Transformation;

public class CircleTransform implements Transformation {
    @Override
    public Bitmap transform(Bitmap source) {
        /**
         * 求出宽和高的哪个小
         */
        int size = Math.min(source.getWidth(), source.getHeight());

        /**
         * 求中心点
         */
        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;

        /**
         * 生成BitMap
         */
        Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
        if (squaredBitmap != source) {
            //释放
            source.recycle();
        }

        /**
         * 建立新的Bitmap
         */
        Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

        /**
         * 画布画笔
         */
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();

        BitmapShader shader = new BitmapShader(squaredBitmap,
                BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAntiAlias(true);

        float r = size / 2f;
        /**
         * 画圆
         */
        canvas.drawCircle(r, r, r, paint);

        squaredBitmap.recycle();
        return bitmap;
    }

    @Override
    public String key() {
        return "circle";
    }
}