package ru.netology.habittracker.feature.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.netology.habittracker.ui.theme.HabitTrackerTheme

@Composable
fun WeekProgressBar(modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DayProgressItem(day = "Пн", progress = 1f)
        DayProgressItem(day = "Вт", progress = 1f)
        DayProgressItem(day = "Сегодня", progress = 0.5f)
        DayProgressItem(day = "Чт", progress = 0f)
        DayProgressItem(day = "Пт", progress = 0f)
        DayProgressItem(day = "Сб", progress = 0f)
        DayProgressItem(day = "Вс", progress = 0f)
    }
}

@Preview(showBackground = true)
@Composable
fun WeekProgressBarPreview() {
    HabitTrackerTheme() {
        WeekProgressBar(Modifier)
    }
}