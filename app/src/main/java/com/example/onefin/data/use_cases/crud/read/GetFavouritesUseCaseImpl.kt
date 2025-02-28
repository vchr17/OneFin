package com.example.onefin.data.use_cases.crud.read

import com.example.onefin.data.database.MainDao
import com.example.onefin.domain.model.Money
import com.example.onefin.domain.repository.MainRepository
import com.example.onefin.domain.use_cases.crud.read.GetFavouritesUseCase

class GetFavouritesUseCaseImpl(private val dao : MainRepository) : GetFavouritesUseCase {

    override suspend fun invoke(): MutableList<Money> {
        return dao.getDao().getFavourites()
    }
}