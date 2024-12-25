package com.cursosant.recomendedarch.promoModule.view.adapaters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cursosant.recomendedarch.common.entities.Promo
import com.cursosant.recomendedarch.R
import com.cursosant.recomendedarch.databinding.ItemPromoBinding

class PromoListAdapter(diff: PromoDiff) : ListAdapter<Promo, RecyclerView.ViewHolder>(diff) {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_promo, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val promo = getItem(position)
        with((holder as ViewHolder)) {
            binding?.setVariable(BR.promo, promo)
            binding?.executePendingBindings()
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = DataBindingUtil.bind<ItemPromoBinding>(view)
    }

}