package com.example.lib;

import java.lang.ref.SoftReference;
import java.util.HashMap;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;

public class AsyncImageLoader {

    private HashMap<String, SoftReference<Drawable>> imageCache;
    public AsyncImageLoader() {
        imageCache = new HashMap<String, SoftReference<Drawable>>();
    }

    public Drawable loadDrawable(final String imageUrl, final ImageCallback imageCallback) {
        if (imageCache.containsKey(imageUrl)) {
            SoftReference<Drawable> softReference = imageCache.get(imageUrl);
            Drawable drawable = softReference.get();
            if (drawable != null) {
                return drawable;
            }
        }
        final Handler handler = new Handler() {
            public void handleMessage(Message message) {
                imageCallback.imageLoaded((Drawable) message.obj, imageUrl);
            }
        };
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Drawable drawable = loadImageFromUrl(imageUrl);
                imageCache.put(imageUrl, new SoftReference<Drawable>(drawable));
                Message message = handler.obtainMessage(0, drawable);
                handler.sendMessage(message);
            }
        }.start();
        return null;
    }

    /*
    * 加载本地图片，并且返回一个drawable
    * */
    public static Drawable loadImageFromUrl(String url) {
        Bitmap bitmap=CreateImage.createImageThumbnail(url);
        @SuppressWarnings("deprecation")
		Drawable drawable=new BitmapDrawable(bitmap);
        return drawable;
    }

    public interface ImageCallback {
        public void imageLoaded(Drawable imageDrawable, String imageUrl);

    }
}
