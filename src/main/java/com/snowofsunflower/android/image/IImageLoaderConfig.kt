package com.snowofsunflower.android.image

import android.widget.ImageView

/**
 * 图片加载配置器
 */
interface IImageLoaderConfig {
    fun into(iv: ImageView)
    fun error(res: Int): ImageLoaderConfig
    fun placeholder(res: Int): ImageLoaderConfig
    fun shape(shape: IImageShape): ImageLoaderConfig
}