package com.ahrokholska.room.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ahrokholska.api.model.NoteType
import com.ahrokholska.room.data.entities.FinishedNoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface FinishNoteDao {
    @Query("DELETE FROM finished_notes WHERE note_id = :noteId AND note_type = :type")
    suspend fun deleteFinishedRecord(noteId: Int, type: NoteType)

    @Insert
    suspend fun insertFinishRecord(record: FinishedNoteEntity)

    @Query("SELECT * FROM finished_notes ORDER BY time")
    fun getAllFinishedNotes(): Flow<List<FinishedNoteEntity>>
}