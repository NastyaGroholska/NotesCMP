package com.ahrokholska.note_presentation.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ListAlt
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.Task
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import notescmp.core.note_presentation.generated.resources.Res
import notescmp.core.note_presentation.generated.resources.buying_something
import notescmp.core.note_presentation.generated.resources.buying_something_descr
import notescmp.core.note_presentation.generated.resources.goals
import notescmp.core.note_presentation.generated.resources.goals_descr
import notescmp.core.note_presentation.generated.resources.guidance
import notescmp.core.note_presentation.generated.resources.guidance_descr
import notescmp.core.note_presentation.generated.resources.interesting_idea
import notescmp.core.note_presentation.generated.resources.interesting_idea_descr
import notescmp.core.note_presentation.generated.resources.routine_tasks
import notescmp.core.note_presentation.generated.resources.routine_tasks_descr
import org.jetbrains.compose.resources.StringResource

enum class NoteType(
    val title: StringResource,
    val description: StringResource,
    val icon: ImageVector,
    val color: Color
) {
    InterestingIdea(
        Res.string.interesting_idea,
        Res.string.interesting_idea_descr,
        Icons.Outlined.Lightbulb,
        Color(0xFF6A3EA1)
    ),
    BuyingSomething(
        Res.string.buying_something,
        Res.string.buying_something_descr,
        Icons.Outlined.ShoppingCart,
        Color(0xFF60D889)
    ),
    Goals(Res.string.goals, Res.string.goals_descr, Icons.Outlined.Star, Color(0xFFF8C715)),
    Guidance(
        Res.string.guidance,
        Res.string.guidance_descr,
        Icons.AutoMirrored.Outlined.ListAlt,
        Color(0xFFCE3A54)
    ),
    RoutineTasks(
        Res.string.routine_tasks,
        Res.string.routine_tasks_descr,
        Icons.Outlined.Task,
        Color(0xFFDEDC52)
    )
}