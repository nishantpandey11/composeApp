package com.example.hilt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hilt.screens.CategoryScreen
import com.example.hilt.screens.TweetListScreen
import com.example.hilt.ui.theme.HiltTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HiltTheme {
                MainApp()
            }
        }
    }

    @Composable
    fun MainApp() {
        Column() {
            Card(
                elevation = 4.dp,
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 16.dp)
            ) {
                Text(
                    text = "ComposeMVVMHilt",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier
                        .padding(8.dp, 16.dp)
                        .fillMaxWidth(1f)
                )
            }


            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "category") {
                composable(route = "category") {
                    CategoryScreen(navController)
                }
                composable(route = "detail/{cat}", arguments = listOf(
                    navArgument("cat") {
                        type = NavType.StringType
                    }
                )) {
                    TweetListScreen(navController, it.arguments?.getString("cat")!!)
                }
            }
        }
    }

}
