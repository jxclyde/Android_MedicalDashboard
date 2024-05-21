package com.example.medicaldashboard_doctor
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "doctor_appointment")
data class Appointment(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "specialization") val specialization: String,
    @ColumnInfo(name = "appointment_time") val appointmentTime : String,
    @ColumnInfo(name = "appointment_date") val appointmentDate : String
)
