package com.example.onefin.data.use_cases.crud.create

import com.example.onefin.domain.model.Money
import com.example.onefin.domain.repository.MainRepository
import com.example.onefin.domain.use_cases.crud.create.AddDataUseCase

class AddDataUseCaseImpl(private val dao : MainRepository) : AddDataUseCase{
    override suspend fun invoke(list: MutableList<Money>){
        dao.getDao().insertItem(list)
    }

}