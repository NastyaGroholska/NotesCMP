package com.ahrokholska.create_note.di

import com.ahrokholska.create_note.domain.useCase.CopyAndUpdateImageUseCase
import com.ahrokholska.create_note.domain.useCase.SaveNoteUseCase
import com.ahrokholska.create_note.presentation.createNote.screenTypes.buy.BuySomethingNoteScreenViewModel
import com.ahrokholska.create_note.presentation.createNote.screenTypes.goal.GoalsNoteScreenViewModel
import com.ahrokholska.create_note.presentation.createNote.screenTypes.guidance.GuidanceNoteScreenViewModel
import com.ahrokholska.create_note.presentation.createNote.screenTypes.idea.InterestingIdeaNoteScreenViewModel
import com.ahrokholska.create_note.presentation.createNote.screenTypes.routine.RoutineTasksNoteScreenViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

expect val platformModule: Module
val createNoteModule = module {
    includes(platformModule)
    factoryOf(::SaveNoteUseCase)
    factoryOf(::CopyAndUpdateImageUseCase)

    viewModelOf(::BuySomethingNoteScreenViewModel)
    viewModelOf(::GoalsNoteScreenViewModel)
    viewModelOf(::GuidanceNoteScreenViewModel)
    viewModelOf(::InterestingIdeaNoteScreenViewModel)
    viewModelOf(::RoutineTasksNoteScreenViewModel)
}
