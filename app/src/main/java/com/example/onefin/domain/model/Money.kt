package com.example.onefin.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "CurrencyTable")
data class Money (
    @ColumnInfo
    val stamp: Long,
    @PrimaryKey
    val name: String,
    @ColumnInfo
    val value: Double,
    @ColumnInfo
    var isFavourite : Int = 0
)