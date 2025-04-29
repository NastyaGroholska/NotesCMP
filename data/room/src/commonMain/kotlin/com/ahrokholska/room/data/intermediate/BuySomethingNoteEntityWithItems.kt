package com.ahrokholska.room.data.intermediate

import androidx.room.Embedded
import androidx.room.Relation
import com.ahrokholska.room.data.entities.BuySomethingNoteEntity
import com.ahrokholska.room.data.entities.BuySomethingNoteItemEntity

internal data class BuySomethingNoteEntityWithItems(
    @Embedded
    val note: BuySomethingNoteEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "note_id"
    )
    val items: List<BuySomethingNoteItemEntity>
)