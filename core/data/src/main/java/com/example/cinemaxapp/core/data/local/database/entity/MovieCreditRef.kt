package com.example.cinemaxapp.core.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "movie_credit_ref",
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["id"],
            childColumns = ["movie_id"],
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = CreditEntity::class,
            parentColumns = ["person_id"],
            childColumns = ["person_id"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [
        Index(value = ["movie_id"]),
        Index(value = ["person_id"]),
    ],
)
data class MovieCreditRef(

    // unique identifier for movie+person+role combination
    @PrimaryKey
    @ColumnInfo(name = "credit_id")
    val creditId: String,

    //refers to a single movie
    @ColumnInfo(name = "movie_id")
    val movieId: Int,

    //refers to individual person like tom holland
    @ColumnInfo(name = "person_id")
    val personId: Int,

    //crew or cast
    @ColumnInfo(name = "credit_type")
    val creditType: String,


    //peter parker for Tom Holland
    @ColumnInfo(name = "character")
    val character: String?,

    //like director etc more specific of a department
    @ColumnInfo(name = "job")
    val job: String?,

    //direction
    @ColumnInfo(name = "department")
    val department: String?,

    //0 for tom holland 1 fo zendaya (bill wise order)
    @ColumnInfo(name = "cast_order")
    val castOrder: Int,
)
