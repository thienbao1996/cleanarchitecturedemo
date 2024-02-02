package com.app.mycanaille

import android.app.Application
import com.app.data.di.koinDataSourceModules
import com.app.domain.di.koinDomainModules
import com.app.ui.di.koinUiModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {

    override fun onCreate() {
        initKoin()
        super.onCreate()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            androidFileProperties()
            modules(koinDataSourceModules)
            modules(koinDomainModules)
            modules(koinUiModules)
        }
    }
}