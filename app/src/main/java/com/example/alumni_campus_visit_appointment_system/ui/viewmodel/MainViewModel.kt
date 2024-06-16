package com.example.alumni_campus_visit_appointment_system.ui.model.entity


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.alumni_campus_visit_appointment_system.R
import com.example.alumni_campus_visit_appointment_system.model.entity.Category
import com.example.alumni_campus_visit_appointment_system.model.entity.SwiperEntity


class MainViewModel:ViewModel() {



    val swiperData= listOf(
        SwiperEntity(R.drawable.f3),
       SwiperEntity(R.drawable.f4),
        SwiperEntity(R.drawable.f2),
        SwiperEntity(R.drawable.f1)

    )
}