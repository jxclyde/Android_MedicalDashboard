package com.example.medicaldashboard_doctor.ui

import com.example.medicaldashboard_doctor.pre_api.DoctorAPI
import com.example.medicaldashboard_doctor.pre_api.ReviewAPI

interface MainContract {
    interface View {
        fun displayDoctor(doctor: DoctorAPI)
        fun displayReview(review: ReviewAPI)

        fun displayError()
    }

    interface Presenter {
        fun doctor()
        fun review()
    }
}