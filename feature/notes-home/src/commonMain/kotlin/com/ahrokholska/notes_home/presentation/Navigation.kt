package com.ahrokholska.notes_home.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.ahrokholska.api.NotesRepository
import com.ahrokholska.api.model.Note
import com.ahrokholska.api.model.NotePreview
import com.ahrokholska.api.model.NoteTitle
import com.ahrokholska.note_presentation.model.NoteType
import com.ahrokholska.notes_home.domain.useCase.GetAllNotesUseCase
import com.ahrokholska.notes_home.domain.useCase.GetAllPinnedNotesUseCase
import com.ahrokholska.notes_home.domain.useCase.GetLast10NotesUseCase
import com.ahrokholska.notes_home.domain.useCase.GetLast10PinnedNotesUseCase
import com.ahrokholska.notes_home.presentation.allNotes.AllNotesScreen
import com.ahrokholska.notes_home.presentation.allNotes.AllNotesScreenViewModel
import com.ahrokholska.notes_home.presentation.allPinnedNotes.AllPinnedNotesScreen
import com.ahrokholska.notes_home.presentation.allPinnedNotes.AllPinnedNotesScreenViewModel
import com.ahrokholska.notes_home.presentation.home.HomeScreen
import com.ahrokholska.notes_home.presentation.home.HomeScreenViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.serialization.Serializable

@Serializable
data object HomeGraph {
    @Serializable
    internal data object Home

    @Serializable
    internal data class AllNotes(val type: NoteType)

    @Serializable
    internal data object AllPinnedNotes
}

val repo = object : NotesRepository {
    override suspend fun saveNote(note: Note): Result<Unit> =
        Result.success(Unit)


    override suspend fun deleteNote(
        noteId: Int,
        noteType: com.ahrokholska.api.model.NoteType
    ): Result<Unit> = Result.success(Unit)

    override fun getAllInterestingIdeaNotes(): Flow<List<NotePreview.InterestingIdea>> = flowOf(
        listOf()
    )

    override fun getLast10InterestingIdeaNotes(): Flow<List<NotePreview.InterestingIdea>> = flowOf(
        listOf()
    )

    override fun getAllBuySomethingNotes(): Flow<List<NotePreview.BuyingSomething>> = flowOf(
        listOf()
    )

    override fun getLast10BuySomethingNotes(): Flow<List<NotePreview.BuyingSomething>> = flowOf(
        listOf()
    )

    override fun getAllGoalsNotes(): Flow<List<NotePreview.Goals>> = flowOf(
        listOf()
    )

    override fun getLast10GoalsNotes(): Flow<List<NotePreview.Goals>> = flowOf(
        listOf()
    )

    override fun getAllGuidanceNotes(): Flow<List<NotePreview.Guidance>> = flowOf(
        listOf()
    )

    override fun getLast10GuidanceNotes(): Flow<List<NotePreview.Guidance>> = flowOf(
        listOf()
    )

    override fun getAllRoutineTasksNotes(): Flow<List<NotePreview.RoutineTasks>> = flowOf(
        listOf()
    )

    override fun getLast10RoutineTasksNotes(): Flow<List<NotePreview.RoutineTasks>> = flowOf(
        listOf()
    )

    override fun getInterestingIdeaNoteDetails(noteId: Int): Flow<Note.InterestingIdea?> = flowOf()

    override fun getBuySomethingNoteDetails(noteId: Int): Flow<Note.BuyingSomething?> = flowOf()

    override fun getGoalsNoteDetails(noteId: Int): Flow<Note.Goals?> = flowOf()

    override fun getGuidanceNoteDetails(noteId: Int): Flow<Note.Guidance?> = flowOf()

    override fun getRoutineTasksNoteDetails(noteId: Int): Flow<Note.RoutineTasks?> = flowOf()

    override suspend fun changeBuySomethingItemCheck(
        noteId: Int,
        index: Int,
        checked: Boolean
    ): Result<Unit> = Result.success(Unit)

    override suspend fun changeGoalsTaskCheck(
        noteId: Int,
        index: Int,
        checked: Boolean
    ): Result<Unit> = Result.success(Unit)

    override suspend fun changeGoalsSubtaskCheck(
        noteId: Int,
        taskIndex: Int,
        subtaskIndex: Int,
        checked: Boolean
    ): Result<Unit> = Result.success(Unit)

    override suspend fun changeRoutineTasksSubNoteCheck(
        noteId: Int,
        index: Int,
        finished: Boolean
    ): Result<Unit> = Result.success(Unit)

    override fun getLast10PinnedNotes(): Flow<List<NotePreview>> = flowOf(listOf())

    override fun getAllPinnedNotes(): Flow<List<NotePreview>> = flowOf(listOf())

    override suspend fun pinNote(
        noteId: Int,
        noteType: com.ahrokholska.api.model.NoteType,
        time: Long
    ) {

    }

    override suspend fun unpinNote(
        noteId: Int,
        noteType: com.ahrokholska.api.model.NoteType
    ) {

    }

    override suspend fun finishNote(
        noteId: Int,
        noteType: com.ahrokholska.api.model.NoteType,
        time: Long
    ) {

    }

    override fun getAllFinishedNotes(): Flow<List<NotePreview>> = flowOf(listOf())

    override fun getAllTitles(): Flow<List<NoteTitle>> = flowOf(listOf())

    override suspend fun updateImage(oldImage: String, image: String) {

    }
}

fun NavGraphBuilder.homeGraph(
    navController: NavHostController,
    onNoteClick: (Int, NoteType) -> Unit,
    decoration: @Composable (content: @Composable (scrollBottomPadding: Dp) -> Unit) -> Unit =
        @Composable { content -> content(0.dp) }
) {
    navigation<HomeGraph>(startDestination = HomeGraph.Home) {
        composable<HomeGraph.Home> {
            HomeScreen(
                viewModel = HomeScreenViewModel(
                    GetLast10NotesUseCase(repo),
                    GetLast10PinnedNotesUseCase(repo)
                ),
                decoration = decoration,
                onNoteClick = onNoteClick,
                onViewAllClick = { navController.navigate(HomeGraph.AllNotes(it)) },
                onViewAllPinnedClick = { navController.navigate(HomeGraph.AllPinnedNotes) }
            )
        }

        composable<HomeGraph.AllNotes> {
            val args = it.toRoute<HomeGraph.AllNotes>()
            val type = args.type
            AllNotesScreen(
                viewModel = AllNotesScreenViewModel(type = type, GetAllNotesUseCase(repo)),
                type = type,
                onBackClick = navController::navigateUp,
                onNoteClick = onNoteClick
            )
        }

        composable<HomeGraph.AllPinnedNotes> {
            AllPinnedNotesScreen(
                viewModel = AllPinnedNotesScreenViewModel(GetAllPinnedNotesUseCase(repo)),
                onBackClick = navController::navigateUp,
                onNoteClick = onNoteClick
            )
        }
    }
}