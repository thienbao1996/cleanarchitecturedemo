package com.app.data.mapper

import com.app.data.api.model.detail.DetailApi
import com.app.data.api.model.detail.Informations
import com.app.domain.model.detail.Detail
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DetailMapperTest {

    private lateinit var detailMapper: DetailMapper
    private lateinit var detailApi: DetailApi
    private lateinit var detailDomainExpected: Detail

    @Before
    fun setUp() {
        detailApi = DetailApi(
            informations = Informations(
                URLImage = "https://duckduckgo.com/?q=efficitur",
                URLLogoChannel = "http://www.bing.com/search?q=tractatos",
                URLMedias = "https://search.yahoo.com/search?p=porta",
                consumptionPlatform = "erat",
                contentID = "tempor",
                displayPersoButtons = false,
                editorialTitle = "tellus",
                fbkWatchTag = "vituperatoribus",
                idRevision = "parturient",
                sharingURL = "https://search.yahoo.com/search?p=class",
                subtitle = "gloriatur",
                summary = "deseruisse",
                thirdTitle = "sodales",
                title = "verterem",
                type = "ludus"
            )
        )
        detailDomainExpected = Detail(
            title = "verterem",
            subtitle = "gloriatur",
            URLImage = "https://duckduckgo.com/?q=efficitur",
            summary = "deseruisse"
        )
        detailMapper = DetailMapper()
    }

    @Test
    fun ` verify toDomain handles correct response`() {
        val detailResult = detailMapper.toDomain(detailApi)
        assertEquals(detailDomainExpected.title, detailResult.title)
        assertEquals(detailDomainExpected.subtitle, detailResult.subtitle)
        assertEquals(detailDomainExpected.URLImage, detailResult.URLImage)
        assertEquals(detailDomainExpected.summary, detailResult.summary)
    }
}