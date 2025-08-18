package com.ahrokholska.copy_image.domain.useCase

class CopyAndUpdateImageUseCase(private val copier: Copier) {
    operator fun invoke(uri: String) {
        copier.doWork(uri)
    }
}

expect class Copier {
    fun doWork(uri: String)
}