package org.inu.universe.model.retrofit

import okhttp3.MultipartBody
import okhttp3.RequestBody
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

    @Headers("Accept: application/json", "Content-type: application/json")
    @Multipart
    @PATCH("profile")
    fun updateProfile(
        @Header("Authorization") token: String,
        @Part image: MultipartBody.Part?,
        @Part request: MultipartBody.Part
    ) : Call<Profile>
}