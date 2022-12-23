package org.sopt.sample.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivityMainBinding
import org.sopt.sample.presentation.fragment.GalleryFragment
import org.sopt.sample.presentation.fragment.HomeFragment
import org.sopt.sample.presentation.fragment.MusicFragment
import org.sopt.sample.presentation.fragment.SearchFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val homeFragment = HomeFragment()
    private val musicFragment = MusicFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //homefragment를 처음에 딱 창 띄우지마자 뜨게끔 하고 싶어서..
        //homecontainer 안에 하나에 하나씩 여러 fragment를 넣을 수 있는 것이다.
        supportFragmentManager.beginTransaction().add(R.id.home_container, HomeFragment()).commit()

        binding.bnvMain.setOnItemSelectedListener {
            val transaction = supportFragmentManager.beginTransaction()
            when (it.itemId) {  //코틀린에서 가정문인 것  //it-> 메뉴
                R.id.menu_home -> transaction.replace(R.id.home_container, HomeFragment())
                R.id.menu_gallery -> transaction.replace(
                    R.id.home_container,  //이걸 GalleryFragment로 바꾸겟다.
                    GalleryFragment()
                )
                R.id.menu_search -> transaction.replace(
                    R.id.home_container,
                    SearchFragment()
                )
                else -> error("item id :${it.itemId}) is cannot be selected")
            }
            transaction.commit()
            true
        }

    }
}

