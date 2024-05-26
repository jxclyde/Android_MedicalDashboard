package com.example.medicaldashboard_doctor.db

data class Doctor (
    val photo: Int,
    val name: String,
    val specialization: String,
    val reviewsCount: Int
)