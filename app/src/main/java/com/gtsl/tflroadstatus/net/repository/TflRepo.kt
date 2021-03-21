package com.gtsl.tflroadstatus.net.repository

import com.gtsl.tflroadstatus.config.Config
import com.gtsl.tflroadstatus.net.repository.model.RoadInfo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class TflRepo constructor(
    private val apiService: ApiService,
    private val config: Config
) {
    private val disposable = CompositeDisposable()

    fun getRoadInfo(
        roadName: String,
        onSuccess: (List<RoadInfo>) -> Unit,
        onError: (Int) -> Unit
    ) {
        disposable.add(
            apiService.getRoadInfo(roadName, config.apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        onSuccess(it)
                    },
                    {
                        if (it is HttpException) {
                            onError(it.code())
                        } else {
                            onError(-1)
                        }
                    })
        )
    }

    fun cancel() {
        disposable.clear()
    }
}