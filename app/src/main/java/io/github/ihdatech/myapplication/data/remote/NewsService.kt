package io.github.ihdatech.myapplication.data.remote

import io.github.ihdatech.myapplication.data.model.LoggedInNews
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("/v2/everything")
    fun everything(
        @Query("q") q: String? = "manga",
        @Query("from") from: String? = "2021-12-21",
        @Query("sortBy") sortBy: String? = "publishedAt",
        @Query("language") language: String? = "en",
        @Query("apiKey") apiKey: String? = "98c8df982b8b4da8b86cd70e851fc521",
    ): Flowable<LoggedInNews>
}