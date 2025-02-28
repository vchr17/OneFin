package com.example.onefin.data.use_cases.crud.update

import com.example.onefin.domain.repository.MainRepository
import com.example.onefin.domain.use_cases.crud.update.SetFavouriteUseCase

class SetFavouriteUseCaseImpl(private val dao: MainRepository) : SetFavouriteUseCase {

    override suspend fun invoke(name: String) {
        dao.getDao().setFavourite(name, 1)
    }

}