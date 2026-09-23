package com.example.cinemaxapp.core.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cinemaxapp.core.data.local.database.dao.CreditDao
import com.example.cinemaxapp.core.data.local.database.dao.GenreDao
import com.example.cinemaxapp.core.data.local.database.dao.MovieDao
import com.example.cinemaxapp.core.data.local.database.entity.CreditEntity
import com.example.cinemaxapp.core.data.local.database.entity.GenreEntity
import com.example.cinemaxapp.core.data.local.database.entity.MovieCreditRef
import com.example.cinemaxapp.core.data.local.database.entity.MovieEntity
import com.example.cinemaxapp.core.data.local.database.entity.MovieGenreCrossRef
import com.example.cinemaxapp.core.data.local.database.entity.NowPlayingMovieRef
import com.example.cinemaxapp.core.data.local.database.entity.WishlistMovieRef


@Database(
    entities = [
        MovieEntity::class,
        GenreEntity::class,
        MovieGenreCrossRef::class,
        NowPlayingMovieRef::class,
        CreditEntity::class,      // Added in v3
        MovieCreditRef::class,    // Added in v3
        WishlistMovieRef::class,  // Added in v4
    ],
    version = 4,
    exportSchema = false,
)
abstract class CinemaxDatabase : RoomDatabase() {


    abstract fun movieDao(): MovieDao

    abstract fun genreDao(): GenreDao

    abstract fun creditDao(): CreditDao
}
