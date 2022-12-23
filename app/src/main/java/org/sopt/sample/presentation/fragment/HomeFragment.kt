package org.sopt.sample
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.sample.databinding.FragmentHomeBinding
import org.sopt.sample.entity.UserData

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
        return binding.root
    }
        private fun initAdapter() {
            homeAdapter = HomeAdapter() //HomeAdapter 내용을 homeAdapter에 저장한다.
            binding.rvHome.adapter = homeAdapter  //홈 리사이클러 명이 rvHome인 것이다.
            initUserData()
        }
        //외부에서 어댑터 내부의 리스트에 접근하면 크래쉬가 났을 때 클래스 내부만 봐도 될 문제를 다른 파일에 있는 코드도
        // 다 뜯어봐야하는 문제가 발생할 수 있어서 최대한 자원은 내부에서 사용하도록 하고 함수를 뚫어서 클래스 내부에서 로직을 실행하도록 하는게 좋다.
        private fun initUserData(){
            homeAdapter.userList.addAll(
                listOf(  //데이터 조작을 해준다. item_home은 그냥 전체적인 틀만 잡아줄 뿐이다. 그건 이제 빈 깡통.
                    UserData(R.drawable.git,"안드1","영주","0@gmail.com"),  //데이터를 여기서 바꾸면 된다.
                    UserData(R.drawable.git,"안드2","대환","1@gmail.com"),
                    UserData(R.drawable.git,"안드3","하정","2@gmail.com"),
                    UserData(R.drawable.git,"안드4","지은","3@gmail.com"),
                    UserData(R.drawable.git,"안드5","a","4@gmail.com"),
                    UserData(R.drawable.git,"안드6","b","5@gmail.com"),
                    UserData(R.drawable.git,"안드7","c","6@gmail.com"),
                    UserData(R.drawable.git,"안드8","d","7@gmail.com"),
                    UserData(R.drawable.git,"안드9","e","8@gmail.com"),
                    UserData(R.drawable.git,"안드10","f","9@gmail.com")
                )
            )
            homeAdapter.notifyDataSetChanged()  //리스트가 업데이트가 됐다는 것을 알려준다.
        }


        fun viewFirst() {
            binding.rvHome.post {
                binding.rvHome.scrollToPosition(0)
            }
        } // 리사이클러 뷰의 최상단으로 이동

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            initAdapter()  //함수 생성
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }