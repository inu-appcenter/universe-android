package org.inu.universe.model.retrofit

import retrofit2.Call
import retrofit2.http.*

interface AccountService {
    @Headers("Accept: application/json", "Content-type: application/json")
    @POST("account")
    fun requestSignup(
        @Body data: AccountRequest
    ) : Call<Unit>

    @Headers("Accept: application/json", "Content-type: application/json")
    @GET("account")
    fun requestIds(
        @Header("Authorization") token: String
    ) : Call<AccountIds>
}