package org.inu.universe.model.retrofit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ProfileService {
    @Headers("Accept: application/json", "Content-type: application/json")
    @POST("profile")
    fun requestAuthenticationCode(
        @Body data:ProfileRequest
    ) : Call<ProfileResponse>
}