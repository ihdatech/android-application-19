package io.github.ihdatech.myapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.ihdatech.myapplication.data.HomeRepository
import io.github.ihdatech.myapplication.data.model.LoggedInZodiac
import io.github.ihdatech.myapplication.utils.defaultErrorHandler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {
    private val disposable: CompositeDisposable = CompositeDisposable()
    val list: LiveData<Result<List<LoggedInZodiac>>> by lazy {
        homeRepository.getList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { Result.success(it) }
            .doOnError(defaultErrorHandler())
            .onErrorReturn { Result.failure(it) }
            // .startWith(Result.loading())
            .toLiveData()
    }
    private val _text = MutableLiveData<String>().apply {
        value = homeRepository.getList().toString()
    }
    val text: LiveData<String> = _text

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
