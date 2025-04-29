package com.ahrokholska.room.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "goals_note")
internal data class GoalsNoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
)