package io.github.ihdatech.myapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.ihdatech.myapplication.data.HomeRepository
import io.github.ihdatech.myapplication.data.model.HomesResponse
import io.github.ihdatech.myapplication.utils.defaultErrorHandler
import io.github.ihdatech.myapplication.utils.ext.toLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
) : ViewModel() {
    private val disposable: CompositeDisposable = CompositeDisposable()
    val list: LiveData<Result<HomesResponse>> by lazy {
        homeRepository.getList()
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
