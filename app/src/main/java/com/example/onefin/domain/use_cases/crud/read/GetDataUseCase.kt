package com.example.onefin.domain.use_cases.crud.read

import com.example.onefin.domain.model.Money

interface GetDataUseCase {

    suspend fun invoke() : MutableList<Money>

}