package com.ahrokholska.permission

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.mohamedrejeb.calf.permissions.ExperimentalPermissionsApi
import com.mohamedrejeb.calf.permissions.isGranted
import com.mohamedrejeb.calf.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun rememberPermissionHandler(
    permission: Permission,
    rational: String,
    onPermissionGranted: () -> Unit
): PermissionState {
    var showDialog by remember { mutableStateOf(false) }

    val permState = rememberPermissionState(permission.permission) { isGranted ->
        if (!isGranted) {
            showDialog = true
        } else {
            onPermissionGranted()
        }
    }

    if (showDialog) {
        Dialog(
            rational = rational,
            onDismissRequest = { showDialog = false },
            onConfirm = permState::openAppSettings
        )
    }
    return remember(permState.status.isGranted) {
        PermissionState(
            isGranted = permState.status.isGranted,
            launchPermissionRequest = permState::launchPermissionRequest
        )
    }
}