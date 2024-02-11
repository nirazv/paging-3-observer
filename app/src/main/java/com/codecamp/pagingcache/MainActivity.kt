package com.codecamp.pagingcache

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codecamp.pagingcache.data.local.ArticleEntity
import com.codecamp.pagingcache.ui.component.ArticleContent
import com.codecamp.pagingcache.ui.theme.PagingOfflineFirstCacheTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PagingOfflineFirstCacheTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArticleContent()
                }
            }
        }
    }
}


@Composable
fun ArticleItem(item: ArticleEntity) {
    Text(
        text = item.text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    )
}
