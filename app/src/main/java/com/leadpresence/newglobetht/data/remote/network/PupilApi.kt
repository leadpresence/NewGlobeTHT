package com.leadpresence.newglobetht.data.remote.network



import com.leadpresence.newglobetht.data.model.PupilDto
import com.leadpresence.newglobetht.domain.model.Pupil
//import com.leadpresence.newglobetht.domain.model.PupilList
import com.leadpresence.newglobetht.domain.model.PupilResponse
import retrofit2.http.*
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
//
//interface PupilApi {
//    @GET("pupils")
//    fun getPupils(): PupilResponse
//    /// get pupil by id
//    @GET("pupil/{pupilId}")
//    fun getPupilById(   @Path("pupilId") pupilId: Int,): Single<Pupil>
//    /// create pupil
//    @POST("pupils")
//    fun createPupil(@Body pupil: Pupil): Single<Pupil>
//    /// edit pupil
//    @PUT("pupils/{pupilId}")
//    fun updatePupil( @Path("pupilId") pupilId: Int,  @Body pupil: Pupil): Single<Pupil>
//    /// delete pupil
//    @DELETE("pupils/{pupilId}")
//    fun deletePupil(@Path("pupilId") pupilId: Int): Response<Unit>
//}

interface PupilApi {
    @GET("pupils")
    suspend fun getPupils(@Query("page") page: Long): PupilResponse

    @GET("pupil/{pupilId}")
    fun getPupilById(   @Path("pupilId") pupilId: Long,):PupilDto

    @POST("pupils")
    suspend fun createPupil(@Body pupil: PupilDto): PupilDto

    @PUT("pupils/{pupilId}")
    suspend fun updatePupil(
        @Path("pupilId") pupilId: Long,
        @Body pupil: PupilDto
    ): PupilDto

    @DELETE("pupils/{pupilId}")
    suspend fun deletePupil(@Path("pupilId") pupilId: Int)



}