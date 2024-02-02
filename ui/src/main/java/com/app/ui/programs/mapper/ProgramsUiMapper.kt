package com.app.ui.programs.mapper

import com.app.domain.model.Program
import com.app.domain.model.ProgramId
import com.app.ui.common.BaseUiMapper
import com.app.ui.common.UiId
import com.app.ui.programs.model.NavigateTo
import com.app.ui.programs.model.PageProgramsUi
import com.app.ui.programs.model.ProgramUi

class ProgramsUiMapper : BaseUiMapper<List<Program>, PageProgramsUi>() {

    override fun toUi(domain: List<Program>): PageProgramsUi {
        val programsUi = domain.map { program ->
            ProgramUi(
                id = program.id.toUiId(),
                title = program.title,
                subtitle = program.subtitle,
                urlImage = program.urlImage,
                urlLogoChannel = program.urlLogoChannel,
                navigateTo = toNavigateUi(program.navigateTo)
            )
        }
        return PageProgramsUi(
            programs = programsUi
        )
    }

    private fun ProgramId.toUiId(): UiId = UiId(id)

    private fun toNavigateUi(navigateTo: com.app.domain.model.NavigateTo): NavigateTo {
        return when(navigateTo) {
            is com.app.domain.model.NavigateTo.QuickTime -> NavigateTo.QuickTime(navigateTo.urlMedias)
            is com.app.domain.model.NavigateTo.DetailPage -> NavigateTo.DetailPage(navigateTo.urlPage)
        }
    }
}