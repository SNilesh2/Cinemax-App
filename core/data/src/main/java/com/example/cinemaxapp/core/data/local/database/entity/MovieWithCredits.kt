package com.example.cinemaxapp.core.data.local.database.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation


data class MovieWithCredits(


    @Embedded
    val movie: MovieEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "person_id",
        associateBy = Junction(
            value        = MovieCreditRef::class,
            parentColumn = "movie_id",    // SQL column name from @ColumnInfo on MovieCreditRef.movieId
            entityColumn = "person_id",   // SQL column name from @ColumnInfo on MovieCreditRef.personId
        ),
    )
    val credits: List<CreditEntity>,
)
