package com.example.onefin.data.repository

import com.example.onefin.domain.repository.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiImpl : Api {

    override fun init(): Retrofit {
            val retrofit = Retrofit.Builder().baseUrl("https://api.currencylayer.com")
                .addConverterFactory(GsonConverterFactory.create()).build()
            return retrofit
    }
}