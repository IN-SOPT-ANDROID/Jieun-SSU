package org.sopt.sample

import android.os.Bundle
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.sopt.sample.HomeFragment
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivityMainBinding

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
        binding.bnvMain.setOnItemReselectedListener { // 다시 아이템이 선택될 때 발생하는 이벤트리스너
            when (it.itemId) {
                R.id.menu_home -> {
                    homeFragment.viewFirst()
                    // 리사이클러 뷰를 최상단으로 끌어올리는 함수
                }
            }
            true
        }

    }
}

