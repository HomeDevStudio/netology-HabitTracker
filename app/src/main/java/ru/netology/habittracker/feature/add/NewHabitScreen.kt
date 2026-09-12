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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun NewHabitScreenRoute(
    modifier: Modifier,
    onBack: () -> Unit,
) {
    val viewModel = viewModel<NewHabitViewModel>()
    val isError by viewModel.errorState.collectAsStateWithLifecycle()
    NewHabitScreen(modifier, onBack, viewModel, isError)
}

@Composable
fun NewHabitScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    viewModel: NewHabitViewModel,
    isError: Boolean
) {
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Column(
        modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        val state = remember { mutableStateOf("") }

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
                    contentDescription = "Назад"
                )
            }

            Text(text = "Новая привычка", fontSize = 24.sp)
            IconButton(
                onClick = {
                    if (!viewModel.validateAndSave(state.value)) {
                        focusRequester.requestFocus()
                    } else {
                        onBack()
                    }
                }
            )
            {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Сохранить"
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            value = state.value,
            onValueChange = {
                state.value = it
                if (isError) viewModel.clearError()
            },
            trailingIcon = {
                IconButton(
                    onClick = { state.value = "" }
                ) {
                    Icon(Icons.Outlined.Cancel, contentDescription = "Отмена")
                }
            },
            isError = isError,
            placeholder = {
                if (isError) {
                    Text("Введите название привычки", color = Color.Red)
                } else {
                    Text("")
                }
            }
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun NewHabitScreenPreview() {
//    HabitTrackerTheme() {
//        NewHabitScreen(onBack = {}, viewModel = viewModel, isError = isError)
//    }
//}