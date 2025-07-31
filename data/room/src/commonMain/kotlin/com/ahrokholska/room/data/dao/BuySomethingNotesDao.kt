package com.ahrokholska.room.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.ahrokholska.api.model.Note
import com.ahrokholska.api.model.NoteType
import com.ahrokholska.room.data.entities.BuySomethingNoteEntity
import com.ahrokholska.room.data.entities.BuySomethingNoteItemEntity
import com.ahrokholska.room.data.intermediate.BuySomethingNoteDetails
import com.ahrokholska.room.data.intermediate.BuySomethingNoteEntityWithItems
import com.ahrokholska.room.data.intermediate.NoteTitle
import kotlinx.coroutines.flow.Flow

@Dao
internal abstract class BuySomethingNotesDao {
    @Query("DELETE FROM buy_something_note WHERE id = :noteId")
    abstract suspend fun deleteBuySomethingNoteOnly(noteId: Int)

    @Insert
    protected abstract suspend fun insertBuySomethingNoteEntity(note: BuySomethingNoteEntity): Long

    @Insert
    protected abstract suspend fun insertBuySomethingNoteItem(item: BuySomethingNoteItemEntity)

    @Query("SELECT * FROM buy_something_note WHERE rowid=:rowId")
    protected abstract suspend fun getBuySomethingNoteByRowId(rowId: Long): BuySomethingNoteEntity

    @Transaction
    open suspend fun insert(note: Note.BuyingSomething) {
        val rowId = insertBuySomethingNoteEntity(
            BuySomethingNoteEntity(
                title = note.title
            )
        )
        val buyNote = getBuySomethingNoteByRowId(rowId)
        note.items.forEachIndexed { index, item ->
            insertBuySomethingNoteItem(
                BuySomethingNoteItemEntity(
                    noteId = buyNote.id,
                    itemIndex = index,
                    checked = item.first,
                    text = item.second
                )
            )
        }
    }

    @Transaction
    @Query(
        "SELECT buy_something_note.*" +
                "FROM buy_something_note " +
                "LEFT JOIN  finished_notes " +
                "ON buy_something_note.id = finished_notes.note_id AND finished_notes.note_type = :type " +
                "WHERE finished_notes.note_id is null"
    )
    protected abstract fun getAllBuySomethingNotesGen(type: NoteType = NoteType.BuyingSomething): Flow<List<BuySomethingNoteEntityWithItems>>

    fun getAllBuySomethingNotes() = getAllBuySomethingNotesGen()

    @Transaction
    @Query(
        "SELECT buy_something_note.*" +
                "FROM buy_something_note " +
                "LEFT JOIN  finished_notes " +
                "ON buy_something_note.id = finished_notes.note_id AND finished_notes.note_type = :type " +
                "WHERE finished_notes.note_id is null " +
                "ORDER BY id DESC LIMIT 10"
    )
    protected abstract fun getLast10BuySomethingNotesGen(type: NoteType = NoteType.BuyingSomething): Flow<List<BuySomethingNoteEntityWithItems>>

    fun getLast10BuySomethingNotes() = getLast10BuySomethingNotesGen()

    @Transaction
    @Query(
        "SELECT buy_something_note.*, NOT finished_notes.note_id is NULL as isFinished,NOT pinned_notes.note_id is NULL as isPinned " +
                "FROM (SELECT buy_something_note.* FROM buy_something_note WHERE buy_something_note.id = :id) buy_something_note " +
                "LEFT JOIN finished_notes ON finished_notes.note_id = :id AND finished_notes.note_type = :type " +
                "LEFT JOIN pinned_notes ON pinned_notes.note_id = :id AND pinned_notes.note_type = :type"
    )
    protected abstract fun getBuySomethingNoteDetailsGen(
        id: Int, type: NoteType = NoteType.BuyingSomething
    ): Flow<BuySomethingNoteDetails?>

    fun getBuySomethingNoteDetails(id: Int) = getBuySomethingNoteDetailsGen(id)

    @Transaction
    @Query("UPDATE buy_something_note_item SET checked = :checked WHERE note_id = :id AND item_index = :index")
    abstract suspend fun changeCheck(id: Int, index: Int, checked: Boolean)

    @Transaction
    @Query("SELECT * FROM buy_something_note WHERE buy_something_note.id = :id")
    abstract fun getBuySomethingNote(id: Int): Flow<BuySomethingNoteEntityWithItems>

    @Query("SELECT title, id FROM buy_something_note")
    abstract fun getAllTitles(): Flow<List<NoteTitle>>
}