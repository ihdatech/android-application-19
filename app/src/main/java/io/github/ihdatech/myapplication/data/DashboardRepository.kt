package io.github.ihdatech.myapplication.data

import io.github.ihdatech.myapplication.data.local.LocalDataSource
import io.github.ihdatech.myapplication.data.model.LoggedInProduct
import io.github.ihdatech.myapplication.data.remote.RemoteDataSource
import io.reactivex.Flowable
import javax.inject.Inject

class DashboardRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) {
    fun getList(): Flowable<List<LoggedInProduct>> = remoteDataSource.create.products()
}