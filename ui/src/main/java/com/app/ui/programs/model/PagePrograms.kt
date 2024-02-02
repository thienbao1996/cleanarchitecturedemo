package com.app.ui.programs.model

import com.app.ui.common.UiId

data class PageProgramsUi(
    val programs: List<ProgramUi>
)

data class ProgramUi(
    val id: UiId,
    val title: String,
    val subtitle: String,
    val urlImage: String,
    val urlLogoChannel: String?,
    val navigateTo: NavigateTo
)

data class ProgramUiState (
    val isLoading: Boolean = false,
    val data: PageProgramsUi? = null,
    val error: String = ""
)
