package com.example.onefin.data.use_cases.crud.read

import com.example.onefin.domain.model.Money
import com.example.onefin.domain.repository.MainRepository
import com.example.onefin.domain.use_cases.crud.read.GetDataUseCase

class GetDataUseCaseImpl(private val dao : MainRepository) : GetDataUseCase {

    override suspend fun invoke(): MutableList<Money> {
        return dao.getDao().getData()
    }


}