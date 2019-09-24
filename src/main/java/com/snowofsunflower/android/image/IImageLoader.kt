package com.snowofsunflower.android.image


/**
 * 图片加载器接口
 */
interface IImageLoader {
    fun load(res: Int): IImageLoaderConfig
    fun load(res: String): IImageLoaderConfig
}
