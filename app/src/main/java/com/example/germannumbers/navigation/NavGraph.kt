package com.example.germannumbers.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.germannumbers.ui.inputnumber.NumberScreen
import com.example.germannumbers.ui.main.MainScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.Main.route
    ) {
        composable(route = Screens.Main.route) {
            MainScreen(viewModel(), navController)
        }
        composable(route = Screens.Number.route) {
            NumberScreen(viewModel(), navController)
        }
        composable(route = Screens.Time.route) {
//            TimeScreen(navController)
        }
//        composable(route = Screens.Detail.route){
//            DetailScreen()
//        }
    }
}
