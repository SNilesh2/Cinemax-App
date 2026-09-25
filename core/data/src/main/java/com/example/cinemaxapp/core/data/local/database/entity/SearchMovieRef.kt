package com.example.cinemaxapp.core.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity


@Entity(
    tableName = "search_movies_ref",
    primaryKeys = ["query", "movie_id"]
)
data class SearchMovieRef(

    @ColumnInfo(name = "query")
    val query: String,

    @ColumnInfo(name = "movie_id")
    val movieId: Int,

    @ColumnInfo(name = "page")
    val page: Int,

    @ColumnInfo(name = "position")
    val position: Int
)
