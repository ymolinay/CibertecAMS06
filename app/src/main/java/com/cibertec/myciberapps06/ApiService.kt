package com.cibertec.myciberapps06

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("getAll")
    fun getAllTourismItems(): Call<List<Tourism>>

    @GET("getById")
    fun getTourismById(@Query("id") id: Int): Call<Tourism>

}