package com.sebastiancorradi.myapplication.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sebastiancorradi.myapplication.data.local.dao.ArticleDao
import com.sebastiancorradi.myapplication.data.local.entities.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}
