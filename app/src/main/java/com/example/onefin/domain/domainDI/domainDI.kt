package com.example.onefin.domain.domainDI

import com.example.onefin.data.use_cases.DataFetchImpl
import com.example.onefin.data.use_cases.crud.create.AddDataUseCaseImpl
import com.example.onefin.data.use_cases.crud.delete.DeleteDataUseCaseImpl
import com.example.onefin.data.use_cases.crud.read.GetDataUseCaseImpl
import com.example.onefin.data.use_cases.crud.read.GetFavouritesUseCaseImpl
import com.example.onefin.data.use_cases.crud.update.SetFavouriteUseCaseImpl
import com.example.onefin.data.use_cases.crud.update.UpdateCurrencyUseCaseImpl
import com.example.onefin.domain.use_cases.DataFetch
import com.example.onefin.domain.use_cases.crud.create.AddDataUseCase
import com.example.onefin.domain.use_cases.crud.delete.DeleteDataUseCase
import com.example.onefin.domain.use_cases.crud.read.GetDataUseCase
import com.example.onefin.domain.use_cases.crud.read.GetFavouritesUseCase
import com.example.onefin.domain.use_cases.crud.update.SetFavouriteUseCase
import com.example.onefin.domain.use_cases.crud.update.UpdateCurrencyUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<DataFetch> {
    DataFetchImpl(retrofit = get())
    }

    factory<UpdateCurrencyUseCase>{
        UpdateCurrencyUseCaseImpl(dao = get())
    }

    factory<AddDataUseCase>{
        AddDataUseCaseImpl(dao = get())
    }

    factory<DeleteDataUseCase>{
        DeleteDataUseCaseImpl(dao = get())
    }

    factory<GetDataUseCase>{
        GetDataUseCaseImpl(dao = get())
    }

    factory<GetFavouritesUseCase>{
        GetFavouritesUseCaseImpl(dao = get())
    }

    factory<SetFavouriteUseCase>{
        SetFavouriteUseCaseImpl(dao = get())
    }


}