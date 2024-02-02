package com.app.data.api.retrofit

import com.app.data.api.model.detail.DetailResponseApi
import com.app.data.api.model.ProgramApi
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET
    fun getPrograms(
        @Url urlPage: String
    ): Single<List<ProgramApi>>


    @GET
    fun getDetail(
        @Url urlPage: String
    ): Single<DetailResponseApi>
}
