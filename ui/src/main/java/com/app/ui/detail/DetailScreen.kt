package com.app.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.ui.common.AlertDialogSimple
import com.app.ui.detail.composable.DetailItemScreen
import com.app.ui.detail.composable.ShimmerDetailItem
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun DetailScreen(
    url: String,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val viewModel = koinViewModel<DetailViewModel>(parameters = { parametersOf(url) })
    val state = viewModel.uiData.observeAsState()
    val showError = remember {
        mutableStateOf(false)
    }

    state.value?.error?.let {
       if (it.isNotEmpty()) {
           showError.value = true
           AlertDialogSimple(
               showError,
               onConfirmation = { navController.popBackStack() },
               dialogTitle = "Warning",
               dialogText = it
           )
       } else {
           showError.value = false
       }
   }

    state.value?.isLoading?.let {
        if (it) {
            ShimmerDetailItem()
        }
    }

    state.value?.detailUi?.let {
        Box(modifier = modifier.fillMaxSize()) {
            Column(
                modifier = modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                state.value?.detailUi?.let {
                    DetailItemScreen(
                        title = it.title,
                        subtitle = it.subtitle,
                        urlImage = it.URLImage,
                        summary = it.summary
                    )
                }
            }
        }
    }
}