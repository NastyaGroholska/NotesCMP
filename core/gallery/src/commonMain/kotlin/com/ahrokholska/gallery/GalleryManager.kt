package com.ahrokholska.gallery

import androidx.compose.runtime.Composable

@Composable
expect fun rememberGalleryManager(onResult: (String) -> Unit): GalleryManager

class GalleryManager(private val onLaunch: () -> Unit) {
    fun launch() {
        onLaunch()
    }
}