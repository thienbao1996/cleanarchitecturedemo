package com.app.data.api.model.detail

data class DetailResponseApi(
    @Transient val code: Int = 200,
    @Transient val currentPage: CurrentPage = CurrentPage(),
    @Transient val text: String = "",
    @Transient val title: String = "",
    @Transient val tracking: Tracking = Tracking(),
    val detail: DetailApi = DetailApi()
)