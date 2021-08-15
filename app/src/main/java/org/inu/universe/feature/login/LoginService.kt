package org.inu.universe.feature.login

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface LoginService {

    @FormUrlEncoded
    @POST("/account/login")
    fun requestLogin(
        @Field("address") address:String,
        @Field("password") password:String
    ) : Call<Unit>

    @GET("/account/login")
    fun getToken(
        @Header("Authorization") authToken: String
    ) : Call<ResponseBody>

}