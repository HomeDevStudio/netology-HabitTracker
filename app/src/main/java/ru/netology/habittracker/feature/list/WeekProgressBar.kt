package ru.netology.habittracker.feature.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.netology.habittracker.ui.theme.HabitTrackerTheme
import java.time.LocalDate

@Composable
fun WeekProgressBar(
    modifier: Modifier = Modifier,
    progress: List<Float>
) {
    val todayIndex = remember { LocalDate.now().dayOfWeek.value - 1 }
    val dayNames = listOf("Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс")

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        (0..6).forEach { index ->
            val label = if (index == todayIndex) "Сегодня" else dayNames[index]
            DayProgressItem(
                day = label,
                progress = progress.getOrElse(index) { 0f }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeekProgressBarPreview() {
    HabitTrackerTheme() {
        WeekProgressBar(progress = listOf(0f, 0.25f, 0.5f, 0.75f, 1f, 1f, 1f))
    }
}