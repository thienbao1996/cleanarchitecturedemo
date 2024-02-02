package com.app.ui.detail.model

data class DetailUi(
    val title: String = "",
    val subtitle: String = "",
    val URLImage: String = "",
    val summary: String = ""
)

data class PageDetailState (
    val isLoading: Boolean = false,
    val detailUi: DetailUi? = null,
    val error: String = ""
)