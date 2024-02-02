package com.app.data

import android.util.Log
import com.app.data.mapper.DetailMapper
import com.app.data.mapper.ProgramMapper
import com.app.domain.Repository
import com.app.domain.model.Program
import com.app.domain.model.detail.Detail
import io.reactivex.rxjava3.core.Single

class RepositoryImpl(
    private val apiDataSource: ApiDataSource,
    private val programMapper: ProgramMapper,
    private val detailMapper: DetailMapper
) : Repository {

    override fun getPrograms(url: String): Single<List<Program>> {
        return apiDataSource.getPrograms(url)
            .map { programsApi ->
                programsApi.map { programApi ->
                    Log.e("TAG", "RepositoryImpl" )
                    programMapper.toDomain(programApi)
                }
            }
    }

    override fun getDetail(url: String): Single<Detail> {
        return apiDataSource.getDetail(url).map {
            Log.e("TAG", "RepositoryImpl" )
            detailMapper.toDomain(it.detail)
        }
    }
}