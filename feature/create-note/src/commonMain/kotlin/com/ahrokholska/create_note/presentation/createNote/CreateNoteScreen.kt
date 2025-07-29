package com.ahrokholska.create_note.presentation.createNote

import androidx.compose.runtime.Composable
import com.ahrokholska.note_presentation.model.NoteType
import com.ahrokholska.create_note.presentation.createNote.screenTypes.buy.BuySomethingNoteScreen
import com.ahrokholska.create_note.presentation.createNote.screenTypes.goal.GoalsNoteScreen
import com.ahrokholska.create_note.presentation.createNote.screenTypes.guidance.GuidanceNoteScreen
import com.ahrokholska.create_note.presentation.createNote.screenTypes.idea.InterestingIdeaNoteScreen
import com.ahrokholska.create_note.presentation.createNote.screenTypes.routine.RoutineTasksNoteScreen

@Composable
internal fun CreateNoteScreen(type: NoteType, onBackClick: () -> Unit, onNoteSaved: () -> Unit) {
    when (type) {
        NoteType.InterestingIdea -> InterestingIdeaNoteScreen(
            onBackClick = onBackClick,
            onNoteSaved = onNoteSaved
        )

        NoteType.BuyingSomething -> BuySomethingNoteScreen(
            onBackClick = onBackClick,
            onNoteSaved = onNoteSaved
        )

        NoteType.Goals -> GoalsNoteScreen(onBackClick = onBackClick, onNoteSaved = onNoteSaved)
        NoteType.Guidance -> GuidanceNoteScreen(
            onBackClick = onBackClick,
            onNoteSaved = onNoteSaved
        )

        NoteType.RoutineTasks -> RoutineTasksNoteScreen(
            onBackClick = onBackClick,
            onNoteSaved = onNoteSaved
        )
    }
}