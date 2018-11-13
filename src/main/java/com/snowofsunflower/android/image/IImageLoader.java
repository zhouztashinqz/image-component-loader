package com.snowofsunflower.android.image;

import android.content.Context;
import android.graphics.drawable.shapes.Shape;
import android.media.Image;
import android.widget.ImageView;

/**
 * Created by zhouztashin on 2018/10/31.
 */

public interface IImageLoader {



    IImageLoader with(Context c);
    IImageLoader into(ImageView iv);
    IImageLoader load(int res);
    IImageLoader load(String fileOrUrl);
    IImageLoader error(int res);
    IImageLoader placeholder(int res);
    IImageLoader shape(IImageShape shape);


}
