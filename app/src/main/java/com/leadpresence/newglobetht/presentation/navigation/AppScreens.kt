package com.leadpresence.newglobetht.presentation.navigation




sealed class AppScreens(val route: String) {
    object Onboarding : AppScreens("onboarding")

    object PupilsScreen : AppScreens("home")
    object PupilDetail : AppScreens("pupil/{pupilId}") {
        fun createRoute(pupilId: Long) = "pupil/$pupilId"
    }
    object AddPupilScreen : AppScreens("add_pupil")
    object EditPupil : AppScreens("edit_pupil/{pupilId}") {
        fun createRoute(pupilId: Long) = "edit_pupil/$pupilId"
    }
}


