package com.cursosant.mviarch.mainModule.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.cursosant.mviarch.R
import com.cursosant.mviarch.databinding.ActivityMainBinding
import com.cursosant.mviarch.loginModule.view.LoginFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        launchLoginUI()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        binding.navView.setupWithNavController(navController)
    }

    fun launchLoginUI() {
        val fragment = LoginFragment()
        supportFragmentManager.beginTransaction().apply {
            add(R.id.container_main, fragment)
                .commit()
        }
    }

    fun setupNavView(isVisible: Boolean) {
        binding.navView.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}