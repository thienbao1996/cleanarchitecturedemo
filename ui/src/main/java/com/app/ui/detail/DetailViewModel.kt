package com.app.ui.detail

import android.util.Log
import com.app.domain.model.Result
import com.app.domain.model.detail.Detail
import com.app.domain.usecase.GetDetailUseCase
import com.app.ui.common.BaseViewModel
import com.app.ui.detail.mapper.DetailUiMapper
import com.app.ui.detail.model.PageDetailState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class DetailViewModel(
    url: String,
    getDetailUseCase: GetDetailUseCase,
    detailUiMapper: DetailUiMapper
) : BaseViewModel<PageDetailState>() {

    init {
        postUiData(PageDetailState(isLoading = true))
        getDetailUseCase(url)
            .delay(2000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy { result ->
                when(result) {
                    is Result.Success -> {
                        val uiState = PageDetailState(detailUi = detailUiMapper.toUi(result.data))
                        postUiData(uiState)
                    }
                    is Result.Error -> {
                        val uiState = PageDetailState(error = result.error.toString())
                        postUiData(uiState)
                    }
                }
            }
            .autoDispose()
    }
}