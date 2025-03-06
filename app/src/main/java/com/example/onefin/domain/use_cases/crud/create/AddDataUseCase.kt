package com.example.onefin.domain.use_cases.crud.create

import com.example.onefin.domain.model.Money

interface AddDataUseCase {

    suspend fun invoke(list : MutableList<Money>)

}