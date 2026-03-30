package com.tech.design_system.common.model

import androidx.compose.ui.graphics.ImageBitmap

/**
 * Unified model of Image source
 */
sealed class RMImageSource {
    data class Url(val url: String) : RMImageSource()
    data class Resource(val resId: Int) : RMImageSource()
    data class Bitmap(val imageBitmap: ImageBitmap) : RMImageSource()
}
