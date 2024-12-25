package com.cursosant.mviarch.loginModule.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.cursosant.mviarch.mainModule.view.MainActivity
import com.cursosant.mviarch.databinding.FragmentLoginBinding
import com.cursosant.mviarch.commonModule.dataAccess.local.FakeFirebaseAuth
import com.cursosant.mviarch.loginModule.LoginViewModelFactory
import com.cursosant.mviarch.loginModule.LoginViewmodel
import com.cursosant.mviarch.loginModule.intent.LoginIntent
import com.cursosant.mviarch.loginModule.model.LoginRepository
import com.cursosant.mviarch.loginModule.model.LoginState
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var vm: LoginViewmodel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupButtons()
        setupObservers()
    }

    private fun setupViewModel(){
        vm = ViewModelProvider(this, LoginViewModelFactory(LoginRepository(FakeFirebaseAuth())))[LoginViewmodel::class.java]
    }

    private fun checkAuth() {
        lifecycleScope.launch {
           vm.channel.send(LoginIntent.CheckAuth)
        }
    }

    private fun setupButtons() {
        with(binding) {
            btnLogin.setOnClickListener {
                lifecycleScope.launch {
                  vm.channel.send(LoginIntent.Login(etUsername.text.toString(), etPin.text.toString()))
                }
            }
        }
    }

    private fun setupObservers(){
        lifecycleScope.launch {
            vm.state.collect{state ->
                when(state){
                    is LoginState.Init -> checkAuth()
                    is LoginState.ShowProgress -> {
                        showProgress(true)
                        showForm(false)
                    }
                    is LoginState.HideProgress -> showProgress(false)
                    is LoginState.AuthValid,
                    is LoginState.LoginSuccess ->  closeLoginUI()
                    is LoginState.Fail -> {
                        showMsg( state.msgRes)
                        showForm(true)
                    }
                }
            }
        }
    }

    private fun showMsg(msgRes: Int) {
        Snackbar.make(binding.root, msgRes, Snackbar.LENGTH_SHORT).show()
    }

    private fun showProgress(isVisible: Boolean) {
        binding.contentProgress.root.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    private fun showForm(isVisible: Boolean) {
        binding.contentLogin.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    private fun closeLoginUI() {
        (requireActivity() as MainActivity).setupNavView(true)
        requireActivity().supportFragmentManager.beginTransaction().apply {
            remove(this@LoginFragment)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}