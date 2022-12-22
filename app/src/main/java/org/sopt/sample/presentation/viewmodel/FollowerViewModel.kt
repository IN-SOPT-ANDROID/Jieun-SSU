package org.sopt.sample.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.sample.data.service.FollowerServicePool
import org.sopt.sample.data.model.response.ResponseFollowerDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerViewModel : ViewModel() {
    val followerList = mutableListOf<ResponseFollowerDTO.Person>()
    private val followerService = FollowerServicePool.FollowerService

    private val _getResult: MutableLiveData<ResponseFollowerDTO> = MutableLiveData()
    val getResult: LiveData<ResponseFollowerDTO>
        get() = _getResult
    private val _successGet = MutableLiveData<Boolean>()
    val successGet: LiveData<Boolean> = _successGet
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun getData() {
        followerService.getData(2).enqueue(object: Callback<ResponseFollowerDTO> {   //여기에 요청하는 값인 2를 넣기
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