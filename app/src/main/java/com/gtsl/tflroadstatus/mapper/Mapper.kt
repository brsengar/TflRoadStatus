package com.gtsl.tflroadstatus.mapper

import com.gtsl.tflroadstatus.net.repository.model.RoadInfo
import com.gtsl.tflroadstatus.presentation.roadstatus.model.UiRoadInfo

fun RoadInfo.toUiModel(): UiRoadInfo {
    return UiRoadInfo(
        displayName = this.displayName,
        statusSeverity = this.statusSeverity,
        statusSeverityDescription = this.statusSeverityDescription
    )
}