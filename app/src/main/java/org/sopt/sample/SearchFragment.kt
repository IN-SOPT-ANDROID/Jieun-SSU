package org.sopt.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import org.sopt.sample.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private val musicViewModel by viewModels<MusicViewModel>()
    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        musicViewModel.getData()
        musicViewModel.successGet.observe(viewLifecycleOwner){ it ->
            if(it){
                val adapter = context?.let { it1 ->
                    musicViewModel.getResult.value?.let {
                        MusicAdapter(musicViewModel.getResult.value!!.data, it1).apply {
                            setRepoList(musicViewModel.getResult.value!!.data)
                        }
                    }
                }
                binding.rvMusic.adapter = adapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }
}