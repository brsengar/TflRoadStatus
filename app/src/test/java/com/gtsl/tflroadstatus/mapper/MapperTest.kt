package com.gtsl.tflroadstatus.mapper

import com.gtsl.tflroadstatus.net.repository.model.RoadInfo
import org.junit.Assert.assertEquals
import org.junit.Test

class MapperTest {

    @Test
    fun `toUiModel converts server info to UiModel`() {
        val serverModel = RoadInfo(
            id = "",
            displayName = "name",
            statusSeverity = "severity",
            statusSeverityDescription = "description",
            bounds = "",
            envelop = "",
            url = ""
        )

        val uiModel = serverModel.toUiModel()

        assertEquals("name", uiModel.displayName)
        assertEquals("severity", uiModel.statusSeverity)
        assertEquals("description", uiModel.statusSeverityDescription)
    }
}
