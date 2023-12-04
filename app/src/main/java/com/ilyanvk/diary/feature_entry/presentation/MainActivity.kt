package com.ilyanvk.diary.feature_entry.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ilyanvk.diary.feature_entry.presentation.add_edit_entry.AddEditEntryScreen
import com.ilyanvk.diary.feature_entry.presentation.entries.EntriesScreen
import com.ilyanvk.diary.feature_entry.presentation.util.Screen
import com.ilyanvk.diary.ui.theme.DiaryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiaryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController, startDestination = Screen.EntriesScreen.route
                    ) {
                        composable(route = Screen.EntriesScreen.route) {
                            EntriesScreen(navController = navController)
                        }
                        composable(route = Screen.AddEditEntryScreen.route + "?entryId={entryId}",
                            arguments = listOf(navArgument("entryId") {
                                type = NavType.StringType
                                defaultValue = ""
                            }
                            )) {
                            AddEditEntryScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}
