package com.ahrokholska.permission

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import notescmp.core.permission.generated.resources.Res
import notescmp.core.permission.generated.resources.confirm
import notescmp.core.permission.generated.resources.dismiss
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun Dialog(rational: String, onDismissRequest: () -> Unit, onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(onClick = {
                onConfirm()
                onDismissRequest()
            }) {
                Text(stringResource(Res.string.confirm))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(stringResource(Res.string.dismiss))
            }
        },
        text = { Text(rational) })
}

@Composable
private fun DialogPreview() {
    Dialog(rational = "Unable to change picture without access to gallery", {}, {})
}