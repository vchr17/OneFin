package com.example.onefin.domain.use_cases.crud.update

interface SetFavouriteStatusUseCase {

    suspend fun invoke(name: String, ifFavourite: Int)

}