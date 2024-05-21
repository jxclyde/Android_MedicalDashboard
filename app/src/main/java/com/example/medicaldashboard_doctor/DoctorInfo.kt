package com.example.medicaldashboard_doctor

import com.example.medicaldashboard_doctor.api.DoctorAPI

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "doctor_info")
data class DoctorInfo(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "specialization") val specialization: String,
    @ColumnInfo(name = "description") val description : String,
    @ColumnInfo(name = "reviews_amount") val reviewsAmount : String,
    @ColumnInfo(name = "available_hours") val availableHours : String,
    @ColumnInfo(name = "available_dates") val availableDates : String
)

fun DoctorAPI.toDoctorInfo(): DoctorInfo {
    return DoctorInfo(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        specialization = this.specialization,
        description = this.description,
        reviewsAmount = this.reviewsAmount,
        availableHours = this.availableHours,
        availableDates = this.availableDates
    )
}