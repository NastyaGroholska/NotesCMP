package com.ahrokholska.room.data.intermediate

import androidx.room.Embedded
import androidx.room.Relation
import com.ahrokholska.room.data.entities.RoutineTasksNoteEntity
import com.ahrokholska.room.data.entities.RoutineTasksNoteSubNoteEntity

internal data class RoutineTasksNoteEntityWithSubNotes(
    @Embedded
    val note: RoutineTasksNoteEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "note_id"
    )
    val subNotes: List<RoutineTasksNoteSubNoteEntity>
)