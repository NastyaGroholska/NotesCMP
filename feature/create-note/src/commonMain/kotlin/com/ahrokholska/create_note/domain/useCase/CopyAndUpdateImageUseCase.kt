package com.ahrokholska.create_note.domain.useCase

internal class CopyAndUpdateImageUseCase (/*private val workManager: WorkManager*/) {
    operator fun invoke(uri: String) {
       /* workManager.enqueue(
            OneTimeWorkRequestBuilder<CopyImageWorker>()
                .setInputData(workDataOf(CopyImageWorker.URI_PARAM to uri))
                .build()
        )*/
    }
}