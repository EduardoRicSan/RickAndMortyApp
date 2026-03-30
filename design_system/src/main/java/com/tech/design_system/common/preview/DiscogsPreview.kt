package com.tech.design_system.common.preview

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.tech.design_system.theme.RMChallengeTheme

@Composable
fun RMPreview(
    content: @Composable () -> Unit
) {
    RMChallengeTheme {
        Surface {
            content()
        }
    }
}
