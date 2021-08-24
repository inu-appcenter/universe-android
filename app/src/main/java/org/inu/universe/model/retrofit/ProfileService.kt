package org.inu.universe.model.retrofit

import org.inu.universe.global.Profile
import retrofit2.Call
import retrofit2.http.*
import java.io.File

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
        @Path("profileId") profileId: String
    ) : Call<Profile>

    @FormUrlEncoded
    @Headers("Accept: application/json", "Content-type: application/json")
    @PATCH("profile")
    fun updateProfile(
        @Header("Authorization") token: String,
        @Field("image") image: File?,
        @Field("request") request: Profile
    ) : Call<Profile>
}