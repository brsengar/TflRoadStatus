package com.gtsl.tflroadstatus.di

import com.gtsl.tflroadstatus.presentation.roadstatus.RoadStatusViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val presentationModule: Module = module {

    viewModel {
        RoadStatusViewModel(
            repository = get(),
            context = androidContext()
        )
    }
}
