package com.cursosant.recomendedarch.homeModule.view

import android.os.Bundle
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.cursosant.recomendedarch.common.entities.Wine
import com.cursosant.recomendedarch.common.utils.OnClickListener
import com.cursosant.recomendedarch.common.view.WineBaseFragment
import com.cursosant.recomendedarch.homeModule.viewModel.HomeViewModel
import com.cursosant.recomendedarch.R
import com.cursosant.recomendedarch.favouriteModule.viewModel.FavouriteViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class HomeFragment : WineBaseFragment(), OnClickListener {

    private val adapter: WineListAdapter by inject {  parametersOf(this) }
    //private lateinit var vm: HomeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupRecyclerView()
        setupObservers()
    }

    private fun setupViewModel() {
        /*vm = ViewModelProvider(this, HomeViewModelFactory(
            HomeRepository(
                HomeRoomDatabase(),
            HomeWineService()
            )
        ))[HomeViewModel::class.java]*/
        val vm: HomeViewModel by inject()
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, vm)

    }

    private fun setupObservers() {
        binding.viewModel?.let { vm ->
            vm.snackbarMsg.observe(viewLifecycleOwner) { resMsg ->
                resMsg?.let {
                    showMsg(resMsg)
                }
            }
            vm.wines.observe(viewLifecycleOwner) { wines ->
                adapter.submitList(wines)
            }
        }
    }



    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(3, RecyclerView.VERTICAL)
            adapter = this@HomeFragment.adapter
        }
    }

    private fun showMsg(msgRes: Int) {
        Snackbar.make(binding.root, msgRes, Snackbar.LENGTH_SHORT).show()
    }


    override fun onPause() {
        super.onPause()
        binding.viewModel?.let {
            it.onPause()
        }
    }

    /*
    * OnClickListener
    * */
    override fun onFavorite(wine: Wine) {
        return
    }

    override fun onLongClick(wine: Wine) {
        val items = resources.getStringArray(R.array.array_home_options)
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.home_dialog_title)
            .setItems(items) { _, index ->
                when(index) {
                    0 -> addToFavourites(wine)
                }
            }.show()
    }

    private fun addToFavourites(wine: Wine) {
        lifecycleScope.launch(Dispatchers.IO) {
            wine.isFavorite = true
            (binding.viewModel as? HomeViewModel)?.addWine(wine)
        }
    }
}