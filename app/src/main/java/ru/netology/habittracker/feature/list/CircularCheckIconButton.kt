package ru.netology.habittracker.feature.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.netology.habittracker.ui.theme.HabitColors
import ru.netology.habittracker.ui.theme.HabitTrackerTheme

@Composable
fun CustomCheckbox(
    state: Boolean,
    onClick: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    today: Boolean
) {
    val animatedColor by animateColorAsState(
        targetValue = if (today) HabitColors().lilac else Color.LightGray
    )

    val iconTint by animateColorAsState(
        targetValue = if (state) {
            if (today) {
                Color.DarkGray
            } else {
                Color.Gray
            }
        } else {
            Color.LightGray
        }
    )

    Box(
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(animatedColor, CircleShape)
            .clickable { onClick(!state) },
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(visible = state) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Check",
                tint = iconTint
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CircularCheckIconButtonPreview() {
    HabitTrackerTheme() {
        Row() {
            CustomCheckbox(
                state = false,
                onClick = {},
                today = false
            )

            CustomCheckbox(
                state = true,
                onClick = {},
                today = false
            )

            CustomCheckbox(
                state = true,
                onClick = {},
                today = true
            )
        }
    }
}