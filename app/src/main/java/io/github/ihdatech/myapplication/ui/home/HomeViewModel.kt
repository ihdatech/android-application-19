package io.github.ihdatech.myapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.github.ihdatech.myapplication.data.MuseumRepository
import io.github.ihdatech.myapplication.data.model.LoggedInHome
import io.github.ihdatech.myapplication.utils.defaultErrorHandler
import io.github.ihdatech.myapplication.utils.ext.toLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel(private val museumRepository: MuseumRepository) : ViewModel() {
    private val disposable: CompositeDisposable = CompositeDisposable()

    val museumList: LiveData<ArrayList<LoggedInHome>?> by lazy {
        museumRepository.getList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.data }
            .doOnError(defaultErrorHandler())
            .toLiveData()
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    // val museumResult: LiveData<Result<ArrayList<LoggedInHome>?>> by lazy {
    //     museumRepository.getMuseums()
    //         .subscribeOn(Schedulers.io())
    //         .observeOn(AndroidSchedulers.mainThread())
    //         .map { Result.success(it.data) }
    //         .doOnError(defaultErrorHandler())
    //         .onErrorReturn { Result.failure(it) }
    //         .startWith(Result.loading())
    //         .toLiveData()
    // }
}
