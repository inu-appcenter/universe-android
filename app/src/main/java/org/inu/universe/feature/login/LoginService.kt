package org.inu.universe.feature.login

import okhttp3.ResponseBody
import org.inu.universe.model.retrofit.LoginResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface LoginService {

    @Headers("Accept: application/json", "Content-type: application/json")
    @POST("/account/login")
    fun requestLogin(
        @Body json:JSONObject
    ) : Call<LoginResponse>

    @GET("/account/login")
    fun getToken(
        @Header("Authorization") authToken: String
    ) : Call<ResponseBody>

}