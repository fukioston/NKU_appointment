package com.example.alumni_campus_visit_appointment_system.data

import android.content.Context
import com.example.alumni_campus_visit_appointment_system.data.appointment.AppointmentDatabase
import com.example.alumni_campus_visit_appointment_system.data.appointment.AppointmentsRepository
import com.example.alumni_campus_visit_appointment_system.data.appointment.OfflineAppointmentsRepository
import com.example.alumni_campus_visit_appointment_system.data.feedback.FeedbackDatabase
import com.example.alumni_campus_visit_appointment_system.data.feedback.FeedbacksRepository
import com.example.alumni_campus_visit_appointment_system.data.feedback.OfflineFeedbacksRepository
import com.example.alumni_campus_visit_appointment_system.data.user.OfflineUserRepository
import com.example.alumni_campus_visit_appointment_system.data.user.UserDatabase
import com.example.alumni_campus_visit_appointment_system.data.user.UserRepository

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val feedbacksRepository: FeedbacksRepository
    val appointmentsRepository: AppointmentsRepository
    val userRepository: UserRepository
}
//
///**
// * [AppContainer] implementation that provides instance of [OfflineItemsRepository]
// */
class AppDataContainer(private val context: Context) : AppContainer {

    override val feedbacksRepository: FeedbacksRepository by lazy {
        OfflineFeedbacksRepository(FeedbackDatabase.getDatabase(context).feedbackDao())
    }
    override val appointmentsRepository: AppointmentsRepository by lazy {
        OfflineAppointmentsRepository(AppointmentDatabase.getDatabase(context).appointmentDao())
    }
    override val userRepository: UserRepository by lazy {
        OfflineUserRepository(UserDatabase.getDatabase(context).userDao())
    }
}