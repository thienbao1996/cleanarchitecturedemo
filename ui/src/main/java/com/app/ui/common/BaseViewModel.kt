package com.app.ui.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseViewModel<UI_MODEL: Any> : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _uiData = MutableLiveData<UI_MODEL>()
    val uiData: LiveData<UI_MODEL>
        get() = _uiData

    protected fun postUiData(uiModel: UI_MODEL) {
        _uiData.value = uiModel
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    protected fun Disposable.autoDispose() {
        compositeDisposable.add(this)
    }
}