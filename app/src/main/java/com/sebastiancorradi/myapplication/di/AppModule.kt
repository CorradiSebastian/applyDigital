package com.sebastiancorradi.myapplication.di

import com.sebastiancorradi.myapplication.data.remote.ArticlesApi
import com.sebastiancorradi.myapplication.data.remote.RetrofitClient
import com.sebastiancorradi.myapplication.data.repository.ArticlesRepositoryImpl
import com.sebastiancorradi.myapplication.domain.repository.ArticlesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideArticlesApi(): ArticlesApi {
        return RetrofitClient.articlesApi
    }

    @Provides
    @Singleton
    fun provideArticlesRepository(api: ArticlesApi): ArticlesRepository {
        return ArticlesRepositoryImpl(api)
    }
}