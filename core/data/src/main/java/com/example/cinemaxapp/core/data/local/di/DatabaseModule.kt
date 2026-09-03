package com.example.cinemaxapp.core.data.local.di

import android.content.Context
import androidx.room.Room
import com.example.cinemaxapp.core.data.local.database.CinemaxDatabase
import com.example.cinemaxapp.core.data.local.database.dao.GenreDao
import com.example.cinemaxapp.core.data.local.database.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideCinemaxDatabase(
        @ApplicationContext context: Context,
    ): CinemaxDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = CinemaxDatabase::class.java,
            name = "cinemax_db",
        )
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    @Singleton
    fun provideMovieDao(database: CinemaxDatabase): MovieDao {
        return database.movieDao()
    }


    @Provides
    @Singleton
    fun provideGenreDao(database: CinemaxDatabase): GenreDao {
        return database.genreDao()
    }
}
