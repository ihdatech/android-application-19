package io.github.ihdatech.myapplication.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.ihdatech.myapplication.data.DashboardRepository
import io.github.ihdatech.myapplication.data.local.LocalDataSource
import io.github.ihdatech.myapplication.data.remote.RemoteDataSource

/**
 * ViewModel provider factory to instantiate DashboardViewModel.
 * Required given DashboardViewModel has a non-empty constructor
 */
class DashboardViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            return DashboardViewModel(dashboardRepository = DashboardRepository(localDataSource = LocalDataSource(), remoteDataSource = RemoteDataSource)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}