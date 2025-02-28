package com.example.onefin.domain.use_cases

import com.example.onefin.domain.model.Response

interface DataFetch {

    suspend fun init() : Response

}