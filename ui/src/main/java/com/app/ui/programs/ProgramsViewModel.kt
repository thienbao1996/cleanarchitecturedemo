package com.app.ui.programs

import android.util.Log
import com.app.domain.model.Result
import com.app.domain.usecase.GetProgramsUseCase
import com.app.ui.common.BaseViewModel
import com.app.ui.programs.mapper.ProgramsUiMapper
import com.app.ui.programs.model.ProgramUiState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class ProgramsViewModel(
    private val getProgramsUseCase: GetProgramsUseCase,
    private val programsUiMapper: ProgramsUiMapper
) : BaseViewModel<ProgramUiState>() {

    init {
        fetchData()
    }

    fun fetchData() {
        Log.e("TAG", "ProgramsViewModel - init")
        postUiData(ProgramUiState(isLoading = true))
        getProgramsUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy {  result ->
                when(result) {
                    is Result.Success -> {
                        val uiState = ProgramUiState(data = programsUiMapper.toUi(result.data))
                        postUiData(uiState)
                    }
                    is Result.Error -> {
                        val uiState = ProgramUiState(error = result.error.toString())
                        postUiData(uiState)
                    }
                }
            }
            .autoDispose()
    }
}