package com.sebastiancorradi.myapplication.di

import android.app.Application
import androidx.room.Room
import com.sebastiancorradi.myapplication.data.local.dao.ArticleDao
import com.sebastiancorradi.myapplication.data.local.database.AppDatabase
import com.sebastiancorradi.myapplication.data.remote.ArticlesApi
import com.sebastiancorradi.myapplication.data.remote.RetrofitClient
import com.sebastiancorradi.myapplication.data.repository.ArticlesRepositoryImpl
import com.sebastiancorradi.myapplication.domain.repository.ArticlesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "articles_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideArticleDao(db: AppDatabase): ArticleDao {
        return db.articleDao()
    }

    @Provides
    @Singleton
    fun provideArticlesApi(): ArticlesApi {
        return RetrofitClient.articlesApi
    }

    @Provides
    @Singleton
    fun provideArticlesRepository(api: ArticlesApi, dao: ArticleDao): ArticlesRepository {
        return ArticlesRepositoryImpl(api, dao)
    }
}
