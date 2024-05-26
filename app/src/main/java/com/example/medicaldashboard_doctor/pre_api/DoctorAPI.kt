package com.example.medicaldashboard_doctor.pre_api

import com.google.gson.annotations.SerializedName

data class DoctorAPI (
    @SerializedName("id") var id : Int,
    @SerializedName("first_name") var firstName : String,
    @SerializedName("last_name") var lastName : String,
    @SerializedName("specialization") var specialization : String,
    @SerializedName("description") var description : String,
    @SerializedName("reviews_amount") var reviewsAmount : String,
    @SerializedName("available_hours") var availableHours : String,
    @SerializedName("available_dates") var availableDates : String
)
