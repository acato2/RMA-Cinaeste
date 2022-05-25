package com.example.cinaeste.view

import com.example.cinaeste.data.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiAdapter {
    val retrofit : Api = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)
}