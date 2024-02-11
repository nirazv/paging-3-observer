package com.codecamp.pagingcache.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp
import java.time.Instant

@Entity(tableName = "article_entity")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val text:String,
    val timestamp: Int = System.currentTimeMillis().toInt()
)