package com.cursosant.recomendedarch.favouriteModule.viewModel
import com.cursosant.recomendedarch.common.entities.Wine
import com.cursosant.recomendedarch.common.viewModel.BaseWineViewModel
import com.cursosant.recomendedarch.favouriteModule.model.FavouriteRepository

class FavouriteViewModel(private val repository: FavouriteRepository): BaseWineViewModel() {
    init {
        getAllWines()
    }

    override fun getAllWines() {
        executeAction {
          repository.getAllWines { wines ->
              setWines(wines)
          }
        }
    }

    fun updateFavorite(wine: Wine){
        executeAction {
            repository.updateFavourite(wine){ resMsg ->
                setSnackbarMsg(resMsg)
            }
        }
    }

    override fun onPause() = clearValues()

}