package io.github.ihdatech.myapplication.data.remote

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RemoteDataSource {
    var createMuseum: MuseumService = Retrofit.Builder().apply {
        baseUrl("https://obscure-earth-55790.herokuapp.com")
        addConverterFactory(GsonConverterFactory.create())
        addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }.build().create(MuseumService::class.java)
    var createNews: NewsService = Retrofit.Builder().apply {
        baseUrl("https://newsapi.org")
        addConverterFactory(GsonConverterFactory.create())
        addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }.build().create(NewsService::class.java)
}

