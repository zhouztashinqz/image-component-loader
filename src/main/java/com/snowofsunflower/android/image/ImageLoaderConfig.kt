package com.snowofsunflower.android.image

import android.widget.ImageView
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

/**
 * Gilde图片配置器实现
 */
class ImageLoaderConfig : IImageLoaderConfig {

    private val mBuilder: RequestBuilder<*>
    private val mManager: RequestManager
    private var mShape: IImageShape? = null

    constructor(manager: RequestManager, res: Int) {
        mBuilder = manager.load(res)
        mManager = manager
    }

    constructor(manager: RequestManager, fileOrUrl: String) {
        mBuilder = manager.load(fileOrUrl)
        mManager = manager
    }

    override fun error(res: Int): ImageLoaderConfig {
        return this
    }

    override fun placeholder(res: Int): ImageLoaderConfig {
        return this
    }

    override fun shape(shape: IImageShape): ImageLoaderConfig {
        mShape = shape
        return this
    }

    override fun into(iv: ImageView) {
        if (mShape != null) {
            val shape = mShape
            if (shape is ImageRound) {
                val corners = RoundedCorners(shape.round.toInt())
                val requestOption = RequestOptions.bitmapTransform(corners)
                mBuilder.apply(requestOption)
            } else if (shape is ImageCircle) {
                val requestOptions = RequestOptions.circleCropTransform()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                mBuilder.apply(requestOptions)
            }
        }
        mBuilder.into(iv)
    }
}