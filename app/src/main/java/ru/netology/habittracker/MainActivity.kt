package ru.netology.habittracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.netology.habittracker.feature.add.NewHabitScreenRoute
import ru.netology.habittracker.feature.list.HabitListScreenRoute
import ru.netology.habittracker.ui.theme.HabitColors

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "home") {
                composable("home") {
                    Scaffold(
                        floatingActionButton = {
                            FloatingActionButton(
                                onClick = { navController.navigate("newHabit") },
                                containerColor = HabitColors().lilac,
                                contentColor = Color.DarkGray
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Добавить"
                                )
                            }
                        }
                    ) { innerPadding ->
                        HabitListScreenRoute(Modifier.padding(innerPadding))
                    }
                }

                composable("newHabit") {
                    NewHabitScreenRoute(
                        onBack = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}