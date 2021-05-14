package com.sincronizacionweb.mitienda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sincronizacionweb.mitienda.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater )
        setContentView(binding.root)

        val tabsAdapter = TabsAdapter(supportFragmentManager)
        tabsAdapter.addFragments(LoginFragment(), "Login")
        tabsAdapter.addFragments(SignupFragment(), "SignUp")

        binding.viewpager.adapter = tabsAdapter
        binding.tabslayout.setupWithViewPager(binding.viewpager)


    }
}