package com.example.medicaldashboard_doctor.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.medicaldashboard_doctor.db.Appointment

@Dao
interface AppointmentDao {
    @Query("SELECT * FROM DOCTOR_APPOINTMENT")
    fun getAll(): List<Appointment>

    @Query("SELECT * FROM DOCTOR_APPOINTMENT WHERE 'first_name' LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): Appointment

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg appointmentsInput: Appointment)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(appointmentInput: Appointment)

    @Update
    fun update(appointment: Appointment)

    @Delete
    fun delete(appointment: Appointment)
}