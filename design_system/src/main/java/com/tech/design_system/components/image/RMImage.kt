package com.tech.design_system.components.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import com.tech.design_system.common.model.RMImageSource

/**
 * A reusable image composable supporting URL, resource, or Bitmap sources.
 *
 * Provides optional click handling and supports placeholders and error images.
 *
 * @param source The image source ([RMImageSource.Url], [RMImageSource.Resource],
 * or [RMImageSource.Bitmap]).
 * @param modifier Modifier applied to the image container.
 * @param contentDescription Accessibility description for screen readers.
 * @param contentScale How the image should scale to fit its bounds. Default is [ContentScale.Crop].
 * @param placeholder Optional placeholder painter displayed while loading.
 * @param error Optional painter displayed if image loading fails.
 * @param onClick Optional callback invoked when the image is clicked.
 */
@Composable
fun RMImage(
    source: RMImageSource,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
    placeholder: Painter? = rememberVectorPainter(Icons.Default.Image),
    error: Painter? = rememberVectorPainter(Icons.Default.BrokenImage),
    onClick: (() -> Unit)? = null,
) {
    val clickableModifier =
        if (onClick != null) modifier.clickable(onClick = onClick)
        else modifier

    when (source) {
        is RMImageSource.Url -> {

            val model = remember(source.url) { source.url }

            AsyncImage(
                model = model,
                contentDescription = contentDescription,
                modifier = clickableModifier,
                contentScale = contentScale,
                placeholder = placeholder,
                error = error
            )


        }

        is RMImageSource.Resource -> {
            Image(
                painter = painterResource(source.resId),
                contentDescription = contentDescription,
                modifier = clickableModifier,
                contentScale = contentScale
            )
        }

        is RMImageSource.Bitmap -> {
            Image(
                bitmap = source.imageBitmap,
                contentDescription = contentDescription,
                modifier = clickableModifier,
                contentScale = contentScale
            )
        }
    }
}
