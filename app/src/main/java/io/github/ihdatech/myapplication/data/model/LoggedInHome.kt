package io.github.ihdatech.myapplication.data.model

data class LoggedInHome(
    val status: Int?,
    val msg: String?,
    val data: ArrayList<LoggedInHomeData>?,
)

data class LoggedInHomeData(
    val id: Int?,
    val name: String?,
    val photo: String?,
)