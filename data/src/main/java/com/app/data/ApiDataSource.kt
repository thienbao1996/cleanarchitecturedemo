package com.app.data

import com.app.data.api.model.detail.DetailResponseApi
import com.app.data.api.model.ProgramApi
import io.reactivex.rxjava3.core.Single

interface ApiDataSource {

    fun getPrograms(url: String): Single<List<ProgramApi>>

    fun getDetail(url: String): Single<DetailResponseApi>
}