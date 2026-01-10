package com.ahrokholska.api.model

import com.ahrokholska.api.model.Note.Goals.Task
import com.ahrokholska.api.model.Note.RoutineTasks.SubNote

sealed class NoteCompact {
    abstract val id: Int

    data class InterestingIdea(
        override val id: Int = 0,
        val title: String = "",
        val body: String = "",
    ) : NoteCompact()

    data class BuyingSomething(
        override val id: Int = 0,
        val title: String = "",
        val items: List<Pair<Boolean, String>> = listOf()
    ) : NoteCompact()

    data class Goals(
        override val id: Int = 0,
        val title: String = "",
        val tasks: List<Pair<Task, List<Task>>> = listOf(),
    ) : NoteCompact()

    data class Guidance(
        override val id: Int = 0,
        val title: String = "",
        val body: String = "",
        val image: String = "",
    ) : NoteCompact()

    data class RoutineTasks(
        override val id: Int = 0,
        val active: List<SubNote> = listOf(),
        val completed: List<SubNote> = listOf(),
    ) : NoteCompact()
}