//package com.leadpresence.newglobetht.presentation.navigation
//
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
////import androidx.compose.ui.graphics.BlendMode.Companion.Screen
//import androidx.compose.ui.unit.dp
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import coil.compose.AsyncImage
//import com.leadpresence.newglobetht.presentation.ui.common.state.UiState
//import com.leadpresence.newglobetht.presentation.ui.onboarding.OnboardingScreen
//import com.leadpresence.newglobetht.presentation.ui.pupils.PupilsScreen
//
//@Composable
//fun AppNavigation() {
//    val navController = rememberNavController()
//
//    NavHost(
//        navController = navController,
//        startDestination = AppScreens.Onboarding.route
//    ) {
//        composable(AppScreens.Onboarding.route) {
//            OnboardingScreen(
//                onGetStarted = {
//                    navController.navigate(AppScreens.PupilsList.route) {
//                        popUpTo(AppScreens.Onboarding.route) { inclusive = true }
//                    }
//                }
//            )
//        }
//
//        composable(AppScreens.PupilsList.route) {
//            val viewModel = getViewModel<PupilsViewModel>()
//            PupilsScreen(
//                viewModel = viewModel,
//                onPupilClick = { id ->
//                    navController.navigate(AppScreens.PupilDetail.createRoute(id))
//                }
//            )
//        }}}