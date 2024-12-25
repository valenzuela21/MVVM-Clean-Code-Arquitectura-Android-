package com.cursosant.recomendedarch.homeModule.view

import androidx.recyclerview.widget.DiffUtil
import com.cursosant.recomendedarch.common.entities.Wine

class WineDiff : DiffUtil.ItemCallback<Wine>() {
    override fun areItemsTheSame(oldItem: Wine, newItem: Wine) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Wine, newItem: Wine) = oldItem == newItem
}