package com.example.medicaldashboard_doctor.db
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import com.example.medicaldashboard_doctor.pre_api.ReviewAPI


@Entity(tableName = "review")
data class Review (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "nick_name") val nickName: String,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "review_text") val reviewsText: String,
    @ColumnInfo(name = "review_date") val reviewsDate : String,
)

fun ReviewAPI.toReview(): Review {
    return Review(
        id = this.id,
        nickName = this.nickName,
        firstName = this.firstName,
        lastName = this.lastName,
        reviewsText = this.reviewText,
        reviewsDate = this.reviewDate
    )
}