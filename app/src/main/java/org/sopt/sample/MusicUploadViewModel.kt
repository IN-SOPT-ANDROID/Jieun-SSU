package org.sopt.sample

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.RequestBody
import org.sopt.sample.remote.ContentUriRequestBody
import org.sopt.sample.remote.ResponseUploadDTO
import org.sopt.sample.remote.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MusicUploadViewModel : ViewModel() {
    private val musicUploadService = ServicePool.musicUploadService
    private val _image = MutableLiveData<ContentUriRequestBody>()
    val image: LiveData<ContentUriRequestBody>
        get() = _image

    fun setRequestBody(requestBody: ContentUriRequestBody) {
        _image.value = requestBody
    }

    fun postMusic(map: HashMap<String, RequestBody>){
        musicUploadService.postMusic(
            image.value!!.toFormData(),
            map
        ).enqueue(object: Callback<ResponseUploadDTO> {
            override fun onResponse(
                call: Call<ResponseUploadDTO>,
                response: Response<ResponseUploadDTO>
            ) {
                when (response.code()) {
                    400 -> {
                        Toast.makeText("400 에러", Toast.LENGTH_SHORT).show();
                    }
                    500 -> {
                        Toast.makeText("500 에러", Toast.LENGTH_SHORT).show();
                    }
                }
                if (response.isSuccessful) {
                    Toast.makeText("음악 전송 선공", Toast.LENGTH_SHORT).show();
                }
            }

            override fun onFailure(call: Call<ResponseUploadDTO>, t: Throwable) {
                Toast.makeText("서버 통신 에러", Toast.LENGTH_SHORT).show();
            }
        })
    }
}