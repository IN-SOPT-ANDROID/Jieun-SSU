package org.sopt.sample

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.databinding.ItemHomeBinding
import org.sopt.sample.databinding.ItemHeaderBinding



    public class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var itemHeaderBinding: ItemHeaderBinding
    private lateinit var itemHomeBinding: ItemHomeBinding
    val userList = mutableListOf<UserData>()  //userdata의 리스트를 userlist로 받음
    val Header = 0 // 현재 뷰 객체가 타이틀임을 감지하기 위한 변수
    val Body = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == Header) {
            itemHeaderBinding = ItemHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            HeaderViewHolder(itemHeaderBinding)
        } else {
            itemHomeBinding= ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            HomeViewHolder(itemHomeBinding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HomeViewHolder) {
            holder.onBind(userList[position])
        } else if (holder is HeaderViewHolder) {
            holder
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(userList[position].type== 0)
            Header
        else Body

    }

    override fun getItemCount(): Int = userList.size

    inner class HomeViewHolder(
        private val binding: ItemHomeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: UserData) {
            binding.imgGit.setImageResource(data.img)
            binding.txtContent.text=data.title
            binding.txtName.text=data.content
        }
    }

    inner class HeaderViewHolder(
        private val binding: ItemHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root)
}
