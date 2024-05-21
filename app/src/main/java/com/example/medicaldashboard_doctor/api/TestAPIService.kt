package com.example.medicaldashboard_doctor.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TestAPIService {
    var api: TestAPI
    val SECRET_KEY = ""

    init {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.jsonbin.io/v3/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(TestAPI::class.java)
    }

    fun getDoctors(callback: DoctorsAPICallback) {
        api.getDoctors(SECRET_KEY).enqueue(object : Callback<DoctorAPI> {
            override fun onResponse(call: Call<DoctorAPI>, response: Response<DoctorAPI>) {
                if (response.code() == 200 && response.body() != null)
                    callback.onSuccess(response.body()!!)
                else
                    callback.onFailure()
            }
            override fun onFailure(call: Call<DoctorAPI>, t: Throwable) {
                callback.onFailure()
            }
        })
    }

    fun getReviews(callback: ReviewsAPICallback) {
        api.getReviews(SECRET_KEY).enqueue(object : Callback<ReviewAPI> {
            override fun onResponse(call: Call<ReviewAPI>, response: Response<ReviewAPI>) {
                if (response.code() == 200 && response.body() != null)
                    callback.onSuccess(response.body()!!)
                else
                    callback.onFailure()
            }
            override fun onFailure(call: Call<ReviewAPI>, t: Throwable) {
                callback.onFailure()
            }
        })
    }

    interface DoctorsAPICallback {
        fun onSuccess(doctorAPI: DoctorAPI)
        fun onFailure()
    }

    interface ReviewsAPICallback {
        fun onSuccess(reviewAPI: ReviewAPI)
        fun onFailure()
    }
}