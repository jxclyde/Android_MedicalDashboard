package com.example.medicaldashboard_doctor.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.medicaldashboard_doctor.db.DoctorInfo
import com.example.medicaldashboard_doctor.db.MedicalDashboardDatabase
import com.example.medicaldashboard_doctor.R
import com.example.medicaldashboard_doctor.adapters.DoctorsAdapter
import com.example.medicaldashboard_doctor.pre_api.DoctorAPI
import com.example.medicaldashboard_doctor.pre_api.ReviewAPI
import com.example.medicaldashboard_doctor.pre_api.TestAPIService
import com.example.medicaldashboard_doctor.db.toDoctorInfo
import com.example.medicaldashboard_doctor.db.toReview


class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var doctorsAdapter: DoctorsAdapter
    lateinit var db: MedicalDashboardDatabase
    lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //createDB()
        presenter.doctor()
        presenter.review()

        val recyclerView: RecyclerView = findViewById(R.id.doctorsRecyclerView)
        doctorsAdapter = DoctorsAdapter(mutableListOf())
        recyclerView.adapter = doctorsAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val detailsButton: Button = findViewById(R.id.buttonDetails)
        detailsButton.setOnClickListener {
            val intent = Intent(this, DoctorDetailsActivity::class.java)
            intent.putExtra("DB_NAME", "medical_dashboard_database")
            startActivity(intent)
        }

    }

}

    private fun displayDoctors(doctor: DoctorAPI) {
        Log.d("API", doctor.firstName)
        Log.d("API", doctor.lastName)
        Log.d("API", doctor.specialization)
        Log.d("API", doctor.description)
        Log.d("API", doctor.reviewsAmount)
        Log.d("API", doctor.availableHours)
        Log.d("API", doctor.availableDates)
    }

    private fun displayReviews(review: ReviewAPI) {
        Log.d("API", review.nickName)
        Log.d("API", review.firstName)
        Log.d("API", review.lastName)
        Log.d("API", review.reviewText)
        Log.d("API", review.reviewDate)
    }

//    private fun displayError() {
//        Log.d("API", "error loading data")
//        Toast.makeText(
//            MainPresenter.this,
//            "Data loading failed!",
//            Toast.LENGTH_LONG
//        ).show()
//    }

    //Ініціалізація БД
//    fun createDB() {
//        db = Room.databaseBuilder(
//            applicationContext,
//            MedicalDashboardDatabase::class.java, "medical_dashboard_database"
//        ).allowMainThreadQueries()
//        .fallbackToDestructiveMigration()
//        .build()
//    }
//
//    //Основні функції тестування БД
//    private fun testDB() {
//        testInsertDoctors()
//        loadDoctorsFromDB()
//        testReadDoctors()
//        //testDeleteDoctors()
//        //testReadDoctors()
//        //testUpdateDoctors()
//        //testReadDoctors()
//    }
//
//    private fun loadDoctorsFromDB() {
//        val dao = db.doctorInfoDao()
//        val allDoctors = dao.getAll()
//        doctorsAdapter.updateDoctors(allDoctors)
//    }
//
//    private fun writeMessage(message: String) {
//        Log.d("TEST", message)
//    }
//
//    private fun testInsertDoctors() {
//        writeMessage("Inserting doctors")
//        val dao = db.doctorInfoDao()
//        val doc1 = DoctorInfo(1, "John", "Watt", "Cardiologist", description = "test description for 1 doctor", reviewsAmount = "10", availableHours = "10.30 AM", availableDates = "24.04.2024")
//        val doc2 = DoctorInfo(2, "Matt", "Daymon", "Neurologist", description = "test description for 2 doctor", reviewsAmount = "5", availableHours = "8.30 AM", availableDates = "24.04.2024")
//
//        dao.insert(doc1)
//        dao.insert(doc2)
//    }
//
//    private fun testReadDoctors() {
//        val dao = db.doctorInfoDao()
//
//        var allDoctors = dao.getAll()
//        for(doctor in allDoctors) {
//            writeMessage("$doctor")
//        }
//    }
//
//    private fun testUpdateDoctors() {
//        writeMessage("Updating doctors")
//        val dao = db.doctorInfoDao()
//        val doc2 = DoctorInfo(2, "Matt", "Daymon", "Neurologist", description = "TEST - description for 2 doctor updated ", reviewsAmount = "5", availableHours = "8.30 AM", availableDates = "24.04.2024")
//
//        dao.update(doc2)
//    }
//
//    private fun testDeleteDoctors() {
//        writeMessage("Deleting doctors")
//        val dao = db.doctorInfoDao()
//        val doc2 = DoctorInfo(2, "Matt", "Daymon", "Neurologist", description = "TEST - description for 2 doctor updated ", reviewsAmount = "5", availableHours = "8.30 AM", availableDates = "24.04.2024")
//
//        dao.delete(doc2)
//    }
