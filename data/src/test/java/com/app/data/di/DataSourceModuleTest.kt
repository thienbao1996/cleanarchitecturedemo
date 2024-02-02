package com.app.data.di

import org.junit.After
import org.junit.Test
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class DataSourceModuleTest: KoinTest {

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun `verify koinDataSourceModules init correct`() {
        checkModules {
            modules(koinDataSourceModules)
        }
    }


}