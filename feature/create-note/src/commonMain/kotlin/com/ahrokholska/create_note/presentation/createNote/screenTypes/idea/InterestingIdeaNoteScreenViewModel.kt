package com.ahrokholska.create_note.presentation.createNote.screenTypes.idea

import androidx.lifecycle.viewModelScope
import com.ahrokholska.api.model.Note
import com.ahrokholska.create_note.domain.useCase.SaveNoteUseCase
import com.ahrokholska.create_note.presentation.createNote.screenTypes.NoteWithTitleViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class InterestingIdeaNoteScreenViewModel(
    private val saveNoteUseCase: SaveNoteUseCase
) : NoteWithTitleViewModel() {
    private val _body = MutableStateFlow("")
    val body = _body.asStateFlow()

    fun changeBody(body: String) {
        if (isSaving) return
        viewModelScope.launch {
            _body.update { body }
        }
    }

    fun saveNote(title: String, body: String, onSuccess: () -> Unit) =
        saveNote {
            saveNoteUseCase(Note.InterestingIdea(title = title, body = body))
            onSuccess()
        }
}