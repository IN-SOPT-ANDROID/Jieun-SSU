package org.sopt.sample

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.databinding.ActivityHomeBinding
import org.sopt.sample.remote.Person
import org.sopt.sample.remote.ResponseFollowerDTO

class FollowerAdapter(Item: List<Person>, context: Context):RecyclerView.Adapter<FollowerAdapter.ViewHolder>(){
    private val inflater by lazy {LayoutInflater.from(context)}
    private var userList: List<ResponseFollowerDTO.Person> = emptyList()

    class UserViewHolder(private val binding: ActivityHomeBinding):RecyclerView.ViewHolder(binding.root){
        fun User(user: ResponseFollowerDTO.Person){
            binding.tvName.text=Person.first_name
            binding.tvEmail.text=Person.email
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       val binding=ActivityHomeBinding.inflate(inflater,parent,false)
        return UserViewHolder(binding)
    }
    override fun OnBindViewHolder(holder:RecyclerView.ViewHolder, position:Int){
        if(holder is UserViewHolder).holder.User.(userList[position])
    }

    override fun getItemCount()=userList.size

    fun UserList(userList: List<ResponseFollowerDTO.Person>){
        this.userList=userList.toList()
        notifyDataSetChanged()
    }
}