package com.ahrokholska.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

internal val height = 54.dp

@Composable
fun TopBar(modifier: Modifier = Modifier, onBackClick: () -> Unit = {}) {
    Column {
        Row(
            modifier = modifier
                .height(height)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackButton(onBackClick = onBackClick)
        }
        HorizontalDivider()
    }
}

@Composable
fun TopBarPreview() {
    TopBar()
}