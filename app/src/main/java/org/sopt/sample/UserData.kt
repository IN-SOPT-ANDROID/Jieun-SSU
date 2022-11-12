package org.sopt.sample

data class UserData(
    val img: Int,  //img는 int 형 데이터이다.
    val title: String,
    val content: String,
    val type: Int = 1,
)