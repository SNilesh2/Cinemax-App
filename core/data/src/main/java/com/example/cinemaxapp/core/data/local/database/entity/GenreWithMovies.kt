package com.example.cinemaxapp.core.data.local.database.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation


data class GenreWithMovies(

    @Embedded
    val genre: GenreEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value         = MovieGenreCrossRef::class,
            parentColumn  = "genre_id",   // SQL column name (from @ColumnInfo on MovieGenreCrossRef.genreId)
            entityColumn  = "movie_id",   // SQL column name (from @ColumnInfo on MovieGenreCrossRef.movieId)
        ),
    )
    val movies: List<MovieEntity>,
)
