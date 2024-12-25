package com.cursosant.eventbuspatt

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.cursosant.eventbuspatt.adapters.OnClickListener
import com.cursosant.eventbuspatt.databinding.ActivityMainBinding
import com.cursosant.eventbuspatt.services.SportService
import com.cursosant.eventbuspatt.adapters.ResultAdapter
import com.cursosant.eventbuspatt.dataAccess.getAdEventsInRealTime
import com.cursosant.eventbuspatt.dataAccess.getResultEventsInRealTime
import com.cursosant.eventbuspatt.dataAccess.someTime
import com.cursosant.eventbuspatt.eventBus.EventBus
import com.cursosant.eventbuspatt.eventBus.SportEvent
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAdapter()
        setupRecyclerView()
        setupSwipeRefresh()
        setupClicks()
        setupSubscribers()

        getResultEventsInRealTime().forEach{
            if(it is  SportEvent.ResultSuccess){
                adapter.add(it)
            }
        }
    }

    private fun setupAdapter() {
        adapter = ResultAdapter(this)
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
            adapter.clear()
            getEvents()
            binding.btnAd.visibility = View.VISIBLE
        }
    }

    private fun setupSubscribers(){
        lifecycleScope.launch {
            SportService.instance().setupSubscribers(this)
            EventBus.instance().subscribe<SportEvent> { event ->
                binding.srlResults.isRefreshing = false
                when(event) {
                    is SportEvent.ResultSuccess -> adapter.add(event)
                    is SportEvent.ResultError ->  Snackbar.make(binding.root, "Code: ${event.code}, Message: ${event.msg}", Snackbar.LENGTH_LONG).show()
                    is SportEvent.AdEvent -> Toast.makeText(this@MainActivity, "Add Click, send data to server...", Toast.LENGTH_SHORT).show()
                    is SportEvent.ClosedAdEvent -> binding.btnAd.visibility = View.GONE
                    is SportEvent.SaveEvent -> Toast.makeText(this@MainActivity, "Guardado", Toast.LENGTH_LONG).show()
                    else -> {}
                }
            }
        }
    }


    private fun getEvents(){
        lifecycleScope.launch {
            val events = getResultEventsInRealTime()
            events.forEach(){ event ->
                delay(someTime())
                EventBus.instance().publish(event)
            }
        }
    }

    private fun setupClicks(){
        binding.btnAd.run {
            setOnClickListener{
              lifecycleScope.launch {
                  binding.srlResults.isRefreshing = true
                  val events = getAdEventsInRealTime()
                  EventBus.instance().publish(events.first())
              }
            }
            setOnLongClickListener { view ->
                lifecycleScope.launch {
                    binding.srlResults.isRefreshing = true
                    EventBus.instance().publish(SportEvent.ClosedAdEvent)
                }
                true
            }
        }
    }

    override fun onStart() {
        super.onStart()
        binding.srlResults.isRefreshing = true
        getEvents()
    }


    override fun onClick(result: SportEvent.ResultSuccess) {
        binding.srlResults.isRefreshing = true
        lifecycleScope.launch {
            //EventBus.instance().publish(SportEvent.SaveEvent)
            SportService.instance().saveResult(result)
        }
    }


}