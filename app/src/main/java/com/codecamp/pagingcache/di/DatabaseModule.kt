package com.codecamp.pagingcache.di

import android.content.Context
import androidx.room.Room
import com.codecamp.pagingcache.data.local.ArticleDao
import com.codecamp.pagingcache.data.local.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun getDatabase(
        @ApplicationContext context: Context
    ) : ArticleDatabase {
        return Room.databaseBuilder(
            context,
            ArticleDatabase::class.java,
            "article_db"
        ).build()
    }

    @Provides
    fun getDao(database: ArticleDatabase) : ArticleDao {
        return database.dao
    }
}