package com.gtsl.tflroadstatus.net.repository.model

data class RoadInfo(
    val id: String,
    val displayName: String,
    val statusSeverity: String,
    val statusSeverityDescription: String,
    val bounds: String,
    val envelop: String,
    val url: String
)

