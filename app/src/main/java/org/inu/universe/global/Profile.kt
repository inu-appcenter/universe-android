package org.inu.universe.global

data class Profile(
    val nickName: String,
    val age: Int,
    val gender: String,
    val college: String,
    val major: String,
    val region: String?,
    val height: String?,
    val bodyType: String?,
    val mbti: String?,
    val introduction: String?,
    val hashTagList: List<String>?,
    val profilePrivate: Boolean?
) {
    override fun toString(): String {
        return "{" +
            "nickName: $nickName," +
            "age: $age," +
            "gender: $gender," +
            "college: $college," +
            "major: $major," +
            "region: $region," +
            "height: $height," +
            "bodyType: $bodyType," +
            "mbti: $mbti," +
            "introduction: $introduction," +
            "hashTagList:," +
            "profilePrivate: false" +
            "}"
    }

    companion object {
        fun mock() = Profile(
            "나는 개발자다", 24, "남자", "정보기술대학", "컴퓨터공학부",
            null, null, null, null, null, null, null
        )
    }
}

