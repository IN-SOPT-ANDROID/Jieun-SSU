package org.sopt.sample.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.databinding.ActivityHomeBinding
import org.sopt.sample.data.model.response.ResponseFollowerDTO

class FollowerAdapter(Item: List<ResponseFollowerDTO.Person>, context: Context):RecyclerView.Adapter<FollowerAdapter.UserViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    private var userList: List<ResponseFollowerDTO.Person> = emptyList()

    class UserViewHolder(private val binding: ActivityHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun User(user: ResponseFollowerDTO.Person) {
            binding.tvName.text = ResponseFollowerDTO.Person.first_name
            binding.tvEmail.text = ResponseFollowerDTO.Person.email
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ActivityHomeBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        if (holder is UserViewHolder)
            holder.User(userList[position])
    }

    override fun getItemCount() = userList.size

    fun UserList(userList: List<Person>) {
        this.userList = userList.toList()
        notifyDataSetChanged()
    }
}
