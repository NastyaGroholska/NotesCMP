package com.ahrokholska.notes_home.domain.useCase

import com.ahrokholska.api.NotesRepository
import com.ahrokholska.note_presentation.model.NotePreview
import com.ahrokholska.presentation_domain_mapper.toUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Suppress("UNCHECKED_CAST")
internal class GetLast10NotesUseCase(private val notesRepository: NotesRepository) {
    inline operator fun <reified T : NotePreview> invoke(): Flow<List<T>> = when (T::class) {
        NotePreview.InterestingIdea::class -> notesRepository.getLast10InterestingIdeaNotes()
        NotePreview.BuyingSomething::class -> notesRepository.getLast10BuySomethingNotes()
        NotePreview.Goals::class -> notesRepository.getLast10GoalsNotes()
        NotePreview.Guidance::class -> notesRepository.getLast10GuidanceNotes()
        NotePreview.RoutineTasks::class -> notesRepository.getLast10RoutineTasksNotes()
        else -> throw IllegalStateException()
    }.map { list ->
        list.mapIndexed { index, item -> item.toUI(index) }
    } as Flow<List<T>>
}