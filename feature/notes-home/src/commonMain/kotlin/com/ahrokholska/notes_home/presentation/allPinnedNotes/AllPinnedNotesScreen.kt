package com.ahrokholska.notes_home.presentation.allPinnedNotes

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ahrokholska.note_presentation.composable.NotePreviewCard
import com.ahrokholska.note_presentation.composable.NotesGrid
import com.ahrokholska.note_presentation.model.NotePreview
import com.ahrokholska.note_presentation.model.NoteType
import com.ahrokholska.note_presentation.theme.noteColors
import com.ahrokholska.presentation.TopBarWithTitle
import notescmp.feature.notes_home.generated.resources.Res
import notescmp.feature.notes_home.generated.resources.pinned_notes
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun AllPinnedNotesScreen(
    viewModel: AllPinnedNotesScreenViewModel,
    onBackClick: () -> Unit,
    onNoteClick: (Int, NoteType) -> Unit
) {
    AllPinnedNotesScreenContent(
        notes = viewModel.notes.collectAsState().value,
        onBackClick = onBackClick,
        onNoteClick = onNoteClick
    )
}

@Composable
internal fun AllPinnedNotesScreenContent(
    notes: List<NotePreview>,
    onBackClick: () -> Unit = {},
    onNoteClick: (Int, NoteType) -> Unit = { _, _ -> }
) {
    NotesGrid(
        background = Color.Transparent,
        topBar = {
            TopBarWithTitle(
                modifier = Modifier.statusBarsPadding(),
                title = stringResource(Res.string.pinned_notes),
                onBackClick = onBackClick
            )
        }
    ) {
        items(notes) { note ->
            NotePreviewCard(
                note = note,
                onNoteClick = onNoteClick,
                shouldShowNoteType = true
            )
        }
    }
}

@Composable
internal fun AllPinnedNotesScreenPreview() {
    AllPinnedNotesScreenContent(
        listOf(
            NotePreview.InterestingIdea(
                title = "\uD83D\uDCA1 New Product Idea Design",
                body = "Create a mobile app UI",
                color = noteColors[4]
            ),
            NotePreview.InterestingIdea(
                title = "\uD83D\uDCA1 New Product Idea Design aaaaaaaaaaa\naaa\naaaaa aaaaaaaaaaaaaaa",
                body = "Create a mobile app UI",
                color = noteColors[4]
            ),
            NotePreview.InterestingIdea(
                title = "\uD83D\uDCA1 New Product Idea Design aaaaaaaaaaaaaaaaaaa",
                body = "Create a mobile app UI",
                color = noteColors[4]
            ),
            NotePreview.InterestingIdea(
                title = "\uD83D\uDCA1 New Product Idea Design aaaaaaaaaaaaaaaaaaa",
                body = "Create a mobile app UI",
                color = noteColors[4]
            ),
            NotePreview.InterestingIdea(
                title = "\uD83D\uDCA1 New Product Idea Design aaaaaaaaaaaaaaaaaaa",
                body = "Create a mobile app UI",
                color = noteColors[4]
            ),
            NotePreview.InterestingIdea(
                title = "\uD83D\uDCA1 New Product Idea Design aaaaaaaaaaaaaaaaaaa",
                body = "Create a mobile app UI",
                color = noteColors[4]
            ),
            NotePreview.InterestingIdea(
                title = "\uD83D\uDCA1 New Product Idea Design aaaaaaaaaaaaaaaaaaa",
                body = "Create a mobile app UI",
                color = noteColors[4]
            ),
            NotePreview.InterestingIdea(
                title = "\uD83D\uDCA1 New Product Idea Design aaaaaaaaaaaaaaaaaaa",
                body = "Create a mobile app UI",
                color = noteColors[4]
            ),
            NotePreview.InterestingIdea(
                title = "\uD83D\uDCA1 New Product Idea Design aaaaaaaaaaaaaaaaaaa",
                body = "Create a mobile app UI",
                color = noteColors[4]
            ),
            NotePreview.InterestingIdea(
                title = "\uD83D\uDCA1 New Product Idea Design aaaaaaaaaaaaaaaaaaa",
                body = "Create a mobile app UI",
                color = noteColors[4]
            ),
            NotePreview.InterestingIdea(
                title = "\uD83D\uDCA1 New Product Idea Design aaaaaaaaaaaaaaaaaaa",
                body = "Create a mobile app UI",
                color = noteColors[4]
            ),
        )
    )
}