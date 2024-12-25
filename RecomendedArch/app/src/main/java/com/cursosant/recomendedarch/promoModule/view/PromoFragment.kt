package com.cursosant.recomendedarch.promoModule.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cursosant.recomendedarch.promoModule.viewModel.PromoViewModel
import com.cursosant.recomendedarch.databinding.FragmentPromoBinding
import com.cursosant.recomendedarch.promoModule.view.adapaters.PromoListAdapter
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject

class PromoFragment : Fragment() {
    private var _binding: FragmentPromoBinding? = null
    private val binding get() = _binding!!

    private val adapter: PromoListAdapter by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentPromoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupRecyclerView()
        setupObservers()
    }

    private fun setupViewModel() {
        val vm: PromoViewModel by inject()
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, vm)
    }

    private fun setupObservers() {
        binding.viewModel?.let { vm ->
            vm.snackbarMsg.observe(viewLifecycleOwner) { resMsg ->
                resMsg?.let {showMsg(resMsg)}
            }
            vm.promos.observe(viewLifecycleOwner) { promos ->
                adapter.submitList(promos)
            }
        }
    }


    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = this@PromoFragment.adapter
        }
    }

    private fun showMsg(msgRes: Int) {
        Snackbar.make(binding.root, msgRes, Snackbar.LENGTH_SHORT).show()
    }


    override fun onPause() {
        super.onPause()
        binding.viewModel?.onPause()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}