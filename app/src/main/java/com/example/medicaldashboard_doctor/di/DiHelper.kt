package com.example.medicaldashboard_doctor.di

import com.example.medicaldashboard_doctor.data.api.RetrofitApiHelper
import com.example.medicaldashboard_doctor.data.api.TestAPI
import com.example.medicaldashboard_doctor.pre_api.TestAPIService
import com.example.medicaldashboard_doctor.ui.MainContract
import com.example.medicaldashboard_doctor.ui.MainPresenter
import javax.sql.DataSource

class DiHelper {
    companion object {
        private var mainPresenter: MainContract.Presenter? = null
        private var service: DataSource? = null
        private var retrofitHelper: RetrofitApiHelper? = null

        fun getPresenter(view: MainContract.View) : MainContract.Presenter {
            if (mainPresenter == null) {
                mainPresenter = MainPresenter(view)
            }
            return mainPresenter!!
        }

        fun getService(): DataSource {
            if (service == null) {
                service = TestAPIService()
            }
            return service!!
        }

        fun getRetrofitHelper(): RetrofitApiHelper {
            if (retrofitHelper == null) {
                retrofitHelper = RetrofitApiHelper()
                retrofitHelper?.init()
            }
            return retrofitHelper!!
        }
    }
}