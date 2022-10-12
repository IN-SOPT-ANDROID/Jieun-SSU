package org.sopt.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.sample.R
import org.sopt.sample.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding!!  //java
    private lateinit var homeAdapter: HomeAdapter //HomeAdapter을 homeAdpater라고 부른다.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        initAdapter()  //함수 생성
        return binding.root
    }
    private fun initAdapter(){
        homeAdapter = HomeAdapter() //HomeAdapter 내용을 homeAdapter에 저장한다.
        binding.rvHome.adapter=homeAdapter  //홈 리사이클러 명이 rvHome인 것이다.
        homeAdapter.userList.addAll(
            listOf(  //데이터 조작을 해준다. item_home은 그냥 전체적인 틀만 잡아줄 뿐이다. 그건 이제 빈 깡통.
                UserData(R.drawable.git,"안드1","영주"),  //데이터를 여기서 바꾸면 된다.
                UserData(R.drawable.git,"안드2","대환"),
                UserData(R.drawable.git,"안드3","하정"),
                UserData(R.drawable.git,"안드4","지은"),
                UserData(R.drawable.git,"안드5","a"),
                UserData(R.drawable.git,"안드6","b"),
                UserData(R.drawable.git,"안드7","c"),
                UserData(R.drawable.git,"안드8","d"),
                UserData(R.drawable.git,"안드9","e"),
                UserData(R.drawable.git,"안드10","f")
            )
        )
        homeAdapter.notifyDataSetChanged()  //리스트가 업데이트가 됐다는 것을 알려준다.
    }

    fun viewFirst() {
        binding.rvHome.scrollToPosition(0)
    } // 리사이클러 뷰의 최상단으로 이동

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}