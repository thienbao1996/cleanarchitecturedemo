package com.app.data.di

import com.app.data.ApiDataSource
import com.app.data.ErrorHandlerImpl
import com.app.data.RepositoryImpl
import com.app.data.api.ApiDataSourceImpl
import com.app.data.api.retrofit.ApiRetrofitFactory
import com.app.data.mapper.DetailMapper
import com.app.data.mapper.OnClickMapper
import com.app.data.mapper.ProgramMapper
import com.app.domain.ErrorHandler
import com.app.domain.Repository
import org.koin.dsl.module


val apiRetrofitModule = module {
    single {
        ApiRetrofitFactory().buildApiService(
            ApiRetrofitFactory().buildApiRetrofit()
        )
    }
}

val apiDataSourceModule = module {
    single<ApiDataSource> {
        ApiDataSourceImpl(
            apiService = get()
        )
    }
}

val apiMapperModule = module {
    single {
        OnClickMapper()
    }
    single {
        ProgramMapper(onClickMapper = get())
    }
    single {
        DetailMapper()
    }
}

val errorHandlerModule = module {
    single<ErrorHandler> {
        ErrorHandlerImpl()
    }
}

val repositoryModule = module {
    single<Repository> {
        RepositoryImpl(
            apiDataSource = get(),
            programMapper = get(),
            detailMapper = get()
        )
    }
}

val koinDataSourceModules = listOf(
    apiRetrofitModule,
    apiDataSourceModule,
    apiMapperModule,
    repositoryModule,
    errorHandlerModule
)