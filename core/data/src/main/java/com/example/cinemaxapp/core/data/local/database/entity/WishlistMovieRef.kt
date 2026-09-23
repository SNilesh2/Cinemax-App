package com.example.cinemaxapp.core.data.local.database.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "wishlist_movies_ref",
    foreignKeys = [
        ForeignKey(
            entity         = MovieEntity::class,
            parentColumns  = ["id"],
            childColumns   = ["movie_id"],
            onDelete       = ForeignKey.CASCADE,
        )
    ],
)
data class WishlistMovieRef(

    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    val movieId: Int,

    @ColumnInfo(name = "added_at")
    val addedAt: Long = System.currentTimeMillis(),
)
