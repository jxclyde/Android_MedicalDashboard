package com.example.medicaldashboard_doctor.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface TestAPI {
    @GET("b/?meta=false")
    fun getDoctors(@Header("X-Access-Key") secretKey: String): Call<DoctorAPI>

    @GET("b/?meta=false")
    fun getReviews(@Header("X-Access-Key") secretKey: String): Call<ReviewAPI>
}