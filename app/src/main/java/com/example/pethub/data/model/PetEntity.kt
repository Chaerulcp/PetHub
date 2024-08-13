package com.example.pethub.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pet")
class PetEntity (
    @field:ColumnInfo(name = "id")
    @field:PrimaryKey
    val id: String,

    @field:ColumnInfo(name = "name")
    val name: String,

    @field:ColumnInfo(name = "age")
    val age: String,

    @field:ColumnInfo(name = "gender")
    val gender: String,

    @field:ColumnInfo(name = "location")
    val location: Int,

    @field:ColumnInfo(name = "image")
    val image: String,
)