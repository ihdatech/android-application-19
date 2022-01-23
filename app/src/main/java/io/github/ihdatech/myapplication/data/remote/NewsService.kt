package io.github.ihdatech.myapplication.data.remote

import io.github.ihdatech.myapplication.data.model.LoggedInNews
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("/v2/everything")
    fun everything(
        @Query("q") q: String? = "bitcoin",
        @Query("from") from: String? = "2021-12-23",
        @Query("sortBy") sortBy: String? = "publishedAt",
        @Query("language") language: String? = "en",
        @Query("apiKey") apiKey: String? = "30ecb3c86e204bbe9207b869b1800e7b",
    ): Flowable<LoggedInNews>

    @GET("/v2/top-headlines")
    fun topHeadlines(
        @Query("q") q: String? = "Apple",
        @Query("from") from: String? = "2021-12-23",
        @Query("sortBy") sortBy: String? = "publishedAt",
        @Query("language") language: String? = "en",
        @Query("apiKey") apiKey: String? = "30ecb3c86e204bbe9207b869b1800e7b",
    ): Flowable<LoggedInNews>
}