package org.sopt.sample.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.data.model.response.ResponseFollowerDTO
import org.sopt.sample.databinding.ActivityHomeBinding

class FollowerAdapter(context: Context) : RecyclerView.Adapter<FollowerAdapter.UserViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    private var userList: List<ResponseFollowerDTO.Person> = emptyList()

    class UserViewHolder(private val binding: ActivityHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun User(user: ResponseFollowerDTO.Person) {
            binding.tvName.text = user.first_name
            binding.tvEmail.text = user.email
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ActivityHomeBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        if (holder is UserViewHolder) {
            holder.User(userList[position])
        }
    }

    override fun getItemCount() = userList.size

    // follower data를 갱신시켜 주는 함수
    fun userList(userList: List<ResponseFollowerDTO.Person>) {
        this.userList = userList.toList()
        notifyDataSetChanged()
    }
}

