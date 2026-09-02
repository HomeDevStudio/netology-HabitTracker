package ru.netology.habittracker.feature.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.netology.habittracker.ui.theme.HabitColors
import ru.netology.habittracker.ui.theme.HabitTrackerTheme

@Composable
fun CircularCheckIconButton(
    checked: Boolean,
    onCheckedChange: () -> Unit,
    modifier: Modifier = Modifier,
    today: Boolean
) {
    val backgroundColor = if (today) HabitColors().lilac else Color.LightGray
    val iconTint = if (checked) {
        if (today) {
            Color.DarkGray
        } else {
            Color.Gray
        }
    } else {
        Color.LightGray
    }

    Box(
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable(onClick = onCheckedChange),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = "Check",
            tint = iconTint
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CircularCheckIconButtonPreview() {
    HabitTrackerTheme() {
        Row() {
            CircularCheckIconButton(
                checked = false,
                onCheckedChange = {},
                today = false
            )

            CircularCheckIconButton(
                checked = true,
                onCheckedChange = {},
                today = false
            )

            CircularCheckIconButton(
                checked = true,
                onCheckedChange = {},
                today = true
            )
        }
    }
}