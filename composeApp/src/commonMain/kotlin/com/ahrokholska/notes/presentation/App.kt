package com.ahrokholska.notes.presentation

import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ahrokholska.notes.presentation.common.MainScreenDecoration
import com.ahrokholska.notes.presentation.common.bottomBar.BottomBarScreen

@Composable
fun App() {
    MaterialTheme {
        MainScreenDecoration(currentScreen = BottomBarScreen.HOME) {
            Text("Hello")
        }
    }
}