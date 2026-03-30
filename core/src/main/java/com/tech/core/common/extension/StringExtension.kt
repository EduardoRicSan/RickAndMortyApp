package com.tech.core.common.extension

import com.tech.core.route.AboutApp
import com.tech.core.route.Characters
import com.tech.core.route.RMAppDestination

fun String?.toDestination(): RMAppDestination? {
    return when (this?.substringAfterLast(".")) {
        Characters::class.simpleName -> Characters
        AboutApp::class.simpleName -> AboutApp
        else -> null
    }
}