package com.ahrokholska.notes.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ahrokholska.notes.presentation.common.MainScreenDecoration
import com.ahrokholska.notes.presentation.common.bottomBar.BottomBarScreen
import com.ahrokholska.notes_home.presentation.HomeGraph
import com.ahrokholska.notes_home.presentation.homeGraph

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HomeGraph) {
        homeGraph(
            navController = navController,
            onNoteClick = { _, _ -> },//navController::navigateToNoteDetailsScreen
        ) { content ->
            MainScreenDecoration(
                content = content,
                currentScreen = BottomBarScreen.HOME,
                onPlusClick = {},//navController::navigateToCreateNewNotesGraph,
                onScreenClick = {
                    when (it) {
                        BottomBarScreen.HOME -> {}
                        BottomBarScreen.FINISHED -> {}//navController.navigateToAllFinishedNotesScreen()
                        BottomBarScreen.SEARCH -> {}//navController.navigateToNoteSearchScreen()
                        BottomBarScreen.SETTINGS -> {}//navController.navigate(Screen.Settings)
                    }
                },
            )
        }
    }
}