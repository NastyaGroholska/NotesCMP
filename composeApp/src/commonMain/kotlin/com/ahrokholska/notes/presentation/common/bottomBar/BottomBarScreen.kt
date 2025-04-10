package com.ahrokholska.notes.presentation.common.bottomBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssignmentTurnedIn
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AssignmentTurnedIn
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import notescmp.composeapp.generated.resources.Res
import notescmp.composeapp.generated.resources.finished
import notescmp.composeapp.generated.resources.home
import notescmp.composeapp.generated.resources.search
import notescmp.composeapp.generated.resources.settings
import org.jetbrains.compose.resources.StringResource

enum class BottomBarScreen(
    val icon: ImageVector,
    val selectedIcon: ImageVector,
    val text: StringResource
) {
    HOME(Icons.Outlined.Home, Icons.Filled.Home, Res.string.home),
    FINISHED(
        Icons.Outlined.AssignmentTurnedIn,
        Icons.Filled.AssignmentTurnedIn,
        Res.string.finished
    ),
    SEARCH(Icons.Outlined.Search, Icons.Filled.Search, Res.string.search),
    SETTINGS(Icons.Outlined.Settings, Icons.Filled.Settings, Res.string.settings)
}