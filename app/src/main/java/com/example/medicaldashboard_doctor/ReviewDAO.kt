package com.example.medicaldashboard_doctor

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ReviewDAO {
    @Query("SELECT * FROM review")
    fun getAll(): List<Review>

    @Query("SELECT * FROM review WHERE 'first_name' LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): Review

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg infoInput: Review)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(infoInput: Review)

    @Update
    fun update(infoInput: Review)

    @Delete
    fun delete(infoInput: Review)
}