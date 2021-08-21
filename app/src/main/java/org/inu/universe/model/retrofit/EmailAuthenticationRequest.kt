package org.inu.universe.model.retrofit

data class EmailAuthenticationRequest(
    val address: String,
    val authKey: String
)