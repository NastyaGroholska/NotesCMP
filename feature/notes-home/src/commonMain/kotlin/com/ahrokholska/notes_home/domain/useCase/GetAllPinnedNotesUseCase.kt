package com.ahrokholska.notes_home.domain.useCase

import com.ahrokholska.api.NotesRepository

internal class GetAllPinnedNotesUseCase(private val notesRepository: NotesRepository) {
    operator fun invoke() = notesRepository.getAllPinnedNotes()
}