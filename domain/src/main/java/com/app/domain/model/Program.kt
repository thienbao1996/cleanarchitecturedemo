package com.app.domain.model

data class Program(
    val id: ProgramId,
    val title: String,
    val subtitle: String,
    val urlImage: String,
    val urlLogoChannel: String?,
    val navigateTo: NavigateTo
)

data class ProgramId(val id: String)