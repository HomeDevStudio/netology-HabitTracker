package ru.netology.habittracker.feature.list

import android.graphics.Paint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.netology.habittracker.ui.theme.HabitTrackerTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HabitCard(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
    ) {
        Column {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 12.dp, start = 24.dp, end = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("3")
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "",
                    Modifier.size(16.dp)
                )
                Text("Пресс качать", Modifier.padding(start = 8.dp))
            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 12.dp, start = 12.dp, end = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularCheckIconButton(
                    checked = true,
                    onCheckedChange = {},
                    modifier = modifier,
                    today = false
                )

                CircularCheckIconButton(
                    checked = false,
                    onCheckedChange = {},
                    modifier = modifier,
                    today = false
                )

                CircularCheckIconButton(
                    checked = true,
                    onCheckedChange = {},
                    modifier = modifier,
                    today = true
                )

                CircularCheckIconButton(
                    checked = false,
                    onCheckedChange = {},
                    modifier = modifier,
                    today = false
                )

                CircularCheckIconButton(
                    checked = false,
                    onCheckedChange = {},
                    modifier = modifier,
                    today = false
                )

                CircularCheckIconButton(
                    checked = false,
                    onCheckedChange = {},
                    modifier = modifier,
                    today = false
                )

                CircularCheckIconButton(
                    checked = false,
                    onCheckedChange = {},
                    modifier = modifier,
                    today = false
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HabitCardPreview() {
    HabitTrackerTheme() {
        HabitCard()
    }
}