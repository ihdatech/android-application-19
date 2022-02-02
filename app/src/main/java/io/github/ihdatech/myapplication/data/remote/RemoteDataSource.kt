package io.github.ihdatech.myapplication.data.remote

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RemoteDataSource {
    var create: ProductsService = Retrofit.Builder().apply {
        baseUrl("https://ihdatech-products.herokuapp.com")
        addConverterFactory(GsonConverterFactory.create())
        addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }.build().create(ProductsService::class.java)
}

