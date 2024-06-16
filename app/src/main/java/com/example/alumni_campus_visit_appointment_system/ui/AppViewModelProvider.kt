package com.example.alumni_campus_visit_appointment_system.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.alumni_campus_visit_appointment_system.MyAppApplication
import com.example.alumni_campus_visit_appointment_system.ui.viewmodel.AppointmentViewModel
import com.example.alumni_campus_visit_appointment_system.ui.viewmodel.FeedbackViewModel
import com.example.alumni_campus_visit_appointment_system.ui.viewmodel.UserViewModel
import com.example.alumni_campus_visit_appointment_system.ui.viewmodel.QaAViewModel
object AppViewModelProvider {
    val Factory = viewModelFactory {

        initializer {
            FeedbackViewModel(MyAppApplication().container.feedbacksRepository)
        }
        initializer {
            AppointmentViewModel(MyAppApplication().container.appointmentsRepository)
        }
        initializer {
            UserViewModel(MyAppApplication().container.userRepository,MyAppApplication().userInfoRepository)
        }
        initializer {
            QaAViewModel()
        }


    }
}

fun CreationExtras.MyAppApplication(): MyAppApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyAppApplication)
