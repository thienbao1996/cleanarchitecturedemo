package com.app.ui.detail.mapper

import com.app.domain.model.detail.Detail
import com.app.ui.common.BaseUiMapper
import com.app.ui.detail.model.DetailUi

class DetailUiMapper : BaseUiMapper<Detail, DetailUi>() {
    override fun toUi(domain: Detail): DetailUi {
        return with(domain) {
            DetailUi(title, subtitle, URLImage, summary)
        }
    }
}