package com.cursosant.mviarch.promoModule.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cursosant.mviarch.commonModule.entries.Promo
import com.cursosant.mviarch.R
import com.cursosant.mviarch.databinding.ItemPromoBinding


class PromoListAdapter : ListAdapter<Promo, RecyclerView.ViewHolder>(PromoDiff()) {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_promo, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val promo = getItem(position)
        with((holder as ViewHolder).binding) {
            tvDescription.text = promo.description

            Glide.with(context)
                .load(promo.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(imgPromo)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemPromoBinding.bind(view)
    }

    private class PromoDiff : DiffUtil.ItemCallback<Promo>() {
        override fun areItemsTheSame(oldItem: Promo, newItem: Promo) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Promo, newItem: Promo) = oldItem == newItem
    }
}