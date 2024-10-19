package com.cibertec.myciberapps06

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ClientIRetrofit {

    private const val BASE_URL = "https://2782e730-dbaf-414a-8426-bc759689516f.mock.pstmn.io"

    private val logginInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    private val clientOkHttp =
        OkHttpClient.Builder()
            .addInterceptor(logginInterceptor)
            .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(clientOkHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

}