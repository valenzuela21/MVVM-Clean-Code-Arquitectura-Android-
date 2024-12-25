package com.cursosant.recomendedarch.promoModule.view.adapaters

import androidx.recyclerview.widget.DiffUtil
import com.cursosant.recomendedarch.common.entities.Promo

class PromoDiff: DiffUtil.ItemCallback<Promo>() {
    override fun areItemsTheSame(oldItem: Promo, newItem: Promo) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Promo, newItem: Promo) = oldItem == newItem
}