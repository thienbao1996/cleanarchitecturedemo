package com.app.data.api

import com.app.data.ApiDataSource
import com.app.data.api.model.detail.DetailResponseApi
import com.app.data.api.model.ProgramApi
import com.app.data.api.retrofit.ApiService
import io.reactivex.rxjava3.core.Single

class ApiDataSourceImpl(
    private val apiService: ApiService
) : ApiDataSource {

    override fun getPrograms(url: String): Single<List<ProgramApi>> {
        return apiService.getPrograms(url)
    }

    override fun getDetail(url: String): Single<DetailResponseApi> {
        return apiService.getDetail(url)
    }
}