package com.ahrokholska.create_note

import androidx.compose.runtime.Composable

@Composable
expect fun rememberGalleryManager(onResult: (String) -> Unit): GalleryManager

class GalleryManager( val onLaunch: () -> Unit) {
    fun launch() {
        onLaunch()
    }
}