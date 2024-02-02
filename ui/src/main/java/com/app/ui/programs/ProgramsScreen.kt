package com.app.ui.programs

import android.annotation.SuppressLint
import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.app.ui.common.AlertDialogCustom
import com.app.ui.main.Destination
import com.app.ui.programs.composable.ProgramItemComposable
import com.app.ui.programs.composable.ShimmerProgramItem
import com.app.ui.programs.model.NavigateTo
import org.koin.androidx.compose.koinViewModel
import kotlin.system.exitProcess


@SuppressLint("RestrictedApi")
@Composable
fun ProgramsScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val viewModel = koinViewModel<ProgramsViewModel>()
    val state = viewModel.uiData.observeAsState()
    val context = LocalContext.current
    val activity = (LocalContext.current as? Activity)
    val showDialog = remember {
        mutableStateOf(false)
    }

    state.value?.isLoading?.let {
        if (it) ShimmerProgramItem()
    }

    state.value?.data?.let { data ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            data.programs.forEach { uiModel ->
                item {
                    ProgramItemComposable(
                        id = uiModel.id,
                        title = uiModel.title,
                        subtitle = uiModel.subtitle,
                        urlImage = uiModel.urlImage,
                        urlLogoChannel = uiModel.urlLogoChannel,
                        onItemClicked = {
                            when (uiModel.navigateTo) {
                                is NavigateTo.DetailPage -> {
                                    navController.currentBackStackEntry?.savedStateHandle?.set(
                                        "navigateTo",
                                        uiModel.navigateTo
                                    )
                                    navController.navigate(
                                        Destination.Detail.route
                                    )
                                }

                                is NavigateTo.QuickTime -> {
                                    Toast.makeText(context, "Navigate To QuickTime", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        },
                    )
                }
            }
        }
    }

    state.value?.error?.let {
        if (it.isNotEmpty()) {
            showDialog.value = true
            AlertDialogCustom(
                showDialog,
                "Retry",
                onFirstAction = { viewModel.fetchData() },
                "Exit",
                onConfirmation = {
                    activity?.finish()
                    exitProcess(0)
                },
                dialogTitle = "Warning",
                dialogText = it
            )
        } else {
            showDialog.value = false
        }
    }
}