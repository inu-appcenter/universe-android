package org.inu.universe.model.retrofit

import org.inu.universe.global.Profile
import retrofit2.Call
import retrofit2.http.*

interface ProfileService {
    @Headers("Accept: application/json", "Content-type: application/json")
    @POST("profile")
    fun createProfile(
        @Header("Authorization") token: String,
        @Body data:ProfileRequest
    ) : Call<ProfileResponse>

    @GET("profile/{profileId}")
    fun getProfile(
        @Header("Authorization") token: String,
        @Path("profileId") profileId: Int
    ) : Call<Profile>
}