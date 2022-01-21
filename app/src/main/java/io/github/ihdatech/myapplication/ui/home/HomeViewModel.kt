package io.github.ihdatech.myapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.github.ihdatech.myapplication.data.MuseumRepository
import io.github.ihdatech.myapplication.data.model.HomesResponse
import io.github.ihdatech.myapplication.data.model.LoggedInHome
import io.github.ihdatech.myapplication.utils.defaultErrorHandler
import io.github.ihdatech.myapplication.utils.ext.toLiveData
import io.github.ihdatech.myapplication.utils.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel(private val museumRepository: MuseumRepository) : ViewModel() {
    private val disposable: CompositeDisposable = CompositeDisposable()

    val homeResult: LiveData<Result<HomesResponse>> by lazy {
        museumRepository.getList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { Result.success(it) }
            .doOnError(defaultErrorHandler())
            .onErrorReturn { Result.failure(it) }
            .startWith(Result.loading())
            .toLiveData()
    }

    val homeList: LiveData<ArrayList<LoggedInHome>?> by lazy {
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
}
