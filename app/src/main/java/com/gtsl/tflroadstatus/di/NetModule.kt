package com.gtsl.tflroadstatus.di

import android.content.Context
import com.google.gson.Gson
import com.gtsl.tflroadstatus.R
import com.gtsl.tflroadstatus.config.Config
import com.gtsl.tflroadstatus.net.repository.ApiService
import com.gtsl.tflroadstatus.net.repository.TflRepo
import com.gtsl.tflroadstatus.presentation.App
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val ReadTimeoutMs: Long = 10000
val WriteTimeoutMs: Long = 10000
val ConnectionTimeoutMs: Long = 10000

val netModule: Module = module {

    single {
        provideOkHttpClient()
    }

    single {
        provideConfig(androidContext())
    }

    single {
        provideApiService(get(), get())
    }

    single {
        provideTflRepository(get(), get())
    }
}

private fun provideOkHttpClient(): OkHttpClient =
    OkHttpClient.Builder()
        .readTimeout(ReadTimeoutMs, TimeUnit.MILLISECONDS)
        .writeTimeout(WriteTimeoutMs, TimeUnit.MILLISECONDS)
        .connectTimeout(ConnectionTimeoutMs, TimeUnit.MILLISECONDS)
        .build()


private fun provideApiService(client: OkHttpClient, config: Config) =
    Retrofit.Builder()
        .baseUrl(config.baseUrl)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(ApiService::class.java)

private fun provideTflRepository(apiService: ApiService, config: Config) =
    TflRepo(apiService, config)

private fun provideConfig(context: Context): Config {
    val configJson = context.resources.openRawResource(R.raw.config)
        .bufferedReader()
        .use {
            it.readText()
        }

    return Gson().fromJson(configJson, Config::class.java)
}

