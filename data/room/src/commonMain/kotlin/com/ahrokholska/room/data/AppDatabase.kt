package com.ahrokholska.room.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahrokholska.room.data.dao.BuySomethingNotesDao
import com.ahrokholska.room.data.dao.FinishNoteDao
import com.ahrokholska.room.data.dao.GoalsNotesDao
import com.ahrokholska.room.data.dao.GuidanceNotesDao
import com.ahrokholska.room.data.dao.InterestingIdeaNotesDao
import com.ahrokholska.room.data.dao.PinNoteDao
import com.ahrokholska.room.data.dao.RoutineTasksNotesDao

@Database(
    entities = [com.ahrokholska.room.data.entities.InterestingIdeaNoteEntity::class, com.ahrokholska.room.data.entities.FinishedNoteEntity::class, com.ahrokholska.room.data.entities.PinnedNoteEntity::class,
        com.ahrokholska.room.data.entities.BuySomethingNoteEntity::class, com.ahrokholska.room.data.entities.BuySomethingNoteItemEntity::class,
        com.ahrokholska.room.data.entities.GoalsNoteEntity::class, com.ahrokholska.room.data.entities.GoalsNoteTaskEntity::class, com.ahrokholska.room.data.entities.GoalsNoteSubtaskEntity::class,
        com.ahrokholska.room.data.entities.GuidanceNoteEntity::class,
        com.ahrokholska.room.data.entities.RoutineTasksNoteEntity::class, com.ahrokholska.room.data.entities.RoutineTasksNoteSubNoteEntity::class],
    version = 1,
    exportSchema = true,
)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun interestingIdeaDao(): InterestingIdeaNotesDao
    abstract fun buySomethingNotesDao(): BuySomethingNotesDao
    abstract fun goalsNotesDao(): GoalsNotesDao
    abstract fun guidanceNotesDao(): GuidanceNotesDao
    abstract fun routineTasksNotesDao(): RoutineTasksNotesDao
    abstract fun pinNoteDao(): PinNoteDao
    abstract fun finishNoteDao(): FinishNoteDao
}