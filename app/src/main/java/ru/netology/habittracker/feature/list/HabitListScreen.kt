package ru.netology.habittracker.feature.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.netology.habittracker.ui.theme.HabitTrackerTheme

@Composable
fun HabitListScreen(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp)
    ) {
        Spacer(Modifier.height(16.dp))
        WeekProgressBar(Modifier.padding(start = 8.dp, end = 8.dp))
        Spacer(Modifier.height(16.dp))
        HabitCard()
    }
}

@Preview(showBackground = true)
@Composable
fun HabitListScreenPreview() {
    HabitTrackerTheme() {
        HabitListScreen(Modifier)
    }
}