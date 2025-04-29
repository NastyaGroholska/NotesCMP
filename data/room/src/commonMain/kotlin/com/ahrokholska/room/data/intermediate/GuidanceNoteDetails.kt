package com.ahrokholska.room.data.intermediate

import androidx.room.Embedded
import com.ahrokholska.room.data.entities.GuidanceNoteEntity

internal data class GuidanceNoteDetails(
    @Embedded
    val note: GuidanceNoteEntity,
    val isFinished: Boolean,
    val isPinned: Boolean
)