package com.example.alumni_campus_visit_appointment_system.ui.viewmodel

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import com.example.alumni_campus_visit_appointment_system.data.appointment.Appointment
import com.example.alumni_campus_visit_appointment_system.data.appointment.AppointmentsRepository


class AppointmentViewModel(private val appointmentsRepository: AppointmentsRepository) : ViewModel() {
    private val TAG = "AppointmentViewModel"
    suspend fun addAppointment(appointment: Appointment) {

        appointmentsRepository.insertAppointment(appointment)

    }
    suspend fun updateAppointment(appointment: Appointment) {

        appointmentsRepository.updateAppointment(appointment)

    }
    suspend fun deleteAppointment(appointment: Appointment) {

        appointmentsRepository.deleteAppointment(appointment)

    }
    suspend fun getAppointmentByUploadName(uploadName: String): List<Appointment> {

        return appointmentsRepository.getAppointmentByUploadName(uploadName)

    }
    suspend fun getAppointmentDetailsById(id: Int): Appointment {
//        Log.d("ScheduleList", "Appointment: $id")
        return appointmentsRepository.getAppointmentDetailsById(id)

    }
    suspend fun getAllAppointments(): List<Appointment> {
        return appointmentsRepository.getAllAppointments()
    }




}
@Stable
data class AppointmentState(
    var id: Long = -1L,
    var content: String = "",

    )


