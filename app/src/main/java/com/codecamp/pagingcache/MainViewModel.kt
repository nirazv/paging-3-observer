package com.codecamp.pagingcache

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.codecamp.pagingcache.data.local.ArticleEntity
import com.codecamp.pagingcache.domain.usecase.ArticlePagedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val articlePagedUseCase: ArticlePagedUseCase,
) : ViewModel() {

    val articlesFlows = articlePagedUseCase().cachedIn(viewModelScope)

    init {
        addArticle()
    }

    private fun addArticle() {
        viewModelScope.launch {
           delay(5000)
            for (i in 369..372) {
                articlePagedUseCase.addArticle(
                    ArticleEntity(id = i,text = "Hello Article: ${i+10}")
                )
                Log.d("GoogleIO", "ArticleAdded")
            }
        }
    }

}