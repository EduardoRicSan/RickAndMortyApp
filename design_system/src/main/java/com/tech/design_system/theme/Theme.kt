package com.tech.design_system.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.tech.design_system.tokens.Elevation
import com.tech.design_system.tokens.LocalElevation
import com.tech.design_system.tokens.LocalRadius
import com.tech.design_system.tokens.LocalSize
import com.tech.design_system.tokens.LocalSpacing
import com.tech.design_system.tokens.Radius
import com.tech.design_system.tokens.Sizes
import com.tech.design_system.tokens.Spacing

private val DarkColorScheme = darkColorScheme(

    primary = RMGreen,
    onPrimary = RMOnPrimary,

    secondary = RMGreenLight,
    tertiary = RMGreenDark,

    background = RMBlack,
    onBackground = RMOnSurface,

    surface = RMSurface,
    onSurface = RMOnSurface,

    surfaceVariant = RMSurfaceVariant,
    onSurfaceVariant = RMOnSurfaceVariant,

    outline = Color(0xFF3E3E3E)
)


private val LightColorScheme = lightColorScheme(

    primary = RMGreen,
    onPrimary = Color.White,

    secondary = RMGreenDark,
    tertiary = RMGreenLight,

    background = RMLightBackground,
    onBackground = RMLightOnSurface,

    surface = RMLightSurface,
    onSurface = RMLightOnSurface,

    surfaceVariant = Color(0xFFEAEAEA),
    onSurfaceVariant = Color(0xFF555555),

    outline = Color(0xFFD0D0D0)
)


@Composable
fun RMChallengeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme)
                dynamicDarkColorScheme(context)
            else
                dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val spacing = Spacing()
    val radius = Radius()
    val elevation = Elevation()
    val sizes = Sizes()

    CompositionLocalProvider(
        LocalSpacing provides spacing,
        LocalRadius provides radius,
        LocalElevation provides elevation,
        LocalSize provides sizes,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}

