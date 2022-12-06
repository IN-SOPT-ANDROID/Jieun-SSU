package org.sopt.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.databinding.FragmentHomeBinding
import org.sopt.sample.remote.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding!!  //java
    private lateinit var homeAdapter: HomeAdapter //HomeAdapter을 homeAdpater라고 부른다.
    private val followerViewModel by viewModels<FollowerViewModel> {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        followerViewModel.getData()
        followerViewModel.successGet.observe(viewLifecycleOwner){ success ->
            if(success){
                val adapter = context?.let { it1 ->
                    followerViewModel.getResult.value?.let {
                        FollowerAdapter(FollowerViewModel.getResult.value!!.data, it1).apply {
                            UserData(FollowerViewModel.getResult.value!!.data) //이 부분 모르겠음음                        }
                        }
                    }
                }
                binding.rvRepos.adapter = adapter
            }

    override fun onCreatedView(view: View,savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        FollowerService.getData().enqueue(object:Callback<ResponseFollowerDTO>{
            override fun onResponse(
                call: Call<ResponseFollowerDTO>,
                response: Response<ResponseFollowerDTO>
            ) {
                if (response.isSuccessful) {
                   FollowerViewModel.followerList.addAll(response.body()?.data!!)
                    val adapter = FollowerAdapter(requireContext())
                    binding.rvHome.adapter = adapter
                    adapter.UserList(FollowerViewModel.followerList)
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