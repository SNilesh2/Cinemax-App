package com.example.cinemaxapp.core.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "now_playing_movies_ref")
data class NowPlayingMovieRef(

    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    val movieId: Int,
)
