package com.example.onefin.domain.repository

import com.example.onefin.data.database.MainDao


interface MainRepository{

    suspend fun getDao() : MainDao

}