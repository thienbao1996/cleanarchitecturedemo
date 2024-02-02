package com.app.data

import com.app.data.api.model.OnClickApi
import com.app.data.api.model.ProgramApi
import com.app.data.api.model.detail.CurrentPage
import com.app.data.api.model.detail.DetailApi
import com.app.data.api.model.detail.DetailResponseApi
import com.app.data.api.model.detail.Informations
import com.app.data.api.model.detail.Omniture
import com.app.data.api.model.detail.Tracking
import com.app.data.mapper.DetailMapper
import com.app.data.mapper.OnClickMapper
import com.app.data.mapper.ProgramMapper
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import org.junit.Before
import org.junit.Test
import kotlin.test.assertNotNull

class RepositoryImplTest {

    private lateinit var apiDataSource: ApiDataSource
    private lateinit var onClickMapper: OnClickMapper
    private lateinit var programMapper: ProgramMapper
    private lateinit var detailMapper: DetailMapper
    private lateinit var repositoryImpl: RepositoryImpl

    @Before
    fun setUp() {
        val listProgramApi = listOf(
            ProgramApi(
                type = "quicktime",
                contentID = "1501162",
                title = "Best of - Part 1",
                subtitle = "TPMP - 12/07/2018",
                URLImage = "https://static.canal-plus.net/exoplayer/api/images/1501162.jpg",
                URLLogoChannel = "https://static.canal-plus.net/exoplayer/api/images/1501162.jpg",
                onClick = OnClickApi(
                    displayTemplate = "quicktime",
                    displayName = "Best of - Part 1",
                    URLPage = "http://hodor.canalplus.pro/api/v1/mycanal/detail/34dd47d57c659b511e83c520079df1a1/pfv/1501162.json?id=1501162&params[type]=pfv&cache=780000",
                    URLMedias = "https://static.canal-plus.net/exoplayer/api/mediaUrls/1501162.json"
                )
            )
        )
        val detailResponseApi = DetailResponseApi(
            code = 5867, currentPage = CurrentPage(
                BOLayoutName = "Young Hernandez",
                BOName = "Rachelle Bartlett",
                displayName = "Lemuel Burton",
                displayTemplate = "dignissim",
                path = "scelerisque"
            ), text = "eleifend", title = "voluptatibus", tracking = Tracking(
                omniture = Omniture(
                    channel = "nam",
                    eVar14 = "platea",
                    pageName = "Juana Fuller",
                    pageType = "magna",
                    prop10 = "prompta",
                    prop11 = "causae",
                    prop12 = "propriae",
                    prop4 = "montes",
                    prop5 = "referrentur"
                )
            ), detail = DetailApi(
                informations = Informations(
                    URLImage = "https://duckduckgo.com/?q=tation",
                    URLLogoChannel = "https://www.google.com/#q=fastidii",
                    URLMedias = "https://search.yahoo.com/search?p=doctus",
                    consumptionPlatform = "molestie",
                    contentID = "vituperatoribus",
                    displayPersoButtons = false,
                    editorialTitle = "electram",
                    fbkWatchTag = "nobis",
                    idRevision = "mea",
                    sharingURL = "https://duckduckgo.com/?q=convenire",
                    subtitle = "tale",
                    summary = "nisi",
                    thirdTitle = "duis",
                    title = "curabitur",
                    type = "dictas"
                )
            )
        )

        apiDataSource = mockk<ApiDataSource>(
            name = null,
            relaxed = false,
            relaxUnitFun = false,
            block = {})
        every { apiDataSource.getPrograms("") } returns Single.just(listProgramApi)
        every { apiDataSource.getDetail("") } returns Single.just(detailResponseApi)

        onClickMapper = OnClickMapper()
        programMapper = ProgramMapper(onClickMapper)
        detailMapper = DetailMapper()
        repositoryImpl = RepositoryImpl(
            apiDataSource = apiDataSource,
            programMapper = programMapper,
            detailMapper = detailMapper
        )
    }

    @Test
    fun `verify getPrograms handles correct response`() {
        val result = repositoryImpl.getPrograms("").subscribeBy (
            onError = {

            },
            onSuccess = {
                assert(it.isNotEmpty())
            }
        )
        result.dispose()
    }

    @Test
    fun `verify getDetail handles correct response`() {
        val result = repositoryImpl.getDetail("").subscribeBy(
            onError = {

            },
            onSuccess = {
                assertNotNull(it)
            }
        )
        result.dispose()
    }
}