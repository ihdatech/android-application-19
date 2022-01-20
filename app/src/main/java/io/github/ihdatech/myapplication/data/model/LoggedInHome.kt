package io.github.ihdatech.myapplication.data.model

/**
 * Data class that captures home information for logged in homes retrieved from HomeRepository
 */
data class LoggedInHome(
    var id: Int?,
    var name: String?,
    var photo: String?,
)

data class HomesResponse(
    val status: Int?,
    val msg: String?,
    val data: ArrayList<LoggedInHome>?,
) {
    fun isSuccess(): Boolean = (status == 200)
}