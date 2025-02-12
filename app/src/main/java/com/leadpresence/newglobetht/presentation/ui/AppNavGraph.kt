package com.leadpresence.newglobetht.presentation.ui

import com.leadpresence.newglobetht.domain.repository.PupilRepository

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


const val POST_ID = "postId"
sealed class AppDestinations(val route: String) {
    object Home : AppDestinations("home")
    object PupilDetail : AppDestinations("pupil/{pupilId}") {
        fun createRoute(pupilId: Long) = "pupil/$pupilId"
    }
    object AddPupil : AppDestinations("add_pupil")
    object EditPupil : AppDestinations("edit_pupil/{pupilId}") {
        fun createRoute(pupilId: Long) = "edit_pupil/$pupilId"
    }
}
@Composable
fun AppNavGraph(
    appContainer: PupilRepository,
    isExpandedScreen: Boolean,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    openDrawer: () -> Unit = {},
    startDestination: String = AppDestinations.Home.route,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        composable(AppDestinations.Home.route) {
            Pu(
                onPupilClick = { pupilId ->
                    navController.navigate(AppDestinations.PupilDetail.createRoute(pupilId))
                },
                onAddPupilClick = {
                    navController.navigate(Screen.AddPupil.route)
                }
            )
        }

        composable(
            route = Screen.PupilDetail.route,
            arguments = listOf(
                navArgument("pupilId") { type = NavType.LongType }
            )
        ) { backStackEntry ->
            val pupilId = backStackEntry.arguments?.getLong("pupilId") ?: return@composable
            PupilDetailScreen(
                pupilId = pupilId,
                onEditClick = {
                    navController.navigate(Screen.EditPupil.createRoute(pupilId))
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.AddPupil.route) {
            AddPupilScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = Screen.EditPupil.route,
            arguments = listOf(
                navArgument("pupilId") { type = NavType.LongType }
            )
        ) { backStackEntry ->
            val pupilId = backStackEntry.arguments?.getLong("pupilId") ?: return@composable
            EditPupilScreen(
                pupilId = pupilId,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

    }
}