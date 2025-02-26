package com.example.onefin.presentation.appDI


import com.example.onefin.presentation.view_models.CurrencyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<CurrencyViewModel>{
        CurrencyViewModel(
            fetchData = get(),
            readData = get(),
            addData = get(),
            deleteData = get()
        )
    }



}