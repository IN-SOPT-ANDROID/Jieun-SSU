package org.sopt.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.R
import org.sopt.sample.databinding.FragmentHomeBinding
import org.sopt.sample.remote.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
    private val FollowerService=FollowerServicePool.FollowerService
        get() = _binding!!  //java
    private lateinit var homeAdapter: HomeAdapter //HomeAdapter을 homeAdpater라고 부른다.
    private val userViewModel by viewModels<FollowerViewModel>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
//    private fun initAdapter() {
//        homeAdapter = HomeAdapter() //HomeAdapter 내용을 homeAdapter에 저장한다.
//        binding.rvHome.adapter = homeAdapter  //홈 리사이클러 명이 rvHome인 것이다.
//        initUserData()
//    }
    //외부에서 어댑터 내부의 리스트에 접근하면 크래쉬가 났을 때 클래스 내부만 봐도 될 문제를 다른 파일에 있는 코드도
    // 다 뜯어봐야하는 문제가 발생할 수 있어서 최대한 자원은 내부에서 사용하도록 하고 함수를 뚫어서 클래스 내부에서 로직을 실행하도록 하는게 좋다.
//    private fun initUserData(){
//        homeAdapter.userList.addAll(
//            listOf(  //데이터 조작을 해준다. item_home은 그냥 전체적인 틀만 잡아줄 뿐이다. 그건 이제 빈 깡통.
//                UserData(R.drawable.git,"안드1","영주"),  //데이터를 여기서 바꾸면 된다.
//                UserData(R.drawable.git,"안드2","대환"),
//                UserData(R.drawable.git,"안드3","하정"),
//                UserData(R.drawable.git,"안드4","지은"),
//                UserData(R.drawable.git,"안드5","a"),
//                UserData(R.drawable.git,"안드6","b"),
//                UserData(R.drawable.git,"안드7","c"),
//                UserData(R.drawable.git,"안드8","d"),
//                UserData(R.drawable.git,"안드9","e"),
//                UserData(R.drawable.git,"안드10","f")
//            )
//        )
//        homeAdapter.notifyDataSetChanged()  //리스트가 업데이트가 됐다는 것을 알려준다.
//    }
    override fun onCreatedView(view: View,savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        FollowerService.getData().enqueue(object:Callback<ResponseFollowerDTO>{
            override fun onResponse(
                call: Call<ResponseFollowerDTO>,
                response: Response<ResponseFollowerDTO>
            ) {
                if (response.isSuccessful) {
                   userViewModel.followerList.addAll(response.body()?.data!!)
                    val adapter = FollowerAdapter(requireContext())
                    binding.rvHome.adapter = adapter
                    adapter.UserList(userViewModel.followerList)
                } else if (response.code() == 404) {
                    Snackbar.make(binding.root, "404 error", Snackbar.LENGTH_LONG)
                        .show()
                } else if (response.code() == 401) {
                    Snackbar.make(binding.root, "401 error", Snackbar.LENGTH_LONG)
                        .show()
                }
            }

            override fun onFailure(call: Call<ResponseFollowerDTO>, t: Throwable) {
                Toast.makeText(this@HomeFragment,"실패",Toast.LENGTH_SHORT).show()
            }
        })
    }


    fun viewFirst() {
        binding.rvHome.post {
            binding.rvHome.scrollToPosition(0)
        }
    } // 리사이클러 뷰의 최상단으로 이동

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initAdapter()  //함수 생성
        _binding=null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}