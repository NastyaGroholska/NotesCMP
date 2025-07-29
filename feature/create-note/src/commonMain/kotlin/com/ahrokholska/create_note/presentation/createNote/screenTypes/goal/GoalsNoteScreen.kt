package com.ahrokholska.create_note.presentation.createNote.screenTypes.goal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.ahrokholska.create_note.presentation.createNote.screenTypes.BottomBarSave
import com.ahrokholska.create_note.presentation.createNote.screenTypes.TextAndError
import com.ahrokholska.presentation.TopBar
import notescmp.feature.create_note.generated.resources.Res
import notescmp.feature.create_note.generated.resources.add_main_task
import notescmp.feature.create_note.generated.resources.add_subtask
import notescmp.feature.create_note.generated.resources.empty_name
import notescmp.feature.create_note.generated.resources.enter_item_name
import notescmp.feature.create_note.generated.resources.goal
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun GoalsNoteScreen(
    viewModel: GoalsNoteScreenViewModel = koinViewModel(),
    onBackClick: () -> Unit,
    onNoteSaved: () -> Unit
) {
    GoalsNoteScreenContent(
        title = viewModel.title.collectAsState().value,
        items = viewModel.items.collectAsState().value,
        onAddMainItemClick = viewModel::addMainItem,
        onAddSubItemClick = viewModel::addSubItem,
        onTitleChange = viewModel::changeTitle,
        onMainItemChange = viewModel::mainItemChange,
        onSubItemChange = viewModel::subItemChange,
        onBackClick = onBackClick,
        onSaveClick = { title ->
            viewModel.saveNote(title, onNoteSaved)
        }
    )
}

@Composable
internal fun GoalsNoteScreenContent(
    title: String,
    items: List<Pair<TextAndError, List<TextAndError>>>,
    onBackClick: () -> Unit = {},
    onAddMainItemClick: () -> Unit = {},
    onAddSubItemClick: (Int) -> Unit = {},
    onTitleChange: (String) -> Unit = {},
    onMainItemChange: (String, Int) -> Unit = { _, _ -> },
    onSubItemChange: (String, Int, Int) -> Unit = { _, _, _ -> },
    onSaveClick: (String) -> Unit = { },
) {
    val adjustedTitle = title.ifEmpty { stringResource(Res.string.goal) }
    Scaffold(
        topBar = {
            TopBar(
                modifier = Modifier.statusBarsPadding(),
                onBackClick = onBackClick
            )
        },
        bottomBar = { BottomBarSave { onSaveClick(adjustedTitle) } }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 24.dp)
                .padding(horizontal = 16.dp)
        ) {
            item {
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
            }
            items.forEachIndexed { index1, item ->
                item {
                    Task(value = item.first.text, onValueChange = { onMainItemChange(it, index1) })
                }
                if (item.first.error) {
                    item {
                        Text(
                            modifier = Modifier.padding(start = 60.dp),
                            text = stringResource(Res.string.empty_name),
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
                itemsIndexed(item.second) { index2, subItem ->
                    Column {
                        Task(
                            modifier = Modifier.padding(start = 36.dp),
                            value = subItem.text,
                            onValueChange = { onSubItemChange(it, index1, index2) }
                        )
                        if (subItem.error) {
                            Text(
                                modifier = Modifier.padding(start = 60.dp),
                                text = stringResource(Res.string.empty_name),
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        modifier = Modifier
                            .padding(start = 36.dp)
                            .padding(horizontal = 16.dp)
                            .clickable { onAddSubItemClick(index1) },
                        text = stringResource(Res.string.add_subtask),
                        color = MaterialTheme.colorScheme.primary,
                        style = TextStyle(textDecoration = TextDecoration.Underline)
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .clickable { onAddMainItemClick() },
                    text = stringResource(Res.string.add_main_task),
                    color = MaterialTheme.colorScheme.primary,
                    style = TextStyle(textDecoration = TextDecoration.Underline)
                )
            }
        }
    }
}

@Composable
private fun Task(modifier: Modifier = Modifier, value: String, onValueChange: (String) -> Unit) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = false, onCheckedChange = {})
        Spacer(modifier = Modifier.width(12.dp))
        BasicTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            textStyle = MaterialTheme.typography.bodyLarge
        )
        { innerTextField ->
            Box(contentAlignment = Alignment.CenterStart) {
                if (value.isEmpty()) {
                    Text(
                        text = stringResource(Res.string.enter_item_name),
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
                innerTextField()
            }
        }
    }
}

@Composable
private fun GoalsNoteScreenPreview() {
    GoalsNoteScreenContent(
        title = "",
        items = listOf(
            TextAndError("A") to listOf(
                TextAndError("A box of instant noodles"),
                TextAndError("3 boxes of coffee"),
                TextAndError("")
            ),
            TextAndError("B") to listOf(TextAndError(""))
        )
    )
}