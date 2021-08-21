package org.inu.universe.model.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    val BASE_URL = "http://ec2-13-124-191-131.ap-northeast-2.compute.amazonaws.com:8080"

    fun build() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}