package com.leadpresence.newglobetht.di

import com.leadpresence.newglobetht.data.local.entity.PupilEntity
import com.leadpresence.newglobetht.data.local.mapper.MapperPaging
import com.leadpresence.newglobetht.data.local.mapper.PupilToPupilEntityMappper
import com.leadpresence.newglobetht.domain.model.Pupil
import org.koin.dsl.module

val mapperModule = module {
    single<MapperPaging<Pupil, PupilEntity>> { PupilToPupilEntityMappper() }
}