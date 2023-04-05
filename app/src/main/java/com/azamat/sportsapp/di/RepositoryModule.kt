package com.azamat.sportsapp.di

import com.azamat.sportsapp.model.repository.RemoteRepository
import com.azamat.sportsapp.model.repository.RemoteRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<RemoteRepository> { RemoteRepositoryImpl(get()) }
}