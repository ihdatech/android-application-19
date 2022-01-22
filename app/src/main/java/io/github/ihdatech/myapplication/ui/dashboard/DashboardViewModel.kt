package io.github.ihdatech.myapplication.ui.dashboard

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.ihdatech.myapplication.data.DashboardRepository
import io.github.ihdatech.myapplication.data.model.LoggedInNews
import io.github.ihdatech.myapplication.utils.defaultErrorHandler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardRepository: DashboardRepository,
) : ViewModel(), LifecycleObserver {
    private val disposable: CompositeDisposable = CompositeDisposable()
    val articles: LiveData<Result<LoggedInNews>> by lazy {
        println("[TATA FUCKING IN VIEW MODEL]: ${dashboardRepository.getList()}")
        dashboardRepository.getList()
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