package io.github.ihdatech.myapplication.data.model

data class LoggedInNews(
    val status: String?,
    val totalResults: Int?,
    val articles: ArrayList<LoggedInNewsArticles>?,

    // ERROR
    val code: String?,
    val message: String?,
)

data class LoggedInNewsArticles(
    val source: LoggedInNewsArticlesSource?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?,
)

data class LoggedInNewsArticlesSource(
    val id: Int?,
    val name: String?,
)