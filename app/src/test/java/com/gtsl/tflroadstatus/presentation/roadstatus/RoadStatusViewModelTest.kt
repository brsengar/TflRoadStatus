package com.gtsl.tflroadstatus.presentation.roadstatus

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gtsl.tflroadstatus.R
import com.gtsl.tflroadstatus.net.repository.TflRepo
import com.gtsl.tflroadstatus.net.repository.model.RoadInfo
import io.mockk.*
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class RoadStatusViewModelTest {

    @get: Rule
    val rule = InstantTaskExecutorRule()

    private val repository: TflRepo = mockk()
    private val context: Context = mockk()

    private lateinit var subject: RoadStatusViewModel

    @Before
    fun setup() {
        subject = RoadStatusViewModel(
            repository = repository,
            context = context
        )
    }

    @Test
    fun `onRoadSearch hides the keyboard`() {
        subject.roadName.value = "RoadName"
        every {
            repository.getRoadInfo(any(), any(), any())
        } just Runs

        subject.onRoadSearch()

        assertEquals(true, subject.shouldHideKeyboard.value)
    }

    @Test
    fun `onRoadSearch sets loading Livedata`() {
        subject.roadName.value = "RoadName"
        every {
            repository.getRoadInfo(any(), any(), any())
        } just Runs

        subject.onRoadSearch()

        assertEquals(true, subject.isLoading.value)
    }

    @Test
    fun `onRoadSearch uses repository to get road status`() {
        subject.roadName.value = "RoadName"
        val captureCallback = slot<(List<RoadInfo>) -> Unit>()
        val errorCallback = slot<(Int) -> Unit>()
        every {
            repository.getRoadInfo(
                "RoadName",
                capture(captureCallback),
                capture(errorCallback)
            )
        } answers {
            val list = configureRoadInfoList()
            captureCallback.captured.invoke(list)
        }

        subject.onRoadSearch()

        verify {
            repository.getRoadInfo(
                "RoadName",
                captureCallback.captured,
                errorCallback.captured
            )
        }

        assertEquals(3, subject.roadStatusLiveData.value?.size)
        assertEquals(null, subject.roadStatusErrorLiveData.value)
    }

    @Test
    fun `onRoadSearch returns error if road does not exist`() {
        subject.roadName.value = "RoadName"
        val captureCallback = slot<(List<RoadInfo>) -> Unit>()
        val errorCallback = slot<(Int) -> Unit>()
        every {
            context.resources.getString(R.string.road_not_found_error)
        } answers {
            "Road not found"
        }
        every {
            repository.getRoadInfo(
                "RoadName",
                capture(captureCallback),
                capture(errorCallback)
            )
        } answers {
            errorCallback.captured.invoke(404)
        }

        subject.onRoadSearch()

        verify {
            repository.getRoadInfo(
                "RoadName",
                captureCallback.captured,
                errorCallback.captured
            )
        }
        assertEquals(null, subject.roadStatusLiveData.value)
        assertEquals("Road not found", subject.roadStatusErrorLiveData.value)
    }

    @Test
    fun `onRoadSearch returns error if some other error is returned from api`() {
        subject.roadName.value = "RoadName"
        val captureCallback = slot<(List<RoadInfo>) -> Unit>()
        val errorCallback = slot<(Int) -> Unit>()
        every {
            context.resources.getString(R.string.road_status_retrieve_error)
        } answers {
            "Unknown error"
        }
        every {
            repository.getRoadInfo(
                "RoadName",
                capture(captureCallback),
                capture(errorCallback)
            )
        } answers {
            errorCallback.captured.invoke(500)
        }

        subject.onRoadSearch()

        verify {
            repository.getRoadInfo(
                "RoadName",
                captureCallback.captured,
                errorCallback.captured
            )
        }
        assertEquals(null, subject.roadStatusLiveData.value)
        assertEquals("Unknown error", subject.roadStatusErrorLiveData.value)
    }

    private fun configureRoadInfoList(): List<RoadInfo> {
        return arrayListOf(
            toRoadInfo("Road 1"),
            toRoadInfo("Road 2"),
            toRoadInfo("Road 3")
        )
    }

    private fun toRoadInfo(displayName: String): RoadInfo {
        return RoadInfo(
            id = "",
            displayName = displayName,
            statusSeverity = "",
            statusSeverityDescription = "",
            bounds = "",
            envelop = "",
            url = ""
        )
    }
}
