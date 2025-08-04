package com.ahrokholska.create_note.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.ahrokholska.create_note.presentation.createNote.CreateNoteScreen
import com.ahrokholska.note_presentation.model.NoteType
import com.ahrokholska.create_note.presentation.selectType.SelectNoteTypeScreen
import kotlinx.serialization.Serializable

@Serializable
internal data object CreateNewNotesGraph {
    @Serializable
    internal data object SelectNoteType

    @Serializable
    internal data class CreateNote(val type: String)
}

fun NavGraphBuilder.createNoteGraph(
    navController: NavHostController,
    onExit: () -> Unit,
    onNoteSaved: () -> Unit,
) {
    navigation<CreateNewNotesGraph>(startDestination = CreateNewNotesGraph.SelectNoteType) {
        composable<CreateNewNotesGraph.SelectNoteType> {
            SelectNoteTypeScreen(
                onBackClick = onExit,
                onTypeClick = {
                    navController.navigate(CreateNewNotesGraph.CreateNote(it.name))
                }
            )
        }

        composable<CreateNewNotesGraph.CreateNote> {
            val args = it.toRoute<CreateNewNotesGraph.CreateNote>()
            CreateNoteScreen(
                type = NoteType.valueOf(args.type),
                onBackClick = navController::navigateUp,
                onNoteSaved = onNoteSaved
            )
        }
    }
}

fun NavController.navigateToCreateNewNotesGraph(builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(route = CreateNewNotesGraph, builder = builder)
}

fun NavController.popCreateNewNotesGraph() {
    popBackStack(
        route = CreateNewNotesGraph,
        inclusive = true
    )
}