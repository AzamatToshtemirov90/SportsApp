package com.azamat.sportsapp.model.remote.api

import com.azamat.sportsapp.model.Constants
import com.azamat.sportsapp.model.remote.response.PlayerResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(Constants.Headers.SEARCH_ATTRIBUTES)
    suspend fun getPlayerDetails(@Query("p") playerName: String): PlayerResponse
}