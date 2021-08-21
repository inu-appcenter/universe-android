package org.inu.universe.feature.login

import okhttp3.ResponseBody
import org.inu.universe.model.retrofit.LoginRequest
import retrofit2.Call
import retrofit2.http.*

interface LoginService {

    @Headers("Accept: application/json", "Content-type: application/json")
    @POST("/account/login")
    fun requestLogin(
        @Body data:LoginRequest
    ) : Call<Unit>

    @GET("/account/login")
    fun getToken(
        @Header("Authorization") authToken: String
    ) : Call<ResponseBody>
}