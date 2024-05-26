package com.example.medicaldashboard_doctor.ui

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.medicaldashboard_doctor.db.MedicalDashboardDatabase
import com.example.medicaldashboard_doctor.db.toDoctorInfo
import com.example.medicaldashboard_doctor.db.toReview
import com.example.medicaldashboard_doctor.di.DiHelper
import com.example.medicaldashboard_doctor.pre_api.DoctorAPI
import com.example.medicaldashboard_doctor.pre_api.ReviewAPI
import com.example.medicaldashboard_doctor.pre_api.TestAPIService
import javax.sql.DataSource


class MainPresenter(val view: MainContract.View) : MainContract.Presenter {
    val service: DataSource = DiHelper.getService()
    lateinit var db: MedicalDashboardDatabase


    override fun doctor() {
        Log.d("API", "load doctor")
        service.getDoctors(object : TestAPIService.DoctorsAPICallback {
            override fun onSuccess(doctor: DoctorAPI) {
                view.displayDoctor(doctor)
            }

            override fun onFailure() {
                view.displayError()
            }
        })
    }

    override fun review() {
        Log.d("API", "load review")
        service.getReviews(object : TestAPIService.ReviewsAPICallback {
            override fun onSuccess(review: ReviewAPI) {
                view.displayReview(review)
            }

            override fun onFailure() {
                view.displayError()
            }
        })
    }

}
