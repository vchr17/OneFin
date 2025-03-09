package com.example.onefin.data.use_cases.crud.update

import com.example.onefin.domain.repository.MainRepository
import com.example.onefin.domain.use_cases.crud.update.SetFavouriteStatusUseCase

class SetFavouriteStatusUseCaseImpl(private val dao: MainRepository) : SetFavouriteStatusUseCase {

    override suspend fun invoke(name: String, ifFavourite: Int) {
        dao.getDao().setFavourite(name, ifFavourite)
    }

}