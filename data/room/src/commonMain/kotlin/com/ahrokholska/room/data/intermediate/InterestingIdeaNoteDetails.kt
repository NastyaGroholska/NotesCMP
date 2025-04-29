package com.ahrokholska.room.data.intermediate

import androidx.room.Embedded
import com.ahrokholska.room.data.entities.InterestingIdeaNoteEntity

internal data class InterestingIdeaNoteDetails(
    @Embedded
    val note: InterestingIdeaNoteEntity,
    val isFinished: Boolean,
    val isPinned: Boolean
)