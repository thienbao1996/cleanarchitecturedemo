package com.app.ui.common

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color

@Composable
fun AlertDialogSimple(
    show: MutableState<Boolean>,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String
) {
    if (show.value) {
        AlertDialog(
            title = {
                Text(text = dialogTitle)
            },
            text = {
                Text(text = dialogText)
            },
            onDismissRequest = {
                show.value = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        show.value = false
                        onConfirmation()
                    }
                ) {
                    Text("Confirm")
                }
            },
            backgroundColor = Color.Gray
        )
    }
}

@Composable
fun AlertDialogCustom(
    showDialog: MutableState<Boolean>,
    actionTitle: String,
    onFirstAction: () -> Unit,
    secondTitle: String,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String
) {

    if (showDialog.value) {
        AlertDialog(
            title = {
                Text(text = dialogTitle)
            },
            text = {
                Text(text = dialogText)
            },
            onDismissRequest = {
                showDialog.value = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog.value = false
                        onConfirmation()
                    }
                ) {
                    Text(secondTitle)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDialog.value = false
                        onFirstAction()
                    }
                ) {
                    Text(actionTitle)
                }
            },
            backgroundColor = Color.Gray
        )
    }
}

fun checkAppear(arr: Array<Int>) {
    val setNumber = mutableSetOf<Int>()
}