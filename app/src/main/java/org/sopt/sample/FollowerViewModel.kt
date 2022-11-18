package org.sopt.sample

import androidx.lifecycle.ViewModel
import org.sopt.sample.remote.ResponseFollowerDTO

class FollowerViewModel : ViewModel() {
    val followerList = mutableListOf<ResponseFollowerDTO>()
}