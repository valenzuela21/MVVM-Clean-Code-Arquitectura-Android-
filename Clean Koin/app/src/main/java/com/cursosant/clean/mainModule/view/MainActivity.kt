package com.cursosant.clean.mainModule.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.cursosant.clean.databinding.ActivityMainBinding
import com.cursosant.clean.common.SportEvent
import com.cursosant.clean.mainModule.model.DataSourceImpl
import com.cursosant.clean.mainModule.model.MainRepositoryImpl
import com.cursosant.clean.mainModule.presenter.MainPresenter
import com.cursosant.clean.mainModule.presenter.MainPresenterImpl
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class MainActivity : AppCompatActivity(), OnClickListener, MainView {

    private lateinit var binding: ActivityMainBinding
    private val adapter: ResultAdapter  by inject { parametersOf(this) }
    private val presenter: MainPresenter by inject { parametersOf( this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //presenter = MainPresenterImpl(this, MainRepositoryImpl(DataSourceImpl()))
        presenter.onCreate()

        setupAdapter()
        setupRecyclerView()
        setupSwipeRefresh()
        setupClicks()
    }


    private fun setupAdapter() {
        //adapter = ResultAdapter(this)
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    private fun setupSwipeRefresh(){
        binding.srlResults.setOnRefreshListener {
            lifecycleScope.launch {
                presenter.refresh()
            }
        }
    }

    private fun setupClicks(){
        binding.btnAd.run {
            setOnClickListener{
                lifecycleScope.launch {
                  presenter.registerAd()
                }
            }
            setOnLongClickListener { view ->
                lifecycleScope.launch {
                    presenter.closeAd()
                }
                true
            }
        }
    }



    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            presenter.getEvents()
        }
    }

    override fun onClick(result: SportEvent.ResultSuccess) {
        lifecycleScope.launch {
            presenter.saveResult(result)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    /**
     * View Layer
     * **/

    override fun add(event: SportEvent.ResultSuccess){
        adapter.add(event)
    }

    override fun clearAdapter(){
        adapter.clear()
    }

    override suspend fun showAdUI(isVisible: Boolean)  = withContext(Dispatchers.Main){
        binding.btnAd.visibility = if(isVisible) View.VISIBLE else View.GONE
    }

    override fun showProgress(isVisible: Boolean){
        binding.srlResults.isRefreshing = isVisible
    }

    override suspend fun showToast(msg: String) = withContext(Dispatchers.Main){
        Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()
    }

    override fun showSnackBar(msg: String){
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_LONG).show()
    }

}