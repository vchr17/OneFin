package com.example.onefin.data.database

import android.app.Application
import com.example.onefin.domain.domainDI.domainModule
import com.example.onefin.presentation.appDI.appModule
import dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application(){

        override fun onCreate() {
            super.onCreate()

            startKoin{
                androidContext(this@Application)
                modules(listOf(appModule, domainModule, dataModule))
            }

        }


    }