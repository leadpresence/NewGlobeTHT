package com.leadpresence.newglobetht.di

import androidx.room.Room
import com.leadpresence.newglobetht.data.local.dao.PupilDao
import com.leadpresence.newglobetht.data.local.dao.RemoteKeyDao
import com.leadpresence.newglobetht.data.local.dao.database.AppDatabase
import com.leadpresence.newglobetht.data.local.entity.PupilEntity
import com.leadpresence.newglobetht.data.local.mapper.MapperPaging
import com.leadpresence.newglobetht.data.local.mapper.PupilDTOToPupilMapper
import com.leadpresence.newglobetht.data.local.mapper.PupilEntityToPupilMapper
import com.leadpresence.newglobetht.data.repository.PupilRepositoryImpl
import com.leadpresence.newglobetht.domain.model.Pupil
import com.leadpresence.newglobetht.domain.model.PupilDTO
import com.leadpresence.newglobetht.domain.repository.PupilRepository
import com.leadpresence.newglobetht.presentation.ui.addPupil.AddPupilViewModel
import com.leadpresence.newglobetht.presentation.ui.onboarding.OnboardingViewModel
import com.leadpresence.newglobetht.presentation.ui.pupildetail.EditViewModel
import com.leadpresence.newglobetht.presentation.ui.pupildetail.PupilDetailViewModel
import com.leadpresence.newglobetht.presentation.ui.pupils.PupilsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single { PupilEntityToPupilMapper() }
    single { PupilDTOToPupilMapper() }

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "new_globe_db"
        ).build()
    }

    // Provide the DAO
    single<PupilDao> {
        get<AppDatabase>().getImageDao()
    }
    // Provide the DAO
    single<RemoteKeyDao> {
        get<AppDatabase>().getRemoteKeyDao()
    }
    single<PupilRepository> {
        PupilRepositoryImpl(
            pupilEntityToPupilMapper= get(),
            pupilDTOToPupilMapperModule = get(),
            pupilDao = get(),
            apiService = get(),

            remoteKeyDao = get()
        )
    }
    viewModel { OnboardingViewModel() }
    viewModel { PupilsViewModel(get()) }
    viewModel { AddPupilViewModel(get()) }
    viewModel { (pupilId: Long) -> PupilDetailViewModel(pupilId, get()) }
    viewModel { (pupilId: Long) -> EditViewModel(pupilId, get()) }
}










