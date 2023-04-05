package com.azamat.sportsapp.di

import com.azamat.sportsapp.ui.fragment.playerlist.PlayerListViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    factory { Dispatchers.IO }
    viewModel { PlayerListViewModel(get(), get()) }
}