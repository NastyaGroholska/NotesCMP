package com.ahrokholska.create_note.domain.useCase

import com.ahrokholska.api.NotesRepository
import com.ahrokholska.api.model.Note

internal class SaveNoteUseCase (private val notesRepository: NotesRepository) {
    suspend operator fun invoke(note: Note) = notesRepository.saveNote(note)
}