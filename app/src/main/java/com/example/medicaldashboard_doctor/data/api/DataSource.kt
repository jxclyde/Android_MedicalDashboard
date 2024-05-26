package com.example.medicaldashboard_doctor.data.api

import com.example.medicaldashboard_doctor.pre_api.DoctorAPI
import com.example.medicaldashboard_doctor.pre_api.ReviewAPI

interface DataSource {
    fun getDoctors(callback: DoctorCallback)
    fun getReviews(callback: ReviewCallback)

    interface DoctorCallback {
        fun onSuccess(doctor: DoctorAPI)
        fun onFailure()
    }

    interface ReviewCallback {
        fun onSuccess(review: ReviewAPI)
        fun onFailure()
    }
}