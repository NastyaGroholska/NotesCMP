package com.ahrokholska.create_note.presentation.createNote.screenTypes.guidance

import androidx.lifecycle.viewModelScope
import com.ahrokholska.api.model.Note
import com.ahrokholska.create_note.domain.useCase.CopyAndUpdateImageUseCase
import com.ahrokholska.create_note.domain.useCase.SaveNoteUseCase
import com.ahrokholska.create_note.presentation.createNote.screenTypes.NoteWithTitleViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class GuidanceNoteScreenViewModel(
    private val saveNoteUseCase: SaveNoteUseCase,
    private val copyAndUpdateImageUseCase: CopyAndUpdateImageUseCase
) : NoteWithTitleViewModel() {
    private val _body = MutableStateFlow("")
    val body = _body.asStateFlow()
    private val _image = MutableStateFlow("")
    val image = _image.asStateFlow()

    fun changeBody(body: String) {
        if (isSaving) return
        viewModelScope.launch {
            _body.update { body }
        }
    }

    fun changeImage(uri: String) {
        if (isSaving) return
        viewModelScope.launch {
            _image.update { uri }
        }
    }

    fun saveNote(title: String, body: String, image: String, onSuccess: () -> Unit) =
        saveNote {
            saveNoteUseCase(Note.Guidance(title = title, body = body, image = image)).onSuccess {
                copyAndUpdateImageUseCase(image)
                onSuccess()
            }
        }
}