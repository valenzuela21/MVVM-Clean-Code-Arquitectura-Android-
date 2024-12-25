package com.cursosant.mvvmarch.favoriteModule.view

import androidx.recyclerview.widget.RecyclerView
import com.cursosant.mvvmarch.BR
import com.cursosant.mvvmarch.homeModule.view.WineListAdapter

class WineFavListAdapter : WineListAdapter() {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        (holder as ViewHolder).binding?.setVariable(BR.isFavModule, true)
    }
}