package com.example.onefin.data.repository


import com.example.onefin.data.database.MainDao
import com.example.onefin.data.database.MainDb
import com.example.onefin.domain.repository.MainRepository

class MainRepositoryImpl(private val db: MainDb) : MainRepository {

    override suspend fun getDao(): MainDao {
        return db.getDao()
    }


}