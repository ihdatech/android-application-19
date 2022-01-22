package io.github.ihdatech.myapplication.data

import io.github.ihdatech.myapplication.data.local.LocalDataSource
import io.github.ihdatech.myapplication.data.remote.RemoteDataSource
import javax.inject.Inject

class DashboardRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) {
    fun getList() = remoteDataSource.createNews.everything()
}