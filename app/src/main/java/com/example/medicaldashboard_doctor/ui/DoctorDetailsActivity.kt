package com.example.medicaldashboard_doctor.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.medicaldashboard_doctor.db.MedicalDashboardDatabase
import com.example.medicaldashboard_doctor.R
import com.example.medicaldashboard_doctor.db.Review
import com.example.medicaldashboard_doctor.adapters.ReviewsAdapter

class DoctorDetailsActivity : AppCompatActivity() {
    private lateinit var reviewsAdapter: ReviewsAdapter
    private lateinit var db: MedicalDashboardDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_details)

        val dbName = intent.getStringExtra("DB_NAME")

        db = Room.databaseBuilder(
            applicationContext,
            MedicalDashboardDatabase::class.java, dbName
        )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

        val recyclerViewReviews: RecyclerView = findViewById(R.id.reviewsRecyclerView)
        reviewsAdapter = ReviewsAdapter(mutableListOf())
        recyclerViewReviews.adapter = reviewsAdapter
        recyclerViewReviews.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // Завантаження відгуків з БД
        testDB()
    }

    private fun testDB() {
        testInsertReviews()
        loadReviewsFromDB()
        testReadReviews()
        //testUpdateReviews()
        //testDeleteReviews()

    }

    private fun writeMessage(message: String) {
        Log.d("TEST", message)
    }

    private fun testInsertReviews() {
        writeMessage("Inserting reviews")
        val dao = db.reviewDao()
        val review1 = Review(1, "user1", "John", "Watt", "Great doctor!", "2024-05-02")
        val review2 = Review(2, "user2", "Matt", "Daymon", "Very professional!", "2024-05-01")
        dao.insert(review1)
        dao.insert(review2)
    }

    private fun loadReviewsFromDB() {
        val dao = db.reviewDao()
        val allReviews = dao.getAll()
        reviewsAdapter.updateReviews(allReviews)
    }

    private fun testReadReviews() {
        val dao = db.reviewDao()

        var allReviews = dao.getAll()
        for(review in allReviews) {
            writeMessage("$review")
        }
    }

    private fun testUpdateReviews() {
        writeMessage("Updating reviews")
        val dao = db.reviewDao()
        val rev2 = Review(2, "user2", "Matt", "Daymon", "Test user2!", "2024-05-01")

        dao.update(rev2)
    }

    private fun testDeleteReviews() {
        writeMessage("Deleting reviews")
        val dao = db.reviewDao()
        val rev2 = Review(2, "user2", "Matt", "Daymon", "Test user2!", "2024-05-01")

        dao.delete(rev2)
    }


}



        // Тепер робимо список відгуків
//        val reviewsList = listOf(
//            Review(R.drawable.ic_account_circle, userName = "John", reviewText = "Great recommendations"),
//            Review(R.drawable.ic_account_circle, userName = "Dan", reviewText = "Great doctor"),
//            Review(R.drawable.ic_account_circle, userName = "Jenny", reviewText = "Thanks for recommendations"),
//            Review(R.drawable.ic_account_circle, userName = "Max", reviewText = "Great recommendations"),
//            Review(R.drawable.ic_account_circle, userName = "Josh", reviewText = "Great doctor"),
//            Review(R.drawable.ic_account_circle, userName = "Jimmy", reviewText = "Great recommendations"),
//            Review(R.drawable.ic_account_circle, userName = "Ann", reviewText = "Great doctor")
//        )
//
//        val customAdapter2 = ReviewsAdapter(reviewsList)
//        //val recyclerView: RecyclerView = findViewById(R.id.doctorsRecyclerView)
//        val recyclerViewReviews: RecyclerView = findViewById(R.id.reviewsRecyclerView)
//        recyclerViewReviews.adapter = customAdapter2
//        recyclerViewReviews.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
