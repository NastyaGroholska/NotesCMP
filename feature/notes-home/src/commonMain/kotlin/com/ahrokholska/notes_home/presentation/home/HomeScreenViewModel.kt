package com.ahrokholska.notes_home.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahrokholska.note_presentation.model.NotePreview
import com.ahrokholska.notes_home.domain.useCase.GetLast10NotesUseCase
import com.ahrokholska.notes_home.domain.useCase.GetLast10PinnedNotesUseCase
import com.ahrokholska.presentation_domain_mapper.toUI
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

internal class HomeScreenViewModel(
    getLast10NotesUseCase: GetLast10NotesUseCase,
    getLast10PinnedNotesUseCase: GetLast10PinnedNotesUseCase,
) : ViewModel() {
    val pinnedNotes = getLast10PinnedNotesUseCase().map { list ->
        list.mapIndexed { index, item ->
            item.toUI(index)
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, null)

    val interestingIdeaNotes = getLast10NotesUseCase<NotePreview.InterestingIdea>()
        .stateIn(viewModelScope, SharingStarted.Lazily, null)

    val buySomethingNotes = getLast10NotesUseCase<NotePreview.BuyingSomething>()
        .stateIn(viewModelScope, SharingStarted.Lazily, null)

    val goalsNotes = getLast10NotesUseCase<NotePreview.Goals>()
        .stateIn(viewModelScope, SharingStarted.Lazily, null)

    val guidanceNotes = getLast10NotesUseCase<NotePreview.Guidance>()
        .stateIn(viewModelScope, SharingStarted.Lazily, null)

    val routineTasksNotes = getLast10NotesUseCase<NotePreview.RoutineTasks>()
        .stateIn(viewModelScope, SharingStarted.Lazily, null)
}