package io.github.ihdatech.myapplication.data.remote

import io.github.ihdatech.myapplication.data.model.LoggedInProduct
import io.reactivex.Flowable
import retrofit2.http.GET

interface ProductsService {
    @GET("/products")
    fun products(): Flowable<List<LoggedInProduct>>
}