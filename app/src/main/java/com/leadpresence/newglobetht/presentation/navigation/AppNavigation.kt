package com.leadpresence.newglobetht.presentation.navigation

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.leadpresence.newglobetht.presentation.ui.onboarding.OnboardingScreen
import com.leadpresence.newglobetht.presentation.ui.onboarding.OnboardingViewModel
import com.leadpresence.newglobetht.presentation.ui.pupildetail.EditScreen
import com.leadpresence.newglobetht.presentation.ui.pupildetail.EditViewModel
import com.leadpresence.newglobetht.presentation.ui.pupildetail.PupilDetailScreen
import com.leadpresence.newglobetht.presentation.ui.pupildetail.PupilDetailViewModel
import com.leadpresence.newglobetht.presentation.ui.pupils.PupilsScreen
import com.leadpresence.newglobetht.presentation.ui.pupils.PupilsViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppScreens.Onboarding.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(AppScreens.Onboarding.route) {
            val viewModel: OnboardingViewModel = koinViewModel()
            OnboardingScreen(
                  viewModel= viewModel,
                onGetStartedClick = {
                    navController.navigate(AppScreens.PupilsScreen.route) {
                        // Pop up to onboarding and remove it from back stack
                        popUpTo(AppScreens.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }

        composable(AppScreens.PupilsScreen.route) {
            val viewModel: PupilsViewModel = koinViewModel()

            PupilsScreen(
                  viewModel= viewModel ,
                onPupilClick = { pupilId ->
                    navController.navigate(AppScreens.PupilDetail.createRoute(pupilId))
                }
            )
        }

        composable(
            route = AppScreens.PupilDetail.route,
            arguments = listOf(
                navArgument("pupilId") {
                    type = NavType.LongType
                }
            )
        ) { backStackEntry ->
            val pupilId = backStackEntry.arguments?.getLong("pupilId") ?: return@composable
            val viewModel: PupilDetailViewModel = koinViewModel()

            PupilDetailScreen(
                viewModel=viewModel,
                pupilId = pupilId,
                onEditClick = {
                    navController.navigate(AppScreens.EditPupil.createRoute(pupilId))
                },
                onBackClick = {
                    navController.popBackStack()
                },

            )
        }

        composable(
            route = AppScreens.EditPupil.route,
            arguments = listOf(
                navArgument("pupilId") {
                    type = NavType.LongType
                }
            )
        ) { backStackEntry ->
            val pupilId = backStackEntry.arguments?.getLong("pupilId") ?: return@composable
            val viewModel: EditViewModel = koinViewModel()

            EditScreen(
                viewModel = viewModel,
                pupilId = pupilId,
                onSaveClick = {
                    // Pop back to details screen
                    navController.popBackStack()
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}