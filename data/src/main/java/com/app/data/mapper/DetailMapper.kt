package com.app.data.mapper

import com.app.data.api.model.detail.DetailApi
import com.app.domain.model.detail.Detail

class DetailMapper : BaseDomainMapper<DetailApi, Detail>() {
    override val tag: String
        get() = "DetailMapper"

    override fun toDomain(api: DetailApi): Detail {
        return Detail(
            title = api.informations.title,
            subtitle = api.informations.subtitle,
            URLImage = api.informations.URLImage,
            summary = api.informations.summary
        )
    }

}