package org.inu.universe.model.retrofit

data class ProfileRequest(
    val nickName: String,
    val age: Int,
    val gender: String,
    val college: String,
    val major: String)