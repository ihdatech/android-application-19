package io.github.ihdatech.myapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.ihdatech.myapplication.data.HomeRepository
import io.github.ihdatech.myapplication.data.model.LoggedInHome
import io.github.ihdatech.myapplication.data.model.LoggedInProduct
import io.github.ihdatech.myapplication.utils.defaultErrorHandler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
) : ViewModel() {
    private val disposable: CompositeDisposable = CompositeDisposable()
    val list: LiveData<Result<LoggedInHome>> by lazy {
        homeRepository.getList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { Result.success(it) }
            .doOnError(defaultErrorHandler())
            .onErrorReturn { Result.failure(it) }
            // .startWith(Result.loading())
            .toLiveData()
    }
    val listFake: LiveData<Result<List<LoggedInProduct>>> by lazy {
        homeRepository.getListFake()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { Result.success(it) }
            .doOnError(defaultErrorHandler())
            .onErrorReturn { Result.failure(it) }
            // .startWith(Result.loading())
            .toLiveData()
    }
    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
