package com.cursosant.recomendedarch.loginModule.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cursosant.mvvmarch.loginModule.viewModel.LoginViewModel
import com.cursosant.recomendedarch.R
import com.cursosant.recomendedarch.common.viewModel.ShareViewModel
import com.cursosant.recomendedarch.mainModule.view.MainActivity
import com.cursosant.recomendedarch.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val sVm: ShareViewModel by activityViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupObservers()
    }

    private fun setupViewModel() {
        val vm: LoginViewModel by inject()
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, vm)
    }

    private fun setupObservers() {
        binding.viewModel?.let { vm ->
            vm.snackbarMsg.observe(viewLifecycleOwner) { resMsg ->
               resMsg?.let { showMsg(resMsg) }
            }
            vm.isAuthValid.observe(viewLifecycleOwner) { isValid ->
                if (isValid) closeLoginUI()
            }
        }
    }

    private fun showMsg(msgRes: Int) {
        Snackbar.make(binding.root, msgRes, Snackbar.LENGTH_SHORT).show()
    }

    private fun closeLoginUI() {
        sVm.showNavView.value = true
        findNavController().navigate(R.id.navigation_home)
    }

    override fun onPause() {
        super.onPause()
        binding.viewModel?.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}