package com.leadpresence.newglobetht.di

import androidx.room.Room
import com.leadpresence.newglobetht.data.local.dao.PupilDao
//import com.leadpresence.newglobetht.data.local.dao.RemoteKeyDao
import com.leadpresence.newglobetht.data.local.dao.database.AppDatabase
//import com.leadpresence.newglobetht.data.local.mapper.PupilDTOToPupilMapper
//import com.leadpresence.newglobetht.data.local.mapper.PupilEntityToPupilMapper
import com.leadpresence.newglobetht.data.remote.network.PupilApi
import com.leadpresence.newglobetht.data.repository.PupilRepositoryImpl
import com.leadpresence.newglobetht.domain.repository.PupilRepository
import com.leadpresence.newglobetht.domain.usecase.PupilUseCases
import com.leadpresence.newglobetht.presentation.ui.PupilViewModel
import com.leadpresence.newglobetht.presentation.ui.addPupil.AddPupilViewModel
import com.leadpresence.newglobetht.presentation.ui.onboarding.OnboardingViewModel
import com.leadpresence.newglobetht.presentation.ui.pupildetail.EditViewModel
import com.leadpresence.newglobetht.presentation.ui.pupildetail.PupilDetailViewModel
import com.leadpresence.newglobetht.presentation.ui.pupils.PupilsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "pupil-db"
        ).build()
    }
    single<PupilDao> {
        get<AppDatabase>().getImageDao()
    }

    single<PupilApi> {
        Retrofit.Builder()
            .baseUrl("https://androidtechnicaltestapi-test.bridgeinternationalacademies.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PupilApi::class.java)
    }

    single<PupilRepository> { PupilRepositoryImpl(get(), get()) }
    factory { PupilUseCases(get()) }

    viewModel { PupilViewModel(get()) }


    viewModel { OnboardingViewModel() }
    viewModel { PupilsViewModel(get()) }
    viewModel { AddPupilViewModel(get()) }
    viewModel { (pupilId: Long) -> PupilDetailViewModel(pupilId, get()) }
    viewModel { (pupilId: Long) -> EditViewModel(pupilId, get()) }
}









