package io.github.ihdatech.myapplication.data.remote

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RemoteDataSource {
    var create: ZodiacService = Retrofit.Builder().apply {
        baseUrl("https://gist.githubusercontent.com")
        addConverterFactory(GsonConverterFactory.create())
        addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }.build().create(ZodiacService::class.java)
}

