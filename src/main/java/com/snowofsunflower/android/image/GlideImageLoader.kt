package com.snowofsunflower.android.image

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager

/**
 * 图片加载器Gilde实现
 */
class GlideImageLoader(c: Context) : IImageLoader {
    private val mManager: RequestManager = Glide.with(c)
    override fun load(res: Int) = ImageLoaderConfig(mManager, res)

    override fun load(res: String) = ImageLoaderConfig(mManager, res)

}

