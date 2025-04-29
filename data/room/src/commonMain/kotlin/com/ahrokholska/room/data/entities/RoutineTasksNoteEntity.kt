package com.ahrokholska.room.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "routine_tasks_note")
internal data class RoutineTasksNoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)