package com.ahrokholska.create_note.domain.useCase

internal class CopyAndUpdateImageUseCase(private val copier: Copier) {
    operator fun invoke(uri: String) {
        copier.doWork(uri)
    }
}

expect class Copier {
    fun doWork(uri: String)
}