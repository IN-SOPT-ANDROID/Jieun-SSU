package org.sopt.sample.presentation.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContentProviderCompat.requireContext
import coil.load
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.sopt.sample.databinding.FragmentMusicBinding
import org.sopt.sample.presentation.viewmodel.MusicUploadViewModel
import org.sopt.sample.data.service.ContentUriRequestBody


class MusicFragment: Fragment() {
    private var _binding: FragmentMusicBinding?=null
    private val binding get() = requireNotNull(_binding) { "노 바인딩.." }
    private val viewModel by viewModels<MusicUploadViewModel>()
    private val map: HashMap<String, RequestBody> = hashMapOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.tvBringImg.setOnClickListener{
        imagePickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
    }

    binding.btnAddMusic.setOnClickListener{
        val name = binding.etNewMusicTitle.text.toString()
        val singer = binding.etSingerNew.text.toString()

        viewModel.postMusic(map!!)
        Toast.makeText(context, "음악이 추가되었습니다.", Toast.LENGTH_SHORT).show()
    }
}

private val imagePickerLauncher = registerForActivityResult(
    ActivityResultContracts.PickVisualMedia()
) {
    if (it != null) {
    binding.imgProfile.load(it)
    viewModel.setRequestBody(ContentUriRequestBody(requireContext(), it))
    }
}

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.tvBringImg.setOnClickListener{
        imagePickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
    }

    binding.btnAddMusic.setOnClickListener{
        val name = binding.etNewMusicTitle.text.toString()
        var requestName: RequestBody = name.toRequestBody("text/plain".toMediaTypeOrNull())
        val singer = binding.etSingerNew.text.toString()
        val requestSinger: RequestBody =
            singer.toRequestBody("text/plain".toMediaTypeOrNull())

        map["singer"] = requestSinger
        map["title"] = requestName

        viewModel.postMusic(map!!)
        Toast.makeText(context, "음악 추가 성공", Toast.LENGTH_SHORT).show()
    }
}

private val imagePickerLauncher = registerForActivityResult(
    ActivityResultContracts.PickVisualMedia()
) {
    if (it != null) {
    binding.imgProfile.load(it)
    viewModel.setRequestBody(ContentUriRequestBody(requireContext(), it))
    }
}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMusicBinding.inflate(inflater, container, false)
        return binding.root
    }


}