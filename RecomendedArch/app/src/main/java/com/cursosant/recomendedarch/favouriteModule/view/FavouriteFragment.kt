package com.cursosant.recomendedarch.favouriteModule.view

import android.os.Bundle
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cursosant.recomendedarch.R
import com.cursosant.recomendedarch.common.entities.Wine
import com.cursosant.recomendedarch.common.utils.Constants
import com.cursosant.recomendedarch.common.utils.OnClickListener
import com.cursosant.recomendedarch.common.view.WineBaseFragment
import com.cursosant.recomendedarch.common.viewModel.ShareFragmentViewModel
import com.cursosant.recomendedarch.favouriteModule.viewModel.FavouriteViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class FavouriteFragment : WineBaseFragment(), OnClickListener {

    private val adapter: WineFavListAdapter by inject{ parametersOf(this) }
    private val sVm: ShareFragmentViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupRecyclerView()
        setupObservers()
    }

    private fun setupViewModel() {
        val vm: FavouriteViewModel by inject()
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, vm)
    }

    private fun setupObservers() {
        binding.viewModel?.let { vm ->
            vm.snackbarMsg.observe(viewLifecycleOwner) { resMsg ->
                resMsg?.let { showMsg(resMsg)  }
            }
            vm.wines.observe(viewLifecycleOwner) { wines ->
                adapter.submitList(wines)
            }
        }
        sVm.isDismiss.observe(viewLifecycleOwner){
            binding.viewModel?.getAllWines()
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = this@FavouriteFragment.adapter
        }
    }

    private fun showMsg(msgRes: Int) {
        Snackbar.make(binding.root, msgRes, Snackbar.LENGTH_SHORT).show()
    }

    /*
    * OnClickListener
    * */
    override fun onFavorite(wine: Wine) {
        (binding.viewModel as? FavouriteViewModel)?.updateFavorite(wine)
    }

    override fun onPause() {
        super.onPause()
        binding.viewModel?.onPause()
    }

    override fun onLongClick(wine: Wine) {

        val args = Bundle()
        args.putDouble(Constants.ARG_ID, wine.id)
        findNavController().navigate(R.id.navigation_update, args)
    }
}