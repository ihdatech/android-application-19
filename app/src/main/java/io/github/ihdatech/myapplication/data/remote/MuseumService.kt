package io.github.ihdatech.myapplication.data.remote

import io.github.ihdatech.myapplication.data.model.LoggedInHome
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.http.GET

interface MuseumService {
    @GET("/api/museums")
    fun museumList(): Flowable<LoggedInHome>

    @GET("/api/museums")
    fun movieList(): Call<LoggedInHome>
}