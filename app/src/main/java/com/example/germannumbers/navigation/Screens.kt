package com.example.germannumbers.navigation

import com.example.germannumbers.common.Constants

sealed class Screens(val route: String) {
    object Main : Screens(Constants.MAIN_SCREEN)
    object Number : Screens(Constants.NUMBER_SCREEN)
    object Time : Screens(Constants.TIME_SCREEN)
}
