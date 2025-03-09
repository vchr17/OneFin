package com.example.onefin.data.use_cases

import com.example.onefin.data.repository.CurrencyApi
import com.example.onefin.domain.model.Response
import com.example.onefin.domain.use_cases.DataFetch
import retrofit2.Retrofit

class DataFetchImpl(private val retrofit : Retrofit): DataFetch {

    override suspend fun init(): Response {
        val data = retrofit.create(CurrencyApi::class.java).fetchData()
    return data
    }
}