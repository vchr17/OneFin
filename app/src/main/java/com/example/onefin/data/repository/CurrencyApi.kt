package com.example.onefin.data.repository

import com.example.onefin.domain.model.Response
import retrofit2.http.GET

interface CurrencyApi {
 @GET("live?access_key=48f9fd21c432bae183cfe4d28f755691&source=BYN&currencies=USD,EUR,RUB,UAH,PLN,GBP,CNY,KZT,CHF,CZK,TRY,GEL,VND,AMD,BRL,NZD,INR,AED")
 suspend fun fetchData() : Response
}