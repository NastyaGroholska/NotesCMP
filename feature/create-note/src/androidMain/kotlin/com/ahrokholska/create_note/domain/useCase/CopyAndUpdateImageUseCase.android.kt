package com.ahrokholska.create_note.domain.useCase

import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.ahrokholska.create_note.data.CopyImageWorker

actual class Copier(private val workManager: WorkManager) {
    actual fun doWork(uri: String) {
        workManager.enqueue(
            OneTimeWorkRequestBuilder<CopyImageWorker>()
                .setInputData(workDataOf(CopyImageWorker.URI_PARAM to uri))
                .build()
        )
    }
}