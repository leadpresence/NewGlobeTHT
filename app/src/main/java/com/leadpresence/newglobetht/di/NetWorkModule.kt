package com.leadpresence.newglobetht.di

import com.leadpresence.newglobetht.data.remote.network.PupilApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

private const val REQUEST_ID = "dda7feeb-20af-415e-887e-afc43f245624"
private const val USER_AGENT = "Bridge Android Tech Test"


object NetworkConstants {
    const val API_TIMEOUT = 30L
    const val REQUEST_ID = "dda7feeb-20af-415e-887e-afc43f245624"
    const val USER_AGENT = "Bridge Android Tech Test"
    const val BASE_URL = "https://androidtechnicaltestapi-test.bridgeinternationalacademies.com/"
    // Replace with your actual base URL
}

val networkModule = module {
    single {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
    }

    single {
        OkHttpClient.Builder().apply {
            readTimeout(NetworkConstants.API_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(NetworkConstants.API_TIMEOUT, TimeUnit.SECONDS)
            connectTimeout(NetworkConstants.API_TIMEOUT, TimeUnit.SECONDS)
            addInterceptor { chain ->
                val originalRequest = chain.request()
                val newRequest = originalRequest.newBuilder()
                    .addHeader("X-Request-ID", NetworkConstants.REQUEST_ID)
                    .addHeader("User-Agent", NetworkConstants.USER_AGENT)
                    .build()
                chain.proceed(newRequest)
            }
        }.build()
    }
    single {
        Retrofit.Builder()
            .baseUrl(NetworkConstants.BASE_URL)
            .client(get())
            .addConverterFactory(get<Json>().asConverterFactory("application/json".toMediaType()))
            .build()
    }

    single { get<Retrofit>().create(PupilApi::class.java) }
}
