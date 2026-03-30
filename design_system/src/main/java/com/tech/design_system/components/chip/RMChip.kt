package com.tech.design_system.components.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.tech.design_system.common.model.CharacterStatus
import com.tech.design_system.components.text.RMCaptionText
import com.tech.design_system.tokens.sizes
import com.tech.design_system.tokens.spacing

@Composable
fun RMChip(
    status: CharacterStatus,
    modifier: Modifier = Modifier
) {

    val backgroundColor = when (status) {
        CharacterStatus.ALIVE -> Color(0xFF4CAF50)
        CharacterStatus.DEAD -> Color(0xFFF44336)
        CharacterStatus.UNKNOWN -> Color(0xFF9E9E9E)
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(MaterialTheme.sizes.avatarMedium))
            .background(backgroundColor)
            .padding(horizontal = MaterialTheme.spacing.md, vertical = MaterialTheme.spacing.xs)
    ) {
        RMCaptionText(
            text = status.name,
            color = Color.White,
            maxLines = 1
        )
    }
}