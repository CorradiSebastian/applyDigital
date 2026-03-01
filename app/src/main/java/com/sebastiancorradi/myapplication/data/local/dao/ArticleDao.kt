package com.sebastiancorradi.myapplication.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sebastiancorradi.myapplication.data.local.entities.ArticleEntity

@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles")
    suspend fun getAllArticles(): List<ArticleEntity>

    @Query("SELECT * FROM articles WHERE deleted = 0")
    suspend fun getUnDeletedArtilesArticles(): List<ArticleEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArticles(articles: List<ArticleEntity>)

    @Query("DELETE FROM articles where deleted = 0")
    suspend fun clearAll()

    @Query("UPDATE articles SET deleted = 1 where id = :articleId")
    suspend fun deleteArticle(articleId: Int)
}
