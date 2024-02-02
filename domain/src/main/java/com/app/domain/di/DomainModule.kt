package com.app.domain.di

import com.app.domain.usecase.GetDetailUseCase
import com.app.domain.usecase.GetDetailUseCaseImpl
import com.app.domain.usecase.GetProgramsUseCase
import com.app.domain.usecase.GetProgramsUseCaseImpl
import org.koin.dsl.module

val domainUseCaseModule = module {
    factory<GetProgramsUseCase> {
        GetProgramsUseCaseImpl(
            repository = get(),
            errorHandler = get()
        )
    }
    factory<GetDetailUseCase> {
        GetDetailUseCaseImpl(
            repository = get(),
            errorHandler = get()
        )
    }
}

val koinDomainModules = listOf(
    domainUseCaseModule
)