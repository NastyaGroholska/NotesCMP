package com.ahrokholska.create_note.presentation.createNote.screenTypes.idea

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ahrokholska.create_note.presentation.createNote.screenTypes.BottomBarSave
import com.ahrokholska.presentation.TopBar
import notescmp.feature.create_note.generated.resources.Res
import notescmp.feature.create_note.generated.resources.new_idea
import notescmp.feature.create_note.generated.resources.your_idea
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun InterestingIdeaNoteScreen(
    viewModel: InterestingIdeaNoteScreenViewModel = koinViewModel(),
    onBackClick: () -> Unit,
    onNoteSaved: () -> Unit
) {
    InterestingIdeaNoteScreenContent(
        title = viewModel.title.collectAsState().value,
        body = viewModel.body.collectAsState().value,
        onTitleChange = viewModel::changeTitle,
        onBodyChange = viewModel::changeBody,
        onBackClick = onBackClick,
        onSaveClick = { title, body -> viewModel.saveNote(title, body, onNoteSaved) }
    )
}

@Composable
internal fun InterestingIdeaNoteScreenContent(
    title: String,
    body: String,
    onBackClick: () -> Unit = {},
    onTitleChange: (String) -> Unit = {},
    onBodyChange: (String) -> Unit = {},
    onSaveClick: (String, String) -> Unit = { _, _ -> },
) {
    val adjustedTitle = title.ifEmpty { stringResource(Res.string.new_idea) }
    val adjustedBody = body.ifEmpty { stringResource(Res.string.your_idea) }
    Scaffold(
        topBar = {
            TopBar(
                modifier = Modifier.statusBarsPadding(),
                onBackClick = onBackClick
            )
        },
        bottomBar = { BottomBarSave { onSaveClick(adjustedTitle, adjustedBody) } }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 24.dp)
                .padding(horizontal = 16.dp)
        ) {
            BasicTextField(
                modifier = Modifier.fillMaxWidth(),
                value = adjustedTitle,
                onValueChange = onTitleChange,
                textStyle = MaterialTheme.typography.displaySmall
            )
            { innerTextField ->
                innerTextField()
            }
            Spacer(modifier = Modifier.height(16.dp))
            BasicTextField(
                modifier = Modifier.fillMaxWidth(),
                value = adjustedBody,
                onValueChange = onBodyChange,
                textStyle = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.secondary)
            )
            { innerTextField ->
                innerTextField()
            }
        }
    }
}

@Composable
private fun InterestingIdeaNoteScreenPreview() {
    InterestingIdeaNoteScreenContent(
        title = "",
        body = "Create a mobile app UI Kit that provide a basic notes functionality but with some improvement. \n" +
                "\n" +
                "There will be a choice to select what kind of notes that user needed, so the experience while taking notes can be unique based on the needs.",
    )
}