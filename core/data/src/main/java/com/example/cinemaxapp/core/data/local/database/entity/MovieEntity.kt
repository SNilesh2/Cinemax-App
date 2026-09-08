package com.example.cinemaxapp.core.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movies")
data class MovieEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,


    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String?,


    @ColumnInfo(name = "poster_path")
    val posterPath: String?,


    @ColumnInfo(name = "release_date")
    val releaseDate: String?,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double?,

    //Details for Movie Details screen
    @ColumnInfo(name = "overview", defaultValue = "")
    val overview: String? = null,

    @ColumnInfo(name = "runtime", defaultValue = "0")
    val runtime: Int? = null,


    @ColumnInfo(name = "tagline", defaultValue = "")
    val tagline: String? = null,

    @ColumnInfo(name = "homepage", defaultValue = "")
    val homepage: String? = null,
)

