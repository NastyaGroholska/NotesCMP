package com.ahrokholska.copy_image.domain.useCase

import com.ahrokholska.api.NotesRepository
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

actual class Copier(
    private val repository: NotesRepository,
    private val ioDispatcher: CoroutineDispatcher,
) {
    @OptIn(DelicateCoroutinesApi::class, ExperimentalForeignApi::class, ExperimentalTime::class)
    actual fun doWork(uri: String) {
        GlobalScope.launch {
            withContext(ioDispatcher) {
                try {

                    val url = NSURL(string = uri)
                    val fm = NSFileManager()
                    val directoryURL = fm.URLForDirectory(
                        directory = NSDocumentDirectory,
                        inDomain = NSUserDomainMask,
                        appropriateForURL = null,
                        create = true,
                        error = null
                    )
                    val fileName = "${Clock.System.now().toEpochMilliseconds()}.${url.pathExtension}"
                    var newURL = directoryURL?.URLByAppendingPathComponent(
                        pathComponent = "$IMAGE_PATH/"
                    ) ?: throw Exception("Unable to create dir URL")

                    newURL.path?.let { path ->
                        if (!fm.fileExistsAtPath(path)) {
                            println("dir doesn't exist")
                            fm.createDirectoryAtPath(
                                path = path,
                                withIntermediateDirectories = true,
                                attributes = null,
                                error = null
                            )
                        }
                    } ?: throw Exception("path is null")

                    newURL = newURL.URLByAppendingPathComponent(fileName)
                        ?: throw Exception("Unable to create file URL")
                    fm.copyItemAtURL(url, newURL, null)
                    repository.updateImage(uri, "$IMAGE_PATH/$fileName")
                } catch (e: Exception) {
                    print("ERROR: $e")
                }
            }
        }
    }

    companion object {
        const val IMAGE_PATH = "images"
    }
}