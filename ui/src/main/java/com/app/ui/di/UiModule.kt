package com.app.ui.di

import com.app.ui.detail.DetailViewModel
import com.app.ui.detail.mapper.DetailUiMapper
import com.app.ui.programs.ProgramsViewModel
import com.app.ui.programs.mapper.ProgramsUiMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiProgramsViewModelModule = module {
    single {
        ProgramsUiMapper()
    }
    single {
        DetailUiMapper()
    }
    viewModel {
        ProgramsViewModel(
            getProgramsUseCase = get(),
            programsUiMapper = get()
        )
    }
    viewModel {parameters ->
        DetailViewModel(
            url = parameters.get(),
            getDetailUseCase = get(),
            detailUiMapper = get()
        )
    }
}

val koinUiModules = listOf(
    uiProgramsViewModelModule
)
