package io.github.ihdatech.myapplication.data.remote

import io.github.ihdatech.myapplication.data.MuseumDataSource
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RemoteDataSource {
    var create: MuseumDataSource = Retrofit.Builder().apply {
        baseUrl("https://obscure-earth-55790.herokuapp.com")
        addConverterFactory(GsonConverterFactory.create())
        addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }.build().create(MuseumDataSource::class.java)
}

