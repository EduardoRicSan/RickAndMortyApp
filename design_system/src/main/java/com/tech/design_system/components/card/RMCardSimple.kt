package com.tech.design_system.components.card

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tech.design_system.common.preview.RMPreview
import com.tech.design_system.tokens.elevation
import com.tech.design_system.tokens.radius
import com.tech.design_system.tokens.spacing

/**
 * Base card component used across the application following the design system.
 *
 * Provides consistent shape, elevation, spacing and optional click behavior.
 * This component should be used as the default container for grouped content.
 *
 * @param modifier Modifier applied to the card container.
 * @param onClick Optional click callback. When provided, the card becomes interactive.
 * @param content Composable content displayed inside the card.
 *
 * Example usage:
 * RMCard(
 *     onClick = { /* action */ }
 * ) {
 *     Text("Card Content")
 * }
 */
@Composable
fun RMCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    contentPadding: PaddingValues = PaddingValues(MaterialTheme.spacing.md),
    content: @Composable () -> Unit
) {
    Card(
        onClick = onClick ?: {},
        enabled = onClick != null,
        modifier = modifier,
        shape = RoundedCornerShape(MaterialTheme.radius.medium),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = MaterialTheme.elevation.level2
        )
    ) {
        Box(
            modifier = Modifier.padding(contentPadding)
        ) {
            content()
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun RMCardPreview() {
    RMPreview {
        RMCard(
            onClick = {}
        ) {
            Text(text = "RM Card")
        }
    }
}



