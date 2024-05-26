package com.example.medicaldashboard_doctor.pre_api

import com.google.gson.annotations.SerializedName

data class ReviewAPI(
    @SerializedName("id") var id : Int,
    @SerializedName("nick_name") var nickName : String,
    @SerializedName("first_name") var firstName : String,
    @SerializedName("last_name") var lastName : String,
    @SerializedName("review_text") var reviewText : String,
    @SerializedName("review_date") var reviewDate : String
)
