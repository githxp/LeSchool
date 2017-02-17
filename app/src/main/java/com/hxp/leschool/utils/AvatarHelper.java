package com.hxp.leschool.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.hxp.leschool.R;

/**
 * 图片加载帮助类
 * Created by hxp on 17-2-11.
 */

public class AvatarHelper {

    private Context mContext;
    private ImageView mImageView;
    private String mAvatarUrl;

    public AvatarHelper(Context context, ImageView imageView, String avatarUrl) {
        mContext = context;
        mImageView = imageView;
        mAvatarUrl = avatarUrl;

        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        ImageLoader imageLoader = new ImageLoader(requestQueue, new BitmapCache());
        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(mImageView,
                R.drawable.ic_avatarloading, R.drawable.ic_avatarloadfail);
        imageLoader.get(mAvatarUrl, imageListener);
    }

    class BitmapCache implements ImageLoader.ImageCache {

        private LruCache<String, Bitmap> mCache;

        public BitmapCache() {
            int maxSize = 10 * 1024 * 1024;
            mCache = new LruCache<String, Bitmap>(maxSize) {
                @Override
                protected int sizeOf(String key, Bitmap bitmap) {
                    return bitmap.getRowBytes() * bitmap.getHeight();
                }
            };
        }

        @Override
        public Bitmap getBitmap(String url) {
            return mCache.get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            mCache.put(url, bitmap);
        }
    }
}
