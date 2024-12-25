package com.cursosant.winescompose.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import com.cursosant.winescompose.R


@Composable
fun OneOptionDialog(resTitle: Int, resOption: Int, onDismissRequest: () -> Unit,
                    onClick: () -> Unit) {
    Dialog(
        onDismissRequest = { onDismissRequest() }) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.login_img_size))
            .padding(bottom = dimensionResource(id = R.dimen.common_space_default)),
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.common_space_default))
        ) {
            Text(text = stringResource(id = resTitle),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.common_space_default)),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge)
            Text(text = stringResource(id = resOption),
                Modifier
                    .clickable {
                        onClick()
                        onDismissRequest()
                    }
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.common_space_default)),
                style = MaterialTheme.typography.bodyLarge)
        }
    }
}