package com.example.medicaldashboard_doctor.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medicaldashboard_doctor.db.DoctorInfo
import com.example.medicaldashboard_doctor.R

class DoctorsAdapter(private var doctors: MutableList<DoctorInfo>) : RecyclerView.Adapter<DoctorsAdapter.DoctorViewHolder>() {

    fun updateDoctors(newDoctors: List<DoctorInfo>) {
        doctors.clear()
        doctors.addAll(newDoctors)
        notifyDataSetChanged()
    }

    //створюємо екземпляр, який використовуємо для представлення елементів списку
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.doctor_item, parent, false)
        return DoctorViewHolder(view)
    }

    //пов'язуємо даних про певного лікаря до певного холдера, тобто нашої view динамічного списку
    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val doctor = doctors[position]
        holder.bind(doctor)
    }

    //розраховуємо кількість лікарів(елементів, які повинні відобразитись) -  для визначення кількості елементів, які потрібно відобразит
    override fun getItemCount(): Int {
        return doctors.size
    }

    //робимо посилання на візуальні лементи для відображення і зберігаємо
    inner class DoctorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val doctorPhotoImageView: ImageView = itemView.findViewById(R.id.doctorPhotoImageView)
        private val doctorNameTextView: TextView = itemView.findViewById(R.id.doctorNameTextView)
        private val doctorSpecializationTextView: TextView = itemView.findViewById(R.id.doctorSpecializationTextView)
        private val doctorReviewsTextView: TextView = itemView.findViewById(R.id.doctorReviewsTextView)

        //а вже тут за рахунок посилань ми присвоюємо необхідні для відображення дані про лікаря
        fun bind(doctor: DoctorInfo) {
            //doctorPhotoImageView.setImageResource(doctor.photo)
            doctorNameTextView.text = doctor.firstName
            doctorSpecializationTextView.text = doctor.specialization
            doctorReviewsTextView.text = "${doctor.reviewsAmount} user reviews"
        }
    }
}

