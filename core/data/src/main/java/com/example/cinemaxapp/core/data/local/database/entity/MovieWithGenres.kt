package com.example.cinemaxapp.core.data.local.database.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class MovieWithGenres(

    @Embedded
    val movie: MovieEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value        = MovieGenreCrossRef::class,
            parentColumn = "movie_id",   // SQL column name on MovieGenreCrossRef.movieId
            entityColumn = "genre_id",   // SQL column name on MovieGenreCrossRef.genreId
        ),
    )
    val genres: List<GenreEntity>,
)
