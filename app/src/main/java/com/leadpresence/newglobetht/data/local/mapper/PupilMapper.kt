package com.leadpresence.newglobetht.data.local.mapper
//
//import com.leadpresence.newglobetht.data.local.entity.PupilEntity
//import com.leadpresence.newglobetht.domain.model.Pupil
//
//
//
//class PupilMapper {
//
//    fun toDomain(entity: PupilEntity): Pupil {
//        return Pupil(
//            id = entity.id,
//            pupilId = entity.pupilId,
//            name = entity.name,
//            image = entity.image,
//            latitude = entity.latitude,
//            longitude = entity.longitude,
//            country=entity.country
//        )
//    }
//
//    fun toEntity(domain: Pupil): PupilEntity {
//        return PupilEntity(
//            id = domain.id,
//            pupilId = domain.pupilId,
//            name = domain.name,
//            image = domain.image,
//            country = domain.country,
//            latitude = domain.latitude,
//            longitude = domain.longitude
//        )
//    }
//
//    fun toDomainList(entities: List<PupilEntity>): List<Pupil> {
//        return entities.map(::toDomain)
//    }
//
//    fun toEntityList(domains: List<Pupil>): List<PupilEntity> {
//        return domains.map(::toEntity)
//    }
//
//    // Optional: Handle nullable inputs
//    fun toDomainOrNull(entity: PupilEntity?): Pupil? {
//        return entity?.let { toDomain(it) }
//    }
//
//    fun toEntityOrNull(domain: Pupil?): PupilEntity? {
//        return domain?.let { toEntity(it) }
//    }
//}