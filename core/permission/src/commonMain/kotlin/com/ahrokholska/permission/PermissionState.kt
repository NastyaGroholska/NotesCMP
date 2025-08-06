package com.ahrokholska.permission

import androidx.compose.runtime.Stable

@Stable
class PermissionState(var isGranted: Boolean, val launchPermissionRequest: () -> Unit)