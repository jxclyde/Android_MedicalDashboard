package com.example.medicaldashboard_doctor

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DoctorInfo::class, Review::class], version = 2, exportSchema = false)
abstract class MedicalDashboardDatabase : RoomDatabase() {
    abstract fun doctorInfoDao(): DoctorInfoDao
    abstract fun reviewDao(): ReviewDAO
}