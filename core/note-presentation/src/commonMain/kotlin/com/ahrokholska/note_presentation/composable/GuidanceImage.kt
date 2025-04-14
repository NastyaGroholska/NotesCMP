package com.ahrokholska.note_presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import notescmp.core.note_presentation.generated.resources.Res
import notescmp.core.note_presentation.generated.resources.laptop
import org.jetbrains.compose.resources.painterResource

@Composable
fun GuidanceImage(modifier: Modifier = Modifier, image: String) {
    if (image.isEmpty()) {
        Image(
            modifier = modifier,
            painter = painterResource(Res.drawable.laptop),
            contentDescription = null
        )
    } else {
        SubcomposeAsyncImage(
            modifier = modifier,
            model = image,
            loading = { CircularProgressIndicator() },
            error = {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Filled.Error),
                    contentDescription = null
                )
            },
            contentDescription = null,
        )
    }
}

@Composable
internal fun GuidanceImagePreview() {
    GuidanceImage(modifier = Modifier.clip(RoundedCornerShape(12.dp)), image = "")
}