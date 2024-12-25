package com.cursosant.recomendedarch.homeModule.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cursosant.recomendedarch.R
import com.cursosant.recomendedarch.common.utils.OnClickListener
import com.cursosant.recomendedarch.common.entities.Wine
import com.cursosant.recomendedarch.databinding.ItemWineBinding


open class WineListAdapter(private val listener: OnClickListener,diff: WineDiff):
    ListAdapter<Wine, RecyclerView.ViewHolder>(diff) {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_wine, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val wine = getItem(position)
        (holder as ViewHolder).run {
            setListener(wine)
            binding?.setVariable(BR.wine, wine)
            binding?.executePendingBindings()
        }
    }



    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = DataBindingUtil.bind<ItemWineBinding>(view)

        fun setListener(wine: Wine) {
            binding?.root?.setOnLongClickListener {
                listener.onLongClick(wine)
                true
            }
            binding?.cbFavorite?.setOnClickListener {
                listener.onFavorite(wine)
            }
        }
    }


}