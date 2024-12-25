package com.cursosant.winescompose.mainModule.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.cursosant.winescompose.homeModule.view.WineList
import com.cursosant.winescompose.ui.theme.WinesComposeTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WinesComposeTheme {
                val snackbarHostState = remember { SnackbarHostState()}
                val coroutineScope = rememberCoroutineScope()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background,
                    content = {
                   WineList(onSnackbarMsg = { resMsg ->
                       coroutineScope.launch { snackbarHostState.showSnackbar(message = resMsg) }
                   })
                }, snackbarHost = { SnackbarHost( hostState = snackbarHostState) } )
            }
        }
    }
}