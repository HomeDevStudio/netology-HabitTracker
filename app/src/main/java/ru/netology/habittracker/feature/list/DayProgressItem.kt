package ru.netology.habittracker.feature.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.netology.habittracker.ui.theme.HabitTrackerTheme

@Composable
fun DayProgressItem(
    modifier: Modifier = Modifier,
    day: String,
    progress: Float
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = day)
        Spacer(Modifier.height(8.dp))
        CircularProgressIndicator(
            progress = { progress },
//            color = ProgressIndicatorDefaults.circularColor,
//            strokeWidth = ProgressIndicatorDefaults.CircularStrokeWidth,
//            trackColor = ProgressIndicatorDefaults.circularTrackColor,
//            strokeCap = ProgressIndicatorDefaults.CircularDeterminateStrokeCap,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DayProgressItemPreview() {
    HabitTrackerTheme() {
        DayProgressItem(modifier = Modifier, day = "Пн", 0.8f)
    }
}