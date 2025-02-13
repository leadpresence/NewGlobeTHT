package com.leadpresence.newglobetht.di

import com.leadpresence.newglobetht.domain.repository.PupilRepository
import com.leadpresence.newglobetht.data.repository.PupilRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<PupilRepository> {
        PupilRepositoryImpl(
            pupilDao = get(),
            apiService = get(),
            pupilEntityToPupilMapper= get(),
            mapper = get(),
            remoteKeyDao = get()

        )
    }
}