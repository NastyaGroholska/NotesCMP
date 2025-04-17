package com.ahrokholska.notes_home.domain.useCase

import com.ahrokholska.api.NotesRepository
import com.ahrokholska.note_presentation.model.NotePreview
import com.ahrokholska.presentation_domain_mapper.toUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Suppress("UNCHECKED_CAST")
internal class GetAllNotesUseCase (private val notesRepository: NotesRepository) {
    inline operator fun <reified T : NotePreview> invoke(): Flow<List<T>> = when(T::class){
        NotePreview.InterestingIdea::class -> notesRepository.getAllInterestingIdeaNotes()
        NotePreview.BuyingSomething::class -> notesRepository.getAllBuySomethingNotes()
        NotePreview.Goals::class -> notesRepository.getAllGoalsNotes()
        NotePreview.Guidance::class -> notesRepository.getAllGuidanceNotes()
        NotePreview.RoutineTasks::class -> notesRepository.getAllRoutineTasksNotes()
        else -> throw IllegalStateException()
    }.map { list ->
        list.mapIndexed { index, item -> item.toUI(index) }
    } as Flow<List<T>>
}