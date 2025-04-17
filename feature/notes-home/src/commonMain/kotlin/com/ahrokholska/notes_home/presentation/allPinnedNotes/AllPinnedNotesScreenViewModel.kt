package com.ahrokholska.notes_home.presentation.allPinnedNotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahrokholska.notes_home.domain.useCase.GetAllPinnedNotesUseCase
import com.ahrokholska.presentation_domain_mapper.toUI
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

internal class AllPinnedNotesScreenViewModel (
    getAllPinnedNotesUseCase: GetAllPinnedNotesUseCase
) : ViewModel() {
    val notes = getAllPinnedNotesUseCase().map { list ->
        list.mapIndexed { index, item -> item.toUI(index) }
    }.stateIn(viewModelScope, SharingStarted.Lazily, listOf())
}