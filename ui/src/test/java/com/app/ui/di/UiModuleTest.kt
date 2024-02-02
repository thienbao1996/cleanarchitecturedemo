package com.app.ui.di

import com.app.domain.model.Program
import com.app.domain.model.Result
import com.app.domain.model.detail.Detail
import com.app.domain.usecase.GetDetailUseCase
import com.app.domain.usecase.GetProgramsUseCase
import com.app.ui.detail.DetailViewModel
import com.app.ui.detail.mapper.DetailUiMapper
import com.app.ui.programs.ProgramsViewModel
import com.app.ui.programs.mapper.ProgramsUiMapper
import io.mockk.MockKAnnotations
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class UiModuleTest: KoinTest {

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun `verify koinUiModule init correct`() {
        val uiProgramsViewModelModule = module {
            single {
                ProgramsUiMapper()
            }
            single {
                DetailUiMapper()
            }
            viewModel {
                mockk<ProgramsViewModel>(relaxed = true)
            }
            viewModel {
                mockk<DetailViewModel>(relaxed = true)
            }
        }
        checkModules {
            modules(
                module {
                    factory<GetProgramsUseCase> {
                        MockGetProgramsUseCase()
                    }
                    factory<GetDetailUseCase> {
                        MockGetDetailUseCase()
                    }
                }
            )
            modules(uiProgramsViewModelModule)
        }
    }

    @Test
    fun testCheck() {
        Assert.assertEquals(true, check(arrayOf(1,2,3,1)))
        Assert.assertEquals(false, check(arrayOf(1,2,3,4)))
        Assert.assertEquals(true, check(arrayOf(1,1,1,3,3,4,3,2,4,2)))
    }

    fun check(arr: Array<Int>): Boolean {
        val setNumber = mutableSetOf<Int>()

        arr.forEach { number ->
            if (setNumber.contains(number)) {
                return true
            } else {
                setNumber.add(number)
            }
        }
        return false
    }

    @Test
    fun test2() {
        Assert.assertEquals(true, isSubSequence("abc", "ahbgdc"))
        //Assert.assertEquals(false, isSubSequence("axc", "ahbgdc"))
    }

    fun isSubSequence(s: String, t: String): Boolean {
        var i = 0
        var j = 0
        println(s.length)
        while (i < s.length - 1 && j < t.length - 1) {
            println("index s $i and value = ${s.get(i)}")
            println("index t $j and value = ${t.get(j)}")
            if (s.get(i) == t.get(j)) {
                i++
            }
            j++
        }
        return i == (s.length - 1)
    }
}

class MockGetProgramsUseCase: GetProgramsUseCase {
    override fun invoke(): Single<Result<List<Program>>> {
        return Single.create{}
    }
}

class MockGetDetailUseCase: GetDetailUseCase {
    override fun invoke(url: String): Single<Result<Detail>> {
        return Single.create{}
    }
}