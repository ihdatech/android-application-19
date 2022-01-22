package io.github.ihdatech.myapplication.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.ihdatech.myapplication.data.remote.MuseumService
import io.github.ihdatech.myapplication.data.remote.NewsService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideMuseumService(): MuseumService {
        return Retrofit.Builder().apply {
            baseUrl("https://obscure-earth-55790.herokuapp.com")
            addConverterFactory(GsonConverterFactory.create())
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        }.build().create(MuseumService::class.java)
    }

    @Singleton
    @Provides
    fun provideNewsService(): NewsService {
        return Retrofit.Builder().apply {
            baseUrl("https://newsapi.org")
            addConverterFactory(GsonConverterFactory.create())
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        }.build().create(NewsService::class.java)
    }
}