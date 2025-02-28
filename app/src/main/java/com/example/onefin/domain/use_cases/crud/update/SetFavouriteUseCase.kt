package com.example.onefin.domain.use_cases.crud.update

interface SetFavouriteUseCase {

    suspend fun invoke(name: String)

}