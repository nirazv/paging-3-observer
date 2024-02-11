package com.codecamp.pagingcache.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArticleDao {

    @Query("SELECT * FROM article_entity ORDER BY ID DESC")
    fun getAllArticles() : PagingSource<Int,ArticleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticle(articleEntity: ArticleEntity)

    @Delete
    suspend fun deleteArticle(articleEntity: ArticleEntity)

    @Query("SELECT MAX(timestamp) FROM article_entity")
    suspend fun getLatestTimestamp(): Long

}