package io.github.ihdatech.myapplication.data.remote

import io.github.ihdatech.myapplication.data.model.HomesResponse
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.http.GET

interface MuseumService {
    @GET("/api/museums")
    fun museumList(): Flowable<HomesResponse>

    @GET("/api/museums")
    fun movieList(): Call<HomesResponse>
}