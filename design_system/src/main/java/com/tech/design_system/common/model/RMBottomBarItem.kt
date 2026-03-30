package com.tech.design_system.common.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.tech.core.route.RMAppDestination

data class BottomBarItem(
    val destination: RMAppDestination,
    val icon: ImageVector,
    @StringRes val labelRes: Int
)