package com.codecamp.pagingcache.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.codecamp.pagingcache.ArticleItem
import com.codecamp.pagingcache.MainViewModel


@Composable
fun ArticleContent(viewModel: MainViewModel = hiltViewModel()) {

    val lazyArticleItems = viewModel.articlesFlows.collectAsLazyPagingItems()
    val previousItemCount = remember { mutableIntStateOf(0) }
    val lazyListState = rememberLazyListState()
    val isClicked = remember { mutableStateOf(false) }
    val isVisible = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = isClicked.value, block = {
        if (isClicked.value) {
            lazyListState.animateScrollToItem(0)
            isVisible.value = false
        }
    })


    //visible only if new data from API has been loaded
    LaunchedEffect(key1 = lazyArticleItems.itemCount, block = {
        // Get the current item count
        val itemCount = lazyArticleItems.itemCount

        // Compare with the previous item count
        if (itemCount > previousItemCount.intValue && lazyListState.canScrollBackward) {
            // Update the previous item count
            previousItemCount.intValue = itemCount

            //render LoadRefreshedDataButton
            isVisible.value = true
        }

    })

    //Box
    Box(modifier = Modifier.fillMaxSize()) {

        if (lazyArticleItems.loadState.refresh is LoadState.Loading && lazyArticleItems.itemCount < 1) {
//            Log.d("GoogleIO", "LazyArticleIsLoading::Refresh")
        } else {
            LazyColumn(
                state = lazyListState,
                modifier = Modifier.fillMaxSize()) {
                items(
                    contentType = lazyArticleItems.itemContentType { "Article" },
                    key = lazyArticleItems.itemKey { it.id },
                    count = lazyArticleItems.itemCount
                ) { index: Int ->
                    ArticleItem(lazyArticleItems[index]!!)
                    if (index < lazyArticleItems.itemCount - 1) Divider()
                }
            }
        }


        //visible only if new data has been loaded
        LoadRefreshedData(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .wrapContentWidth()
                .padding(top = 20.dp),
            onClick = {
                isClicked.value = true
            },
            isVisible = isVisible.value
        )
    }
}
