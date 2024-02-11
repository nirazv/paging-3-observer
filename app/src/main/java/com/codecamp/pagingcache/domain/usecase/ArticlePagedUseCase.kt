package com.codecamp.pagingcache.domain.usecase

import android.util.Log
import androidx.paging.InvalidatingPagingSourceFactory
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.codecamp.pagingcache.data.local.ArticleDao
import com.codecamp.pagingcache.data.local.ArticleEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArticlePagedUseCase @Inject constructor(
    private val articleDao:ArticleDao
) {

    operator fun invoke(): Flow<PagingData<ArticleEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 40
            ),
            pagingSourceFactory = {
                articleDao.getAllArticles()
            }
        ).flow
    }

    suspend fun addArticle(articleEntity: ArticleEntity) {
        articleDao.addArticle(articleEntity)
    }

}