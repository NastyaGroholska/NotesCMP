package com.ahrokholska.notes_home.presentation.allNotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahrokholska.note_presentation.model.NotePreview
import com.ahrokholska.note_presentation.model.NoteType
import com.ahrokholska.notes_home.domain.useCase.GetAllNotesUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

internal class AllNotesScreenViewModel(
    type: NoteType,
    getAllNotesUseCase: GetAllNotesUseCase,
) : ViewModel() {
    val notes = when (type) {
        NoteType.InterestingIdea -> getAllNotesUseCase<NotePreview.InterestingIdea>()
        NoteType.BuyingSomething -> getAllNotesUseCase<NotePreview.BuyingSomething>()
        NoteType.Goals -> getAllNotesUseCase<NotePreview.Goals>()
        NoteType.Guidance -> getAllNotesUseCase<NotePreview.Guidance>()
        NoteType.RoutineTasks -> getAllNotesUseCase<NotePreview.RoutineTasks>()
    }.stateIn(viewModelScope, SharingStarted.Lazily, listOf())
}