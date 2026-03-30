package com.tech.design_system.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.tech.design_system.common.model.RMImageSource
import com.tech.design_system.components.image.RMImage
import com.tech.design_system.components.text.RMCaptionText
import com.tech.design_system.components.text.RMTitleText
import com.tech.design_system.tokens.sizes
import com.tech.design_system.tokens.spacing

@Composable
fun RMImageCard(
    imageUrl: String?,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit
) {
    RMCard(
        modifier = modifier
            .fillMaxWidth()
            .height(MaterialTheme.sizes.imageCardHeight),
        onClick = onClick,
        contentPadding = PaddingValues(MaterialTheme.spacing.none)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            RMImage(
                source = RMImageSource.Url(imageUrl.orEmpty()),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.75f)
                            )
                        )
                    )
            )

            content()
        }
    }
}