package org.sopt.sample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.sample.remote.FollowerServicePool
import org.sopt.sample.remote.ResponseFollowerDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerViewModel : ViewModel() {
    val followerList = mutableListOf<ResponseFollowerDTO>()
    private val followerService = FollowerServicePool.FollowerService

    private val _getResult: MutableLiveData<ResponseFollowerDTO> = MutableLiveData()
    val getResult: LiveData<ResponseFollowerDTO>
        get() = _getResult
    private val _successGet = MutableLiveData<Boolean>()
    val successGet: LiveData<Boolean> = _successGet
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun getData() {
        followerService.getData().enqueue(object: Callback<ResponseFollowerDTO> {
            override fun onResponse(
                call: Call<ResponseFollowerDTO>,
                response: Response<ResponseFollowerDTO>
            ) {
                when (response.code()) {
                    400 -> {
                        _successGet.value = false
                    }
                    500 -> {
                        _successGet.value = false
                    }
                }
                if (response.isSuccessful) {
                    _getResult.value = response.body()
                    _successGet.value = true
                    //
                }
            }

            override fun onFailure(call: Call<ResponseFollowerDTO>, t: Throwable) {
                _successGet.value = false
                _errorMessage.value = t.message
            }
        })
    }
}