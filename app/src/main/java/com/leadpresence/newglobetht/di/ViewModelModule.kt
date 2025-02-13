package com.leadpresence.newglobetht.di

import com.leadpresence.newglobetht.presentation.ui.onboarding.OnboardingViewModel
import com.leadpresence.newglobetht.presentation.ui.pupildetail.EditViewModel
import com.leadpresence.newglobetht.presentation.ui.pupildetail.PupilDetailViewModel
import com.leadpresence.newglobetht.presentation.ui.pupils.PupilsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { OnboardingViewModel() }
    viewModel { PupilsViewModel(get()) }
    viewModel { (pupilId: Long) -> PupilDetailViewModel(pupilId, get()) }
    viewModel { (pupilId: Long) -> EditViewModel(pupilId, get()) }
}