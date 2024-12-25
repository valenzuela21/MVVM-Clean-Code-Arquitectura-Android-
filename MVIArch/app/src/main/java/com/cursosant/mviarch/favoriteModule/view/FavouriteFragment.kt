package com.cursosant.mviarch.favoriteModule.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.cursosant.mviarch.commonModule.utils.Constants
import com.cursosant.mviarch.commonModule.utils.OnClickListener
import com.cursosant.mviarch.commonModule.entries.Wine
import com.cursosant.mviarch.updateModule.views.UpdateDialogFragment
import com.cursosant.mviarch.commonModule.view.WineBaseFragment
import com.cursosant.mviarch.favoriteModule.FavouriteViewModel
import com.cursosant.mviarch.favoriteModule.FavouriteViewModelFactory
import com.cursosant.mviarch.favoriteModule.intent.FavouriteIntent
import com.cursosant.mviarch.favoriteModule.model.FavoriteRepository
import com.cursosant.mviarch.favoriteModule.model.FavouriteState
import com.cursosant.mviarch.favoriteModule.model.RoomDatabase
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FavouriteFragment : WineBaseFragment(), OnClickListener {

    private lateinit var adapter: WineFavListAdapter
    private lateinit var vm: FavouriteViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupAdapter()
        setupRecyclerView()
        setupSwipeRefresh()
        setupObservers()
    }

    private fun setupViewModel() {
        vm = ViewModelProvider(
            this,
            FavouriteViewModelFactory(FavoriteRepository(RoomDatabase()))
        )[FavouriteViewModel::class.java]
    }


    private fun setupAdapter() {
        adapter = WineFavListAdapter()
        adapter.setOnClickListener(this)
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = this@FavouriteFragment.adapter
        }
    }

    private fun setupSwipeRefresh() {
        binding.srlResults.setOnRefreshListener {
            getWines()
        }
    }

    private fun getWines() {
      lifecycleScope.launch(Dispatchers.IO){
          vm.channel.send(FavouriteIntent.RequestWines)
      }
    }

    private fun setupObservers() {
        lifecycleScope.launch{
            vm.state.collect { state ->
                when (state) {
                    is FavouriteState.Init -> {}
                    is FavouriteState.ShowProgress -> showProgress(true)
                    is FavouriteState.HideProgress -> showProgress(false)
                    is FavouriteState.RequestWinesSuccess -> {
                        adapter.submitList(state.list)
                        showNoDataView(state.list.isEmpty())
                        showRecyclerView(state.list.isNotEmpty())
                    }
                    is FavouriteState.AddWineSuccess -> showMsg(state.msgRes)
                    is FavouriteState.DeleteWineSuccess -> showMsg(state.msgRes)
                    is FavouriteState.Fail -> showMsg(state.msgRes)
                }
            }

        }
    }

    private fun showMsg(msgRes: Int) {
        Snackbar.make(binding.root, msgRes, Snackbar.LENGTH_SHORT).show()
    }

    private fun showProgress(isVisible: Boolean) {
        binding.srlResults.isRefreshing = isVisible
    }

    private fun showNoDataView(isVisible: Boolean) {
        binding.tvNoData.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    private fun showRecyclerView(isVisible: Boolean) {
        binding.recyclerView.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    override fun onResume() {
        super.onResume()
        getWines()
    }

    /*
    * OnClickListener
    * */
    override fun onFavorite(wine: Wine) {
        wine.isFavorite = !wine.isFavorite
        lifecycleScope.launch(Dispatchers.IO) {
            if (wine.isFavorite) {
                vm.channel.send(FavouriteIntent.AddWine(wine))
            } else {
                vm.channel.send(FavouriteIntent.DeleteWine(wine))
            }
        }
    }

    override fun onLongClick(wine: Wine) {
        val fragmentManager = childFragmentManager
        val fragment = UpdateDialogFragment()
        val args = Bundle()
        args.putDouble(Constants.ARG_ID, wine.id)
        fragment.arguments = args
        fragment.show(fragmentManager, UpdateDialogFragment::class.java.simpleName)
        fragment.setOnUpdateListener {
            binding.srlResults.isRefreshing = true
            getWines()
        }
    }
}