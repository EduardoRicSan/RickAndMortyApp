package com.tech.design_system.components.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.tech.design_system.common.model.RMImageSource
import com.tech.design_system.theme.RMChallengeTheme
import com.tech.design_system.tokens.sizes
import com.tech.design_system.tokens.spacing

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
    placeholder: Painter? = null,
    error: Painter? = null,
    onClick: (() -> Unit)? = null,
) {
    val clickableModifier =
        if (onClick != null) modifier.clickable(onClick = onClick)
        else modifier

    when (source) {
        is RMImageSource.Url -> {
            AsyncImage(
                model = source.url,
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

/**
 * A circular image composable with optional border and click handling.
 *
 * Uses [RMImage] internally and provides standardized avatar sizes
 * via the Design System.
 *
 * @param source The image source ([RMImageSource.Url], [RMImageSource.Resource],
 * or [RMImageSource.Bitmap]).
 * @param contentDescription Accessibility description for screen readers.
 * @param size Diameter of the circular image. Defaults to [MaterialTheme.sizes.avatarMedium].
 * @param borderWidth Width of the circular border.
 * @param borderColor Color of the border. Defaults to [MaterialTheme.colorScheme.outline].
 * @param onClick Optional callback invoked when the image is clicked.
 */

@Composable
fun RMCircularImage(
    source: RMImageSource,
    contentDescription: String? = null,
    size: Dp = MaterialTheme.sizes.avatarMedium,
    borderWidth: Dp = 1.dp,
    borderColor: Color = MaterialTheme.colorScheme.outline,
    onClick: (() -> Unit)? = null
) {
    RMImage(
        source = source,
        contentDescription = contentDescription,
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .border(borderWidth, borderColor, CircleShape)
            .let {
                if (onClick != null) it.clickable(onClick = onClick) else it
            }
    )
}

@Preview(showBackground = true)
@Composable
private fun RMImageResourcePreview() {
    RMChallengeTheme {
        RMImage(
            source = RMImageSource.Resource(android.R.drawable.sym_def_app_icon),
            contentDescription = "Resource Image"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RMCircularImagePreview() {
    RMChallengeTheme {
        var clicked by remember { mutableStateOf(false) }

        RMCircularImage(
            source = RMImageSource.Resource(android.R.drawable.sym_def_app_icon),
            size = MaterialTheme.sizes.avatarLarge,
            borderColor = if (clicked) Color.Blue else MaterialTheme.colorScheme.outline,
            onClick = { clicked = !clicked },
            contentDescription = "Circular Avatar"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RMCircularImageRowPreview() {
    RMChallengeTheme {
        var clicked by remember { mutableStateOf(false) }

        Row {
            RMCircularImage(
                source = RMImageSource.Resource(android.R.drawable.sym_def_app_icon),
                size = MaterialTheme.sizes.avatarSmall,
                onClick = { clicked = !clicked },
                contentDescription = "Small Avatar"
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.md))
            RMCircularImage(
                source = RMImageSource.Resource(android.R.drawable.sym_def_app_icon),
                size = MaterialTheme.sizes.avatarMedium,
                onClick = { clicked = !clicked },
                contentDescription = "Medium Avatar"
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.md))
            RMCircularImage(
                source = RMImageSource.Resource(android.R.drawable.sym_def_app_icon),
                size = MaterialTheme.sizes.avatarExtraLarge,
                onClick = { clicked = !clicked },
                contentDescription = "Extra Large Avatar"
            )
        }
    }
}

