package com.azamat.sportsapp.di

import com.azamat.sportsapp.model.remote.RetrofitClient
import com.azamat.sportsapp.utils.OkHttpClientFactory
import com.azamat.sportsapp.utils.ResponseHandler
import org.koin.dsl.module

val networkModule = module {
    single { RetrofitClient(OkHttpClientFactory().create()).retrofit }
    factory { ResponseHandler() }
}