package com.app.data.mapper

import com.app.data.api.model.OnClickApi
import com.app.domain.model.NavigateTo

class OnClickMapper : BaseDomainMapper<OnClickApi, NavigateTo>() {

    override val tag: String = "OnClickMapper"

    override fun toDomain(api: OnClickApi): NavigateTo {
        return when (api.displayTemplate) {
            DISPLAY_TEMPLATE_QUICKTIME -> api.toQuicktime()
            DISPLAY_TEMPLATE_DETAIL_PAGE -> api.toDetailPage()
            else -> throw NoSuchElementException(
                "This template is not handled : ${api.displayTemplate}"
            )
        }
    }

    private fun OnClickApi.toQuicktime(): NavigateTo {
        return NavigateTo.QuickTime(
            urlMedias = this.URLMedias!!
        )
    }

    private fun OnClickApi.toDetailPage(): NavigateTo {
        return NavigateTo.DetailPage(
            urlPage = this.URLPage.consolidateValue("URLPage")
        )
    }

    companion object {
        private const val DISPLAY_TEMPLATE_QUICKTIME = "quicktime"
        private const val DISPLAY_TEMPLATE_DETAIL_PAGE = "detailPage"
    }

}