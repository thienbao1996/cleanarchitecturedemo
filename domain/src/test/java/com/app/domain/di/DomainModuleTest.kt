package com.app.domain.di

import com.app.domain.ErrorHandler
import com.app.domain.Repository
import org.junit.After
import org.junit.Test
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

/**
 * Unit test for Domain module
 */
class DomainModuleTest : KoinTest {

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun `verify koinDomainModules init correct`() {
        checkModules {
            modules(
                module {
                    single<Repository> {
                        MockRepository()
                    }
                    single<ErrorHandler> {
                        MockErrorHandler()
                    }
                }
            )
            modules(koinDomainModules)
        }
    }
}

