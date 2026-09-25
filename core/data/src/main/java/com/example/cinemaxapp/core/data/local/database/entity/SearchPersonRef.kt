package com.example.cinemaxapp.core.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity


@Entity(
    tableName = "search_persons_ref",
    primaryKeys = ["query", "person_id"]
)
data class SearchPersonRef(

    @ColumnInfo(name = "query")
    val query: String,

    @ColumnInfo(name = "person_id")
    val personId: Int,

    @ColumnInfo(name = "page")
    val page: Int,

    @ColumnInfo(name = "position")
    val position: Int
)