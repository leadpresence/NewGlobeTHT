package com.leadpresence.newglobetht.di

import com.leadpresence.newglobetht.data.local.entity.PupilEntity
import com.leadpresence.newglobetht.data.local.mapper.MapperPaging
import com.leadpresence.newglobetht.data.local.mapper.PupilDTOToPupilMapper
import com.leadpresence.newglobetht.data.local.mapper.PupilToPupilEntityMappper
import com.leadpresence.newglobetht.domain.model.Pupil
import com.leadpresence.newglobetht.domain.model.PupilDTO
import org.koin.dsl.module

val mapperModule = module {
    single<MapperPaging<Pupil, PupilEntity>> { PupilToPupilEntityMappper() }
}

val pupilEntityToPupilMapperModule= module{
    single<MapperPaging<PupilDTO, Pupil>> { PupilDTOToPupilMapper() }

}