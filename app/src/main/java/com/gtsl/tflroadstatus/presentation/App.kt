package com.gtsl.tflroadstatus.presentation

import android.app.Application
import com.gtsl.tflroadstatus.di.netModule
import com.gtsl.tflroadstatus.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    netModule,
                    presentationModule
                )
            )
        }
    }
}
