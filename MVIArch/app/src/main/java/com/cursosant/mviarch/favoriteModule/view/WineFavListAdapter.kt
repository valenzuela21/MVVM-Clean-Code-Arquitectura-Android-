package com.cursosant.mviarch.favoriteModule.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cursosant.mviarch.homeModule.view.WineListAdapter


class WineFavListAdapter : WineListAdapter() {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val wine = getItem(position)
        with((holder as ViewHolder).binding) {
            cbFavorite.apply {
                isChecked = wine.isFavorite
                visibility = View.VISIBLE
            }
        }
    }
}