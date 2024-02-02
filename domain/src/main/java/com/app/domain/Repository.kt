package com.app.domain

import com.app.domain.model.Program
import com.app.domain.model.detail.Detail
import io.reactivex.rxjava3.core.Single

interface Repository {

    fun getPrograms(url: String): Single<List<Program>>

    fun getDetail(url: String): Single<Detail>
}