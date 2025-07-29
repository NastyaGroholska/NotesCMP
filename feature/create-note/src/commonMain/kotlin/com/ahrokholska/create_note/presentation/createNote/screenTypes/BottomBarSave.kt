package com.ahrokholska.create_note.presentation.createNote.screenTypes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import notescmp.feature.create_note.generated.resources.Res
import notescmp.feature.create_note.generated.resources.save
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun BottomBarSave(onClick: () -> Unit = {}) {
    Column(modifier = Modifier.navigationBarsPadding(), horizontalAlignment = Alignment.End) {
        HorizontalDivider()
        Row(
            modifier = Modifier
                .clickable { onClick() }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Filled.Done,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.width(5.dp))
            Text(text = stringResource(Res.string.save), color = MaterialTheme.colorScheme.primary)
        }
    }
}

@Composable
private fun BottomBarSavePreview() {
    BottomBarSave()
}