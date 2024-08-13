package com.example.pethub.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class UserEntity (

    @field:ColumnInfo(name = "name")
    @field:PrimaryKey
    val name: String,
)