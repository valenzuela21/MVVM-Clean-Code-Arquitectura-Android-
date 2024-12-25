package com.cursosant.recomendedarch.mainModule.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.cursosant.recomendedarch.R
import com.cursosant.recomendedarch.common.viewModel.ShareViewModel
import com.cursosant.recomendedarch.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private val vm: ShareViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.nav_host_fragment_activity_main)
        binding.navView.setupWithNavController(navController)

        launchLoginUI()
        setupObservers()
    }

    private fun setupObservers(){
        vm.showNavView.observe(this){
            showNavView -> setupNavView(showNavView)
        }
        vm.isSignOut.observe(this){ isSignOut ->
            if(isSignOut){
                setupNavView(false)
                launchLoginUI()
            }
        }
    }

    private fun launchLoginUI() {
        navController.navigate(R.id.navigation_login)
    }

    private fun setupNavView(isVisible: Boolean) {
        binding.navView.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}