package com.example.medicaldashboard_doctor.data.api

import com.example.medicaldashboard_doctor.pre_api.DoctorAPI
import com.example.medicaldashboard_doctor.pre_api.ReviewAPI
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface TestAPI {
    @GET("b/664b957facd3cb34a84af727?meta=false")
    fun getDoctors(@Header("X-MASTER-Key") secretKey: String): Call<DoctorAPI>

    @GET("b/664b953ce41b4d34e4f6cc35?meta=false")
    fun getReviews(@Header("X-MASTER-Key") secretKey: String): Call<ReviewAPI>
}