package com.app.data.mapper

import com.app.data.api.model.ProgramApi
import com.app.domain.model.Program
import com.app.domain.model.ProgramId

class ProgramMapper(
    private val onClickMapper: OnClickMapper
) : BaseDomainMapper<ProgramApi, Program>() {

    override val tag: String = "ProgramMapper"

    override fun toDomain(api: ProgramApi): Program {
        val navigateTo = onClickMapper.toDomain(api.onClick.consolidateValue("onClick"))
        return Program(
            id = ProgramId(api.contentID.consolidateValue("contentID")),
            title = api.title.consolidateValue("title"),
            subtitle = api.subtitle.consolidateValue("subtitle"),
            urlImage = api.URLImage.consolidateValue("URLImage"),
            urlLogoChannel = api.URLLogoChannel,
            navigateTo = navigateTo
        )
    }

}