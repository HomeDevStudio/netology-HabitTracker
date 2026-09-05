package ru.netology.habittracker.feature.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.netology.habittracker.ui.theme.HabitTrackerTheme

@Composable
fun NewHabitScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        TopBar(onBack)
        Spacer(Modifier.height(16.dp))
        HabitInputField()
    }
}

@Composable
private fun TopBar(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = onBack
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Arrow back"
            )
        }

        Text(text = "Новая привычка", fontSize = 24.sp)
        IconButton(
            onClick = onBack
        )
        {
            Icon(
                imageVector = Icons.Default.Done,
                contentDescription = "Save"
            )
        }
    }
}

@Composable
fun HabitInputField(modifier: Modifier = Modifier) {
    val state = remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(),
        value = state.value,
        onValueChange = { state.value = it },
        label = { Text("Название") },
        trailingIcon = {
            IconButton(
                onClick = { state.value = "" }
            ) {
                Icon(Icons.Outlined.Cancel, contentDescription = "Cancel")
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun NewHabitScreenPreview() {
    HabitTrackerTheme() {
        NewHabitScreen({}, Modifier)
    }
}