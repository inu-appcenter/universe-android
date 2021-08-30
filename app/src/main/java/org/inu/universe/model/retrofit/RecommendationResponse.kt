package org.inu.universe.model.retrofit

import android.provider.ContactsContract
import java.io.File

data class RecommendationResponse(
    val id: Int,
    val profileImage: File?,
    val nickName: String,
    val age: Int,
    val gender: String,
    val major: String
)