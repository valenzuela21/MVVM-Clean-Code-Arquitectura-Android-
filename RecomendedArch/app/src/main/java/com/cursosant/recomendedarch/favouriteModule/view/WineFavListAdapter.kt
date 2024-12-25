package com.cursosant.recomendedarch.favouriteModule.view

import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.cursosant.recomendedarch.common.utils.OnClickListener
import com.cursosant.recomendedarch.homeModule.view.WineDiff
import com.cursosant.recomendedarch.homeModule.view.WineListAdapter

class WineFavListAdapter(listener: OnClickListener, diff: WineDiff): WineListAdapter(listener, diff) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        (holder as ViewHolder).binding?.setVariable(BR.isFavModule, true)
    }
}