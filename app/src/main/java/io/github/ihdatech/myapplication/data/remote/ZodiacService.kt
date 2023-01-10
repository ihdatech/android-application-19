package io.github.ihdatech.myapplication.data.remote

import io.github.ihdatech.myapplication.data.model.LoggedInZodiac
import io.reactivex.Flowable
import retrofit2.http.GET

interface ZodiacService {
    @GET("/ihdatech/4377fcdde116fa0ec652c0340ced76ec/raw/3956c4ca8b303babb84d3b31d712dbacc7a4c190")
    fun products(): Flowable<List<LoggedInZodiac>>
}