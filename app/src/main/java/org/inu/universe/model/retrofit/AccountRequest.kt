package org.inu.universe.model.retrofit

data class AccountRequest(
    val address: String,
    val password: String,
    val password2: String
)