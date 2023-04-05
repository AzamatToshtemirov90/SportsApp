package com.azamat.sportsapp.ui.fragment.playerlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.azamat.sportsapp.base.BaseViewModel
import com.azamat.sportsapp.base.Status
import com.azamat.sportsapp.model.remote.response.PlayerResponse
import com.azamat.sportsapp.model.repository.RemoteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class PlayerListViewModel (
    private val ioDispatcher: CoroutineDispatcher,
    private val remoteRepository: RemoteRepository,
) : BaseViewModel() {

    private val TAG = PlayerListViewModel::class.java.simpleName

    private var _playerDetails = MutableLiveData<PlayerResponse?>()
    val playerDetails: LiveData<PlayerResponse?> = _playerDetails

    fun getPlayerDetails(playerName: String) {
        viewModelScope.launch(ioDispatcher) {
            remoteRepository.getPlayerDetails(playerName).let { baseApiResult->
                when (baseApiResult.status) {
                    Status.SUCCESS -> {
                        baseApiResult.data?.let { it->
                            _playerDetails.postValue(it)
                        }
                    }
                    Status.ERROR -> {
                        _error.postValue(baseApiResult.message)
                        Log.d(TAG,baseApiResult.message.toString())
                    }
                }
            }
        }

    }
}