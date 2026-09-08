package com.example.cinemaxapp.core.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "credits")
data class CreditEntity(


    @PrimaryKey
    @ColumnInfo(name = "person_id")
    val personId: Int,

    @ColumnInfo(name = "name")
    val name: String,


    @ColumnInfo(name = "profile_path")
    val profilePath: String?,
)
