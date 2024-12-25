package com.cursosant.mviarch.promoModule.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.cursosant.mviarch.databinding.FragmentPromoBinding
import com.cursosant.mviarch.commonModule.dataAccess.local.getAllPromos
import com.cursosant.mviarch.promoModule.PromoModuleFactory
import com.cursosant.mviarch.promoModule.PromoViewModel
import com.cursosant.mviarch.promoModule.intent.PromoIntent
import com.cursosant.mviarch.promoModule.model.Database
import com.cursosant.mviarch.promoModule.model.PromoRepository
import com.cursosant.mviarch.promoModule.model.PromoState
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch


class PromoFragment : Fragment() {
    private var _binding: FragmentPromoBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: PromoListAdapter
    private lateinit var vm: PromoViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentPromoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupAdapter()
        setupRecyclerView()
        setupObservers()
    }

    private fun setupViewModel() {
        vm = ViewModelProvider(this, PromoModuleFactory(PromoRepository(Database())))[PromoViewModel::class.java]
    }

    private fun setupAdapter() {
        adapter = PromoListAdapter()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = this@PromoFragment.adapter
        }
    }

    private fun setupObservers(){
        lifecycleScope.launch {
            vm.state.collect{
                state -> when(state){
                    is PromoState.Init -> getPromos()
                    is PromoState.RequestPromoSuccess -> adapter.submitList(getAllPromos())
                    is PromoState.Fail -> showMsg(state.msgRes)
                }
            }
        }
    }

    private fun getPromos() {
            lifecycleScope.launch { vm.channel.send(PromoIntent.RequestPromos) }
    }

    private fun showMsg(msgRes: Int) {
        Snackbar.make(binding.root, msgRes, Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}