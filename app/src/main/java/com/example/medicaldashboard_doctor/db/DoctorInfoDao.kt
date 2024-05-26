package com.example.medicaldashboard_doctor.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.medicaldashboard_doctor.db.DoctorInfo

@Dao
interface DoctorInfoDao {
    @Query("SELECT * FROM DOCTOR_INFO")
    fun getAll(): List<DoctorInfo>

    @Query("SELECT * FROM DOCTOR_INFO WHERE 'first_name' LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): DoctorInfo

    @Query("SELECT * FROM DOCTOR_INFO WHERE specialization = :specialization")
    fun findDoctorsBySpecialization(specialization: String): List<DoctorInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg infoInput: DoctorInfo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(infoInput: DoctorInfo)

    @Update
    fun update(infoInput: DoctorInfo)

    @Delete
    fun delete(infoInput: DoctorInfo)
}