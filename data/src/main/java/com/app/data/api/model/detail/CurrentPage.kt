package com.app.data.api.model.detail

data class CurrentPage(
    @Transient var BOLayoutName: String = "",
    @Transient var BOName: String = "",
    @Transient var displayName: String = "",
    @Transient var displayTemplate: String = "",
    @Transient var path: String = ""
)