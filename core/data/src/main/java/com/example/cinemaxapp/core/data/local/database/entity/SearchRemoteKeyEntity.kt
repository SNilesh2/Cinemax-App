package com.example.cinemaxapp.core.data.local.database.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "search_remote_keys")
data class SearchRemoteKeyEntity(

    @PrimaryKey
    @ColumnInfo(name = "query")
    val query: String,

    @ColumnInfo(name = "next_page")
    val nextPage: Int?,

    @ColumnInfo(name = "prev_page")
    val prevPage: Int?,

    @ColumnInfo(name = "last_updated")
    val lastUpdated: Long = System.currentTimeMillis()
)