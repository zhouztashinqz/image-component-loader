package com.snowofsunflower.android.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by zhouztashin on 2018/10/31.
 */

public class GlideImageLoader implements IImageLoader {
    private RequestManager mManager = null;
    private RequestBuilder mBuilder = null;
    private int mErrorRes;
    private int mPlaceholderRes;
    private IImageShape mShape;

    @Override
    public IImageLoader with(Context c) {
        mManager = Glide.with(c);
        return this;
    }

    @Override
    public IImageLoader into(ImageView iv) {
        if(mBuilder ==null){
            throw new IllegalStateException("you need to call load first");
        }else{
            if(mErrorRes >0){
               // mBuilder.error(mErrorRes);
            }
            if(mPlaceholderRes >0){
                //mBuilder.placeholder(mPlaceholderRes);
            }
            if(mShape !=null){
                if(mShape instanceof  ImageRound){
                    ImageRound round = (ImageRound) mShape;
                    round.getRound();
                    //设置图片圆角角度
                    RoundedCorners roundedCorners= new RoundedCorners(round.getRound());
                    //通过RequestOptions扩展功能
                    RequestOptions requestOptions = RequestOptions.bitmapTransform(roundedCorners);
                    mBuilder.apply(requestOptions);
                }else if(mShape instanceof  ImageCircle){

                    RequestOptions requestOptions = RequestOptions.circleCropTransform()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盘缓存
                            .skipMemoryCache(true);//不做内存缓存
                    mBuilder.apply(requestOptions);
                }
            }
            mBuilder.into(iv);
        }
        return this;
    }

    @Override
    public IImageLoader load(int res) {
        if(mManager ==null){
            throw new IllegalStateException("you need to call with first");
        }else{
            mBuilder = mManager.load(res);
        }
        return this;
    }

    @Override
    public IImageLoader load(String fileOrUrl) {
        if(mManager ==null){
            throw new IllegalStateException("you need to call with first");
        }else{
            mBuilder = mManager.load(fileOrUrl);
        }
        return this;
    }

    @Override
    public IImageLoader error(int res) {
        mErrorRes = res;
        return this;
    }

    @Override
    public IImageLoader placeholder(int res) {
        mPlaceholderRes = res;
        return this;
    }

    @Override
    public IImageLoader shape(IImageShape shape) {
        mShape = shape;
        return this;
    }
}
