package io.github.ihdatech.myapplication.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.ihdatech.myapplication.data.HomeRepository
import io.github.ihdatech.myapplication.data.local.LocalDataSource
import io.github.ihdatech.myapplication.data.remote.RemoteDataSource

/**
 * ViewModel provider factory to instantiate HomeViewModel.
 * Required given HomeViewModel has a non-empty constructor
 */
class HomeViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(homeRepository = HomeRepository(localDataSource = LocalDataSource(), remoteDataSource = RemoteDataSource)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}