package com.ahrokholska.notes_home.domain.useCase

import com.ahrokholska.api.NotesRepository

internal class GetLast10PinnedNotesUseCase (private val notesRepository: NotesRepository) {
    operator fun invoke() = notesRepository.getLast10PinnedNotes()
}