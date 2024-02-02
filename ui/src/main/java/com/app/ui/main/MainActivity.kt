package com.app.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.ui.R
import com.app.ui.detail.DetailScreen
import com.app.ui.programs.ProgramsScreen
import com.app.ui.programs.model.NavigateTo
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.core.annotation.KoinExperimentalAPI

class MainActivity : ComponentActivity() {

    @OptIn(KoinExperimentalAPI::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            KoinAndroidContext {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(stringResource(R.string.app_name))
                            }
                        )
                    },
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = Destination.Programs.route
                        )
                        {
                            composable(Destination.Programs.route) {
                                ProgramsScreen(
                                    navController,
                                    modifier = Modifier.padding(innerPadding)
                                )
                            }
                            composable(
                                Destination.Detail.route
                            ) {
                                val result = navController.previousBackStackEntry?.savedStateHandle?.get<NavigateTo>(
                                    "navigateTo"
                                )
                                result?.let {
                                    DetailScreen(
                                        (it as NavigateTo.DetailPage).urlPage,
                                        navController
                                    )
                                }
                            }       
                        }
                    }
                }
            }

        }
    }
}


sealed class Destination(val route: String) {
    data object Programs : Destination("ProgramList")
    data object Detail : Destination("Detail")
}