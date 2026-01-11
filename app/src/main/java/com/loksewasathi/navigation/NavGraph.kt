package com.loksewasathi.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.loksewasathi.ui.dashboard.DashboardScreen
import com.loksewasathi.ui.quiz.QuizScreen
import com.loksewasathi.ui.result.ResultScreen
import com.loksewasathi.ui.review.ReviewScreen
import com.loksewasathi.ui.splash.SplashScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        enterTransition = {
            fadeIn(animationSpec = tween(300)) + slideInHorizontally(
                initialOffsetX = { 1000 },
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            fadeOut(animationSpec = tween(300)) + slideOutHorizontally(
                targetOffsetX = { -1000 },
                animationSpec = tween(300)
            )
        }
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onNavigateToDashboard = {
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }
        
        composable(Screen.Dashboard.route) {
            DashboardScreen(
                onTopicClick = { topicId ->
                    navController.navigate(Screen.Quiz.createRoute(topicId))
                }
            )
        }
        
        composable(
            route = Screen.Quiz.route,
            arguments = listOf(navArgument("topicId") { type = NavType.StringType })
        ) { backStackEntry ->
            val topicId = backStackEntry.arguments?.getString("topicId") ?: return@composable
            QuizScreen(
                topicId = topicId,
                onNavigateToResult = {
                    navController.navigate(Screen.Result.route) {
                        popUpTo(Screen.Dashboard.route)
                    }
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
        
        composable(Screen.Result.route) {
            ResultScreen(
                onNavigateToReview = {
                    navController.navigate(Screen.Review.route)
                },
                onNavigateToDashboard = {
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Dashboard.route) { inclusive = true }
                    }
                },
                onRetakeQuiz = {
                    navController.popBackStack()
                }
            )
        }
        
        composable(Screen.Review.route) {
            ReviewScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
