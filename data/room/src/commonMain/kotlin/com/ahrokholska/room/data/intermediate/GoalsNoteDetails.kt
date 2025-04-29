package com.ahrokholska.room.data.intermediate

import androidx.room.Embedded
import com.ahrokholska.room.data.entities.GoalsNoteEntity

internal data class GoalsNoteDetails(
    @Embedded
    val note: GoalsNoteEntity,
    val isFinished: Boolean,
    val isPinned: Boolean
)