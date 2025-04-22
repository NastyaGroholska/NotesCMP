package com.ahrokholska.notes.di

import com.ahrokholska.api.NotesRepository
import com.ahrokholska.api.model.Note
import com.ahrokholska.api.model.NotePreview
import com.ahrokholska.api.model.NoteTitle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformModule: Module

val mainModule = module {
    single<NotesRepository> {
        object : NotesRepository {
            override suspend fun saveNote(note: Note): Result<Unit> =
                Result.success(Unit)


            override suspend fun deleteNote(
                noteId: Int,
                noteType: com.ahrokholska.api.model.NoteType
            ): Result<Unit> = Result.success(Unit)

            override fun getAllInterestingIdeaNotes(): Flow<List<NotePreview.InterestingIdea>> =
                flowOf(
                    listOf()
                )

            override fun getLast10InterestingIdeaNotes(): Flow<List<NotePreview.InterestingIdea>> =
                flowOf(
                    listOf(NotePreview.InterestingIdea(title = "dsf", body = "body"))
                )

            override fun getAllBuySomethingNotes(): Flow<List<NotePreview.BuyingSomething>> =
                flowOf(
                    listOf()
                )

            override fun getLast10BuySomethingNotes(): Flow<List<NotePreview.BuyingSomething>> =
                flowOf(
                    listOf()
                )

            override fun getAllGoalsNotes(): Flow<List<NotePreview.Goals>> = flowOf(
                listOf()
            )

            override fun getLast10GoalsNotes(): Flow<List<NotePreview.Goals>> = flowOf(
                listOf()
            )

            override fun getAllGuidanceNotes(): Flow<List<NotePreview.Guidance>> = flowOf(
                listOf()
            )

            override fun getLast10GuidanceNotes(): Flow<List<NotePreview.Guidance>> = flowOf(
                listOf()
            )

            override fun getAllRoutineTasksNotes(): Flow<List<NotePreview.RoutineTasks>> = flowOf(
                listOf()
            )

            override fun getLast10RoutineTasksNotes(): Flow<List<NotePreview.RoutineTasks>> =
                flowOf(
                    listOf()
                )

            override fun getInterestingIdeaNoteDetails(noteId: Int): Flow<Note.InterestingIdea?> =
                flowOf()

            override fun getBuySomethingNoteDetails(noteId: Int): Flow<Note.BuyingSomething?> =
                flowOf()

            override fun getGoalsNoteDetails(noteId: Int): Flow<Note.Goals?> = flowOf()

            override fun getGuidanceNoteDetails(noteId: Int): Flow<Note.Guidance?> = flowOf()

            override fun getRoutineTasksNoteDetails(noteId: Int): Flow<Note.RoutineTasks?> =
                flowOf()

            override suspend fun changeBuySomethingItemCheck(
                noteId: Int,
                index: Int,
                checked: Boolean
            ): Result<Unit> = Result.success(Unit)

            override suspend fun changeGoalsTaskCheck(
                noteId: Int,
                index: Int,
                checked: Boolean
            ): Result<Unit> = Result.success(Unit)

            override suspend fun changeGoalsSubtaskCheck(
                noteId: Int,
                taskIndex: Int,
                subtaskIndex: Int,
                checked: Boolean
            ): Result<Unit> = Result.success(Unit)

            override suspend fun changeRoutineTasksSubNoteCheck(
                noteId: Int,
                index: Int,
                finished: Boolean
            ): Result<Unit> = Result.success(Unit)

            override fun getLast10PinnedNotes(): Flow<List<NotePreview>> =
                flowOf(listOf(NotePreview.InterestingIdea(title = "dsf", body = "body")))

            override fun getAllPinnedNotes(): Flow<List<NotePreview>> = flowOf(listOf())

            override suspend fun pinNote(
                noteId: Int,
                noteType: com.ahrokholska.api.model.NoteType,
                time: Long
            ) {

            }

            override suspend fun unpinNote(
                noteId: Int,
                noteType: com.ahrokholska.api.model.NoteType
            ) {

            }

            override suspend fun finishNote(
                noteId: Int,
                noteType: com.ahrokholska.api.model.NoteType,
                time: Long
            ) {

            }

            override fun getAllFinishedNotes(): Flow<List<NotePreview>> = flowOf(listOf())

            override fun getAllTitles(): Flow<List<NoteTitle>> = flowOf(listOf())

            override suspend fun updateImage(oldImage: String, image: String) {

            }
        }
    }
}