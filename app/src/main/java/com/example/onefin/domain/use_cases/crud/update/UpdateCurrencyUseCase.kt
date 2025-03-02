package com.example.onefin.domain.use_cases.crud.update

interface UpdateCurrencyUseCase {

    suspend fun invoke(name: String, stamp: Long, value: Double)

}