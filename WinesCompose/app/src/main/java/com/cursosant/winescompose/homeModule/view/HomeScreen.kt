package com.cursosant.winescompose.homeModule.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.cursosant.winescompose.R
import com.cursosant.winescompose.common.components.OneOptionDialog
import com.cursosant.winescompose.common.entities.Wine
import com.cursosant.winescompose.homeModule.viewModel.HomeViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun WineList(vm: HomeViewModel = koinViewModel(), onSnackbarMsg: (String) -> Unit) {
    val showAddFavDialog = remember { mutableStateOf(false) }
    val wineSelected = remember { mutableStateOf<Wine?>(null) }

    val snackbarMsg: Int? = vm.snackbarMsg.observeAsState().value//null
    val wines: List<Wine>? = vm.wines.observeAsState().value//listOf(Wine("Castilla", "Liria", Rating("4.7", "Good"), "Spain", "", 1.0))

    wines?.let {
        LazyVerticalStaggeredGrid(columns = StaggeredGridCells
            .Adaptive(dimensionResource(id = R.dimen.home_column_min_size)),
            verticalItemSpacing = dimensionResource(id = R.dimen.common_space_min),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.common_space_min)),
            modifier = Modifier.padding(dimensionResource(id = R.dimen.common_space_min))) {
            items( wines.size ) {
                val wine = wines[it]
                ItemWine(wine = wine, modifier = Modifier.clickable {
                    showAddFavDialog.value = true
                    wineSelected.value = wine
                })
            }
        }
    }

    if (showAddFavDialog.value && wineSelected.value != null) {
        wineSelected.value?.let { wine ->
            OneOptionDialog(resTitle = R.string.home_dialog_title,
                resOption = R.string.home_dialog_option_add,
                onDismissRequest = {
                    showAddFavDialog.value = false
                    wineSelected.value = null },
                onClick = {
                    wine.isFavorite = true
                    vm.addWine(wine)
                })
        }
    }

    if (snackbarMsg != null) {
        onSnackbarMsg(stringResource(id = snackbarMsg))
    }
}
