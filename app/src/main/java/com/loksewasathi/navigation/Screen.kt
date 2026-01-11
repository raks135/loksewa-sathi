package com.loksewasathi.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Dashboard : Screen("dashboard")
    object Quiz : Screen("quiz/{topicId}") {
        fun createRoute(topicId: String) = "quiz/$topicId"
    }
    object Result : Screen("result")
    object Review : Screen("review")
}
