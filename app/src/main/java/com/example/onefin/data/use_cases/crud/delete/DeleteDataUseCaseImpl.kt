package com.example.onefin.data.use_cases.crud.delete

import com.example.onefin.domain.repository.MainRepository
import com.example.onefin.domain.use_cases.crud.delete.DeleteDataUseCase

class DeleteDataUseCaseImpl(private val dao : MainRepository) : DeleteDataUseCase {

    override suspend fun invoke() {
        dao.getDao().deleteData()
    }
}