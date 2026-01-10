package com.ahrokholska.room.data

import com.ahrokholska.api.NotesRepository
import com.ahrokholska.api.model.Note
import com.ahrokholska.api.model.NoteCompact
import com.ahrokholska.api.model.NoteTitle
import com.ahrokholska.api.model.NoteType
import com.ahrokholska.result.ResultUtils.getResult
import com.ahrokholska.room.data.dao.BuySomethingNotesDao
import com.ahrokholska.room.data.dao.FinishNoteDao
import com.ahrokholska.room.data.dao.GoalsNotesDao
import com.ahrokholska.room.data.dao.GuidanceNotesDao
import com.ahrokholska.room.data.dao.InterestingIdeaNotesDao
import com.ahrokholska.room.data.dao.PinNoteDao
import com.ahrokholska.room.data.dao.RoutineTasksNotesDao
import com.ahrokholska.room.data.entities.FinishedNoteEntity
import com.ahrokholska.room.di.TransactionProvider
import com.ahrokholska.room.mapper.toDomain
import com.ahrokholska.room.mapper.toDomainPreview
import com.ahrokholska.room.mapper.toEntity
import com.ahrokholska.room.mapper.toNote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

internal class NotesRepositoryImpl internal constructor(
    private val transactionProvider: TransactionProvider,
    private val interestingIdeaNotesDao: InterestingIdeaNotesDao,
    private val buySomethingNotesDao: BuySomethingNotesDao,
    private val goalsNotesDao: GoalsNotesDao,
    private val guidanceNotesDao: GuidanceNotesDao,
    private val routineTasksNotesDao: RoutineTasksNotesDao,
    private val pinNoteDao: PinNoteDao,
    private val finishNoteDao: FinishNoteDao,
) : NotesRepository {
    override suspend fun saveNote(note: Note): Result<Unit> = withContext(Dispatchers.IO) { //TODO
        when (note) {
            is Note.InterestingIdea ->
                getResult { interestingIdeaNotesDao.insert(note.toEntity()) }

            is Note.BuyingSomething ->
                getResult { buySomethingNotesDao.insert(note) }

            is Note.Goals -> getResult { goalsNotesDao.insert(note) }
            is Note.Guidance -> getResult { guidanceNotesDao.insert(note.toEntity()) }
            is Note.RoutineTasks -> getResult { routineTasksNotesDao.insert(note) }
        }
    }

    override suspend fun deleteNote(noteId: Int, noteType: NoteType): Result<Unit> =
        withContext(Dispatchers.IO) {
            getResult {
                transactionProvider.startTransaction {
                    when (noteType) {
                        NoteType.InterestingIdea ->
                            interestingIdeaNotesDao.deleteInterestingIdeaNoteOnly(noteId)


                        NoteType.BuyingSomething ->
                            buySomethingNotesDao.deleteBuySomethingNoteOnly(noteId)


                        NoteType.Goals -> goalsNotesDao.deleteGoalsOnly(noteId)


                        NoteType.Guidance -> guidanceNotesDao.deleteGuidanceNoteOnly(noteId)


                        NoteType.RoutineTasks -> routineTasksNotesDao.deleteRoutineTasksOnly(noteId)

                    }
                    pinNoteDao.unpinNote(noteId, noteType)
                    finishNoteDao.deleteFinishedRecord(noteId, noteType)
                }
            }
        }

    @Suppress("UNCHECKED_CAST")
    override fun <T : NoteCompact> getAllNotes(note: T): Flow<List<T>> = when (note) {
        is NoteCompact.BuyingSomething -> buySomethingNotesDao.getAllBuySomethingNotes()
            .map { list ->
                list.map { it.toDomainPreview() as T }
            }

        is NoteCompact.Goals -> goalsNotesDao.getAllGoalsNotes().map { map ->
            map.map { it.toDomainPreview() as T }
        }

        is NoteCompact.Guidance -> guidanceNotesDao.getAllGuidanceNotes().map { list ->
            list.map { it.toDomainPreview() as T }
        }

        is NoteCompact.InterestingIdea -> interestingIdeaNotesDao.getAllInterestingIdeaNotes()
            .map { list ->
                list.map { it.toDomainPreview() as T }
            }

        is NoteCompact.RoutineTasks -> routineTasksNotesDao.getAllRoutineTasksNotes().map { list ->
            list.map { it.toDomainPreview() as T }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : NoteCompact> getLast10Notes(note: T): Flow<List<T>> = when (note) {
        is NoteCompact.BuyingSomething -> buySomethingNotesDao.getLast10BuySomethingNotes()
            .map { list ->
                list.map { it.toDomainPreview() as T }
            }

        is NoteCompact.Goals -> goalsNotesDao.getLast10GoalsNotes().map { map ->
            map.map { mapEntry -> mapEntry.toDomainPreview() as T }
        }

        is NoteCompact.Guidance -> guidanceNotesDao.getLast10GuidanceNotes().map { list ->
            list.map { it.toDomainPreview() as T }
        }

        is NoteCompact.InterestingIdea -> interestingIdeaNotesDao.getLast10InterestingIdeaNotes()
            .map { list ->
                list.map { it.toDomainPreview() as T }
            }

        is NoteCompact.RoutineTasks -> routineTasksNotesDao.getLast10RoutineTasksNotes()
            .map { list ->
                list.map { it.toDomainPreview() as T }
            }
    }

    override fun getInterestingIdeaNoteDetails(noteId: Int): Flow<Note.InterestingIdea?> =
        interestingIdeaNotesDao.getInterestingIdeaNoteDetails(noteId).map { it?.toNote() }

    override fun getBuySomethingNoteDetails(noteId: Int): Flow<Note.BuyingSomething?> =
        buySomethingNotesDao.getBuySomethingNoteDetails(noteId).map { it?.toNote() }

    override fun getGoalsNoteDetails(noteId: Int): Flow<Note.Goals?> =
        goalsNotesDao.getGoalsNoteDetails(noteId)
            .combine(goalsNotesDao.getGoalsNoteTasks(noteId)) { detail, tasks ->
                detail?.toNote(tasks)
            }

    override fun getGuidanceNoteDetails(noteId: Int): Flow<Note.Guidance?> =
        guidanceNotesDao.getGuidanceNoteDetails(noteId).map { it?.toNote() }

    override fun getRoutineTasksNoteDetails(noteId: Int): Flow<Note.RoutineTasks?> =
        routineTasksNotesDao.getRoutineTasksNoteDetails(noteId).map { it?.toNote() }

    override suspend fun changeBuySomethingItemCheck(
        noteId: Int, index: Int, checked: Boolean
    ): Result<Unit> = withContext(Dispatchers.IO) {
        getResult { buySomethingNotesDao.changeCheck(noteId, index, checked) }
    }

    override suspend fun changeGoalsTaskCheck(
        noteId: Int, index: Int, checked: Boolean
    ): Result<Unit> = withContext(Dispatchers.IO) {
        getResult { goalsNotesDao.changeTaskCheck(noteId, index, checked) }
    }

    override suspend fun changeGoalsSubtaskCheck(
        noteId: Int, taskIndex: Int, subtaskIndex: Int, checked: Boolean
    ): Result<Unit> = withContext(Dispatchers.IO) {
        getResult { goalsNotesDao.changeSubtaskCheck(noteId, taskIndex, subtaskIndex, checked) }
    }

    override suspend fun changeRoutineTasksSubNoteCheck(
        noteId: Int, index: Int, finished: Boolean
    ): Result<Unit> = withContext(Dispatchers.IO) {
        getResult { routineTasksNotesDao.changeRoutineTasksSubNoteCheck(noteId, index, finished) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getLast10PinnedNotes(): Flow<List<NoteCompact>> =
        pinNoteDao.getLast10PinnedNotes().flatMapLatest { list ->
            if (list.isEmpty()) {
                flowOf(listOf())
            } else {
                combine(list.map { note -> mapNote(note.noteType, note.noteId) }) { it.asList() }
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getAllPinnedNotes(): Flow<List<NoteCompact>> =
        pinNoteDao.getAllPinnedNotes().flatMapLatest { list ->
            if (list.isEmpty()) {
                flowOf(listOf())
            } else {
                combine(list.map { note -> mapNote(note.noteType, note.noteId) }) { it.asList() }
            }
        }

    private fun mapNote(noteType: NoteType, noteId: Int) = when (noteType) {
        NoteType.InterestingIdea ->
            interestingIdeaNotesDao.getInterestingIdeaNote(noteId)
                .map { it.toDomainPreview() }

        NoteType.BuyingSomething ->
            buySomethingNotesDao.getBuySomethingNote(noteId)
                .map { it.toDomainPreview() }

        NoteType.Goals ->
            goalsNotesDao.getGoalsNote(noteId)
                .map { map ->
                    map.entries.first().toDomainPreview()
                }

        NoteType.Guidance -> guidanceNotesDao.getGuidanceNote(noteId)
            .map { it.toDomainPreview() }

        NoteType.RoutineTasks -> routineTasksNotesDao.getRoutineTasksNote(noteId)
            .map { it.toDomainPreview() }
    }

    override suspend fun pinNote(noteId: Int, noteType: NoteType, time: Long) =
        withContext(Dispatchers.IO) {
            pinNoteDao.pinNote(
                com.ahrokholska.room.data.entities.PinnedNoteEntity(
                    noteId,
                    noteType,
                    time
                )
            )
        }

    override suspend fun unpinNote(noteId: Int, noteType: NoteType) = withContext(Dispatchers.IO) {
        pinNoteDao.unpinNote(noteId, noteType)
    }

    override suspend fun finishNote(noteId: Int, noteType: NoteType, time: Long) =
        withContext(Dispatchers.IO) {
            transactionProvider.startTransaction {
                pinNoteDao.unpinNote(noteId, noteType)
                finishNoteDao.insertFinishRecord(
                    FinishedNoteEntity(noteId, noteType, time)
                )
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getAllFinishedNotes(): Flow<List<NoteCompact>> =
        finishNoteDao.getAllFinishedNotes().flatMapLatest { list ->
            if (list.isEmpty()) {
                flowOf(listOf())
            } else {
                combine(list.map { note -> mapNote(note.noteType, note.noteId) }) { it.asList() }
            }
        }

    override fun getAllTitles(): Flow<List<NoteTitle>> =
        combine(
            interestingIdeaNotesDao.getAllTitles().toDomain(NoteType.InterestingIdea),
            buySomethingNotesDao.getAllTitles().toDomain(NoteType.BuyingSomething),
            guidanceNotesDao.getAllTitles().toDomain(NoteType.Guidance),
            goalsNotesDao.getAllTitles().toDomain(NoteType.Goals)
        ) { it -> it.flatMap { it.toList() } }

    override suspend fun updateImage(oldImage: String, image: String) =
        withContext(Dispatchers.IO) {
            guidanceNotesDao.updateImage(oldImage, image)
        }
}