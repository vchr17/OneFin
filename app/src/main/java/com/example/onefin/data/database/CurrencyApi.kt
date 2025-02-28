package com.example.onefin.data.database

import com.example.onefin.domain.model.Response
import retrofit2.http.GET

interface CurrencyApi {
 @GET("live?access_key=0aa1420fc08a065dd27945087ee7d5c4&source=BYN&currencies=USD,EUR,RUB,UAH,PLN,GBP,CNY,KZT,CHF,CZK,TRY,GEL,VND,AMD,BRL,NZD,INR,AED")
 suspend fun fetchData() : Response
}