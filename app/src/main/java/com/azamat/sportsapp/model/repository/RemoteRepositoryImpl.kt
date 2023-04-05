package com.azamat.sportsapp.model.repository

import com.azamat.sportsapp.base.BaseApiResult
import com.azamat.sportsapp.base.BaseRepository
import com.azamat.sportsapp.model.remote.api.ApiService
import com.azamat.sportsapp.model.remote.response.PlayerResponse

class RemoteRepositoryImpl(private val apiService: ApiService) : RemoteRepository,
    BaseRepository() {
    override suspend fun getPlayerDetails(playerName: String): BaseApiResult<PlayerResponse> {
        return safeApi {
            apiService.getPlayerDetails(playerName)
        }
    }

}