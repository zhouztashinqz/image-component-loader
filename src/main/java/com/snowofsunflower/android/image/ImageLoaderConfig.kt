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

    /**
     * 构造Int资源访问
     */
    constructor(manager: RequestManager, res: Int) {
        mBuilder = manager.load(res)
        mManager = manager
    }

    /**
     *构造文件或者URL访问
     */
    constructor(manager: RequestManager, fileOrUrl: String) {
        mBuilder = manager.load(fileOrUrl)
        mManager = manager
    }

    /**
     * 配置错误时候的显示
     */
    override fun error(res: Int): ImageLoaderConfig {
        return this
    }

    /**
     * 配置等待显示
     */
    override fun placeholder(res: Int): ImageLoaderConfig {
        return this
    }

    /**
     * 配置形状
     */
    override fun shape(shape: IImageShape): ImageLoaderConfig {
        mShape = shape
        return this
    }

    override fun into(iv: ImageView) {

        mShape?.run {
            when (mShape) {
                is ImageRound -> {
                    val shape: ImageRound = mShape as ImageRound
                    val corners = RoundedCorners(shape.round.toInt())
                    val requestOption = RequestOptions.bitmapTransform(corners)
                    mBuilder.apply(requestOption)
                }
                is ImageCircle -> {
                    val requestOptions = RequestOptions.circleCropTransform()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                    mBuilder.apply(requestOptions)
                }
                else -> null
            }
        }
        mBuilder.into(iv)
    }
}