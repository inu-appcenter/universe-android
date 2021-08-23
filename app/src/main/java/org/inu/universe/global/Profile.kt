package org.inu.universe.global

data class Profile(
    val nickName:String,
    val age:Int,
    val gender: String,
    val college: String,
    val major: String,
    val militaryStatus: Boolean?,
    val region: String?,
    val height: String?,
    val bodyType: String?,
    val mbti: String?,
    val introduction: String?,
    val hashTagList: List<String>?
)

