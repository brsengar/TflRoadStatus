package com.gtsl.tflroadstatus.net.repository

import com.gtsl.tflroadstatus.net.repository.model.RoadInfo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("Road/{road_name}")
    fun getRoadInfo(
        @Path("road_name") roadName: String,
        @Query("app_key") appKey: String
    ): Observable<List<RoadInfo>>
}