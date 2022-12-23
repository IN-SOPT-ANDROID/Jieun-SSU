package org.sopt.sample.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivityHomeBinding
import org.sopt.sample.presentation.fragment.HomeFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentFragment=supportFragmentManager.findFragmentById(R.id.home_container)
        if(currentFragment==null){
            supportFragmentManager.beginTransaction().add(R.id.home_container, HomeFragment()).commit()
        }
        binding.tvName.text="이름: " + intent.getStringExtra("id")
        binding.tvEmail.text="Email: "+ intent.getStringExtra("email")
    }
    private fun changeFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.home_container, fragment).commit()
    }
}