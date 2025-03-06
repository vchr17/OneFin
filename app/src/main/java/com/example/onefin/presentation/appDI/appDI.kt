package com.example.onefin.presentation.appDI


import com.example.onefin.presentation.view_models.CurrencyViewModel
import com.example.onefin.presentation.view_models.ExchangeViewModel
import com.example.onefin.presentation.view_models.FavouritesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<CurrencyViewModel>{
        CurrencyViewModel(
            fetchData = get(),
            readData = get(),
            addData = get(),
            setFavourite = get(),
            updateCurrency = get()
        )
    }

    viewModel<FavouritesViewModel>{
        FavouritesViewModel(
            getFavourite = get(),
            setFavourite = get()
        )
    }

    viewModel<ExchangeViewModel>{
        ExchangeViewModel(
            getData = get()
        )
    }



}