package com.example.cinemaxapp.core.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.cinemaxapp.core.data.local.database.entity.GenreEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface GenreDao {

    @Query("SELECT * FROM genres ORDER BY name ASC")
    fun getAll(): Flow<List<GenreEntity>>

    @Upsert
    suspend fun upsertAll(genres: List<GenreEntity>)

    @Query("DELETE FROM genres")
    suspend fun clearAll()
}
