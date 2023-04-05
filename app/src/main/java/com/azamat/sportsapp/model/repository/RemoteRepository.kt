package com.azamat.sportsapp.model.repository

import com.azamat.sportsapp.base.BaseApiResult
import com.azamat.sportsapp.model.remote.response.PlayerResponse

interface RemoteRepository {

    suspend fun getPlayerDetails(playerName: String): BaseApiResult<PlayerResponse>

}