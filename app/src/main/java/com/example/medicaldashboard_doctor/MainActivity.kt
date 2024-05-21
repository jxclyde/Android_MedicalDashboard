package com.example.medicaldashboard_doctor

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.medicaldashboard_doctor.api.DoctorAPI
import com.example.medicaldashboard_doctor.api.ReviewAPI
import com.example.medicaldashboard_doctor.api.TestAPIService


class MainActivity : AppCompatActivity() {
    private lateinit var doctorsAdapter: DoctorsAdapter
    lateinit var db : MedicalDashboardDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createDB()

        val recyclerView: RecyclerView = findViewById(R.id.doctorsRecyclerView)
        doctorsAdapter = DoctorsAdapter(mutableListOf())
        recyclerView.adapter = doctorsAdapter
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
        
        val detailsButton: Button = findViewById(R.id.buttonDetails)
        detailsButton.setOnClickListener {
            val intent = Intent(this, DoctorDetailsActivity::class.java)
            intent.putExtra("DB_NAME", "medical_dashboard_database")
            startActivity(intent)
        }

        testDB()

        // Створюємо список лікарів
//        val doctorsList = listOf(
//            Doctor(R.drawable.ic_doctor_photo, "Sarah Patel", "Cardiologist", 10),
//            Doctor(R.drawable.ic_doctor_photo, "Michael Chang", "Dentist", 15),
//            Doctor(R.drawable.ic_doctor_photo, "Emily Rodriguez", "Neurologist", 20),
//            Doctor(R.drawable.ic_doctor_photo, "David Lee", "Surgeon", 12),
//            Doctor(R.drawable.ic_doctor_photo, "Lisa Nguyen", "Pediatrician", 18),
//            Doctor(R.drawable.ic_doctor_photo, "Jamal Khan", "Psychiatrist", 25)
//        )


        //val customAdapter1 = DoctorsAdapter(doctorsList)

        //тут було змінено  ініціалізацію адаптера так, щоб він приймала порожній вже змінний список лікарів, а не хардкод
        //        val customAdapter1 = DoctorsAdapter(mutableListOf())


    }


    private fun loadData() {
        Log.d("API", "loadData")
        val service = TestAPIService()
        service.getDoctors(object : TestAPIService.DoctorsAPICallback {
            override fun onSuccess(doctor: DoctorAPI) {
                displayDoctors(doctor)
                val dao = db.doctorInfoDao()
                dao.insert(doctor.toDoctorInfo())
            }
            override fun onFailure() {
                displayError()
            }
        })

        service.getReviews(object : TestAPIService.ReviewsAPICallback {
            override fun onSuccess(review: ReviewAPI) {
                displayReviews(review)
                val dao = db.reviewDao()
                dao.insert(review.toReview())
            }
            override fun onFailure() {
                displayError()
            }
        })
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

    private fun displayError() {
        Log.d("API", "error loading data")
        Toast.makeText(MainActivity@ this, "Data loading failed!", Toast.LENGTH_LONG).show()
    }

    //Ініціалізація БД
    fun createDB() {
        db = Room.databaseBuilder(
            applicationContext,
            MedicalDashboardDatabase::class.java, "medical_dashboard_database"
        ).allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()
    }

    //Основні функції тестування БД
    private fun testDB() {
        testInsertDoctors()
        loadDoctorsFromDB()
        testReadDoctors()
        //testDeleteDoctors()
        //testReadDoctors()
        //testUpdateDoctors()
        //testReadDoctors()
    }

    private fun loadDoctorsFromDB() {
        val dao = db.doctorInfoDao()
        val allDoctors = dao.getAll()
        doctorsAdapter.updateDoctors(allDoctors)
    }


    private fun writeMessage(message: String) {
        Log.d("TEST", message)
    }

    private fun testInsertDoctors() {
        writeMessage("Inserting doctors")
        val dao = db.doctorInfoDao()
        val doc1 = DoctorInfo(1, "John", "Watt", "Cardiologist", description = "test description for 1 doctor", reviewsAmount = "10", availableHours = "10.30 AM", availableDates = "24.04.2024")
        val doc2 = DoctorInfo(2, "Matt", "Daymon", "Neurologist", description = "test description for 2 doctor", reviewsAmount = "5", availableHours = "8.30 AM", availableDates = "24.04.2024")

        dao.insert(doc1)
        dao.insert(doc2)
    }


    private fun testReadDoctors() {
        val dao = db.doctorInfoDao()

        var allDoctors = dao.getAll()
        for(doctor in allDoctors) {
            writeMessage("$doctor")
        }
    }

    private fun testUpdateDoctors() {
        writeMessage("Updating doctors")
        val dao = db.doctorInfoDao()
        val doc2 = DoctorInfo(2, "Matt", "Daymon", "Neurologist", description = "TEST - description for 2 doctor updated ", reviewsAmount = "5", availableHours = "8.30 AM", availableDates = "24.04.2024")

        dao.update(doc2)
    }

    private fun testDeleteDoctors() {
        writeMessage("Deleting doctors")
        val dao = db.doctorInfoDao()
        val doc2 = DoctorInfo(2, "Matt", "Daymon", "Neurologist", description = "TEST - description for 2 doctor updated ", reviewsAmount = "5", availableHours = "8.30 AM", availableDates = "24.04.2024")

        dao.delete(doc2)
    }

}
