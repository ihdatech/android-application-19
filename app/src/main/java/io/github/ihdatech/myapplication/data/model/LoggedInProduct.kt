package io.github.ihdatech.myapplication.data.model

data class LoggedInProduct(
    val id: Int,
    val price: Int,
    val title: String,
    val description: String,
    val images: List<String>,
)