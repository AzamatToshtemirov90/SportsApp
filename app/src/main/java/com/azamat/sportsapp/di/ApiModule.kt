package com.azamat.sportsapp.di

import com.azamat.sportsapp.model.remote.api.ApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single(createdAtStart = false) { get<Retrofit>().create(ApiService::class.java) }
}