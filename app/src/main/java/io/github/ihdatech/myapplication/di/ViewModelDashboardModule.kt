package io.github.ihdatech.myapplication.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import io.github.ihdatech.myapplication.data.DashboardRepository
import io.github.ihdatech.myapplication.data.local.LocalDataSource
import io.github.ihdatech.myapplication.data.remote.RemoteDataSource

@Module
@InstallIn(ViewModelComponent::class)
internal object ViewModelDashboardModule {

    @Provides
    @ViewModelScoped
    fun provideRepository(): DashboardRepository = DashboardRepository(
        localDataSource = LocalDataSource(),
        remoteDataSource = RemoteDataSource,
    )
}