package com.example.onefin.data.use_cases.crud.update

import com.example.onefin.domain.repository.MainRepository
import com.example.onefin.domain.use_cases.crud.update.UpdateCurrencyUseCase

class UpdateCurrencyUseCaseImpl(private val dao: MainRepository) : UpdateCurrencyUseCase{

    override suspend fun invoke(name: String, stamp: Long, value: Double) {
        dao.getDao().updateCurrency(name, stamp, value)
    }


}