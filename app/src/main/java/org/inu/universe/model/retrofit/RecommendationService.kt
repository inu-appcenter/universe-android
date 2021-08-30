package org.inu.universe.model.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface RecommendationService {
    @Headers("Accept: application/json", "Content-type: application/json")
    @GET("recommend")
    fun getRecommendation(
        @Header("Authorization") token: String
    ) : Call<List<RecommendationResponse>>
}