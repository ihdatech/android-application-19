package io.github.ihdatech.myapplication.data

import io.github.ihdatech.myapplication.data.local.LocalDataSource
import io.github.ihdatech.myapplication.data.model.LoggedInHome
import io.github.ihdatech.myapplication.data.remote.RemoteDataSource
import io.reactivex.Flowable
import javax.inject.Inject
import android.app.Application
import io.github.ihdatech.myapplication.data.local.LocalRoomDatabase


class HomeRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) {
    fun getList(): Flowable<LoggedInHome> = remoteDataSource.createMuseum.museumList()

    // private val _list = MutableLiveData<ArrayList<LoggedInHome>>().apply { value = arrayListOf() }
    // private var _refresh = MutableLiveData<ArrayList<LoggedInHome>>().apply { value = arrayListOf() }
    // private var _refreshing = MutableLiveData<Boolean>().apply { value = true }
    // var list: LiveData<ArrayList<LoggedInHome>> = _list
    // var refresh: LiveData<ArrayList<LoggedInHome>> = _refresh
    // var refreshing: LiveData<Boolean> = _refreshing
    // fun getHomes() {
    //     remoteDataSource.create.movieList().enqueue(object : Callback<HomesResponse> {
    //         override fun onFailure(call: Call<HomesResponse>?, t: Throwable?) {
    //             _list.value?.clear()
    //         }
    //         override fun onResponse(call: Call<HomesResponse>?, response: Response<HomesResponse>?) {
    //             _list.value = response?.body()?.data
    //             _refresh.value = response?.body()?.data
    //             _refreshing.value = false
    //         }
    //     })
    // }
}