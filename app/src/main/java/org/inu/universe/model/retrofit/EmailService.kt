package org.inu.universe.model.retrofit

import retrofit2.Call
import retrofit2.http.*

interface EmailService {
    @Headers("Accept: application/json", "Content-type: application/json")
    @POST("/email")
    fun requestAuthenticationCode(
        @Body data:EmailRequest
    ) : Call<Unit>

    @Headers("Accept: application/json", "Content-type: application/json")
    @POST("/email/auth")
    fun requestAuthentication(
        @Body data:EmailAuthenticationRequest
    ) : Call<Unit>
}