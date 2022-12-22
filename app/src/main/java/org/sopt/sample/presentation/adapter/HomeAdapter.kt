package org.sopt.sample.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.entity.UserData
import org.sopt.sample.databinding.ItemHomeBinding
import org.sopt.sample.databinding.ItemHeaderBinding



    class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var itemHeaderBinding: ItemHeaderBinding
    private lateinit var itemHomeBinding: ItemHomeBinding
    val userList = mutableListOf<UserData>()  //userdata의 리스트를 userlist로 받음
    val Header = 0 // 현재 뷰 객체가 타이틀임을 감지하기 위한 변수
    val Body = 1
        //const는 클래스 외부나 companion object 내에서 사용이 가능하다.
        //const를 사용하면 값이 컴파일 단에서 결정된다.
        //상수면 밑처럼 남기는 것이 좋다. 또한 private로 제한하는 게 좋다.

        /*
        companion object {
        const val HEADER = 0
        const val BODY = 1
        }
        */
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
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(position== 0)   //헤더를 가져오기
            Header
        else Body
    }

    override fun getItemCount(): Int = userList.size
        // inner class ~ : 이건 사용을 지양하는 것이 좋다.
        // 이유: inner 클래스를 사용하면 내부클래스에서 외부클래스의 자원을 참조할 수 있는데 이때 외부에 있는 변수가 소멸된 상태라면 의도치 안은 크러쉬를 발생시킬 수 있다.
    class HomeViewHolder(
        private val binding: ItemHomeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: UserData) {
//            binding.ivGit.setImageResource(data.img)
//            binding.tvContent.text=data.title
//            binding.tvName.text=data.content
        }
    }

    inner class HeaderViewHolder(
        private val binding: ItemHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root)
}
