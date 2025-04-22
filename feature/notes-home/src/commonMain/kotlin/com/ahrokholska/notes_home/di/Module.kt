package com.ahrokholska.notes_home.di

import com.ahrokholska.note_presentation.model.NoteType
import com.ahrokholska.notes_home.domain.useCase.GetAllNotesUseCase
import com.ahrokholska.notes_home.domain.useCase.GetAllPinnedNotesUseCase
import com.ahrokholska.notes_home.domain.useCase.GetLast10NotesUseCase
import com.ahrokholska.notes_home.domain.useCase.GetLast10PinnedNotesUseCase
import com.ahrokholska.notes_home.presentation.allNotes.AllNotesScreenViewModel
import com.ahrokholska.notes_home.presentation.allPinnedNotes.AllPinnedNotesScreenViewModel
import com.ahrokholska.notes_home.presentation.home.HomeScreenViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homeModule = module {
    factoryOf(::GetAllNotesUseCase)
    factoryOf(::GetAllPinnedNotesUseCase)
    factoryOf(::GetLast10NotesUseCase)
    factoryOf(::GetLast10PinnedNotesUseCase)

    viewModelOf(::HomeScreenViewModel)
    viewModel { (type: NoteType) -> AllNotesScreenViewModel(type = type, get()) }
    viewModelOf(::AllPinnedNotesScreenViewModel)
}