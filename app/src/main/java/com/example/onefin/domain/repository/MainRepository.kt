package com.example.onefin.domain.repository

import com.example.onefin.data.database.MainDao
import com.example.onefin.data.database.MainDb


interface MainRepository{

    suspend fun getDao() : MainDao

}