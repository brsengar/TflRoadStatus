package com.gtsl.tflroadstatus.presentation.roadstatus

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gtsl.tflroadstatus.R
import com.gtsl.tflroadstatus.mapper.toUiModel
import com.gtsl.tflroadstatus.net.repository.TflRepo
import com.gtsl.tflroadstatus.presentation.roadstatus.model.UiRoadInfo

class RoadStatusViewModel constructor(
    val repository: TflRepo,
    val context: Context
) : ViewModel() {

    private val _roadStatusErrorLiveData = MutableLiveData<String>()
    val roadStatusErrorLiveData: LiveData<String>
        get() = _roadStatusErrorLiveData

    private val _roadStatusLiveData = MutableLiveData<List<UiRoadInfo>>()
    val roadStatusLiveData: LiveData<List<UiRoadInfo>>
        get() = _roadStatusLiveData

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    val roadName = MutableLiveData<String>("")
    val shouldHideKeyboard = MutableLiveData<Boolean>(false)

    fun onRoadSearch() {
        roadName.value?.let {
            shouldHideKeyboard.value = true
            loadRoadStatus(roadName = it)
        }
    }

    override fun onCleared() {
        repository.cancel()
        super.onCleared()
    }

    private fun loadRoadStatus(roadName: String) {
        _isLoading.value = true
        repository.getRoadInfo(roadName,
            onSuccess = { roadStatusList ->
                _roadStatusLiveData.value = roadStatusList.map { it.toUiModel() }
                _roadStatusErrorLiveData.value = null
                _isLoading.value = false
            },
            onError = { errorCode ->
                // show error to Ui
                if (errorCode == 404) {
                    _roadStatusErrorLiveData.value =
                        context.resources.getString(R.string.road_not_found_error)
                } else {
                    _roadStatusErrorLiveData.value =
                        context.resources.getString(R.string.road_status_retrieve_error)
                }
                _isLoading.value = false
            }
        )
    }
}