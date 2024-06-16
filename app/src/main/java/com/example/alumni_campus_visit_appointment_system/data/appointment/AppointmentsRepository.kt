package com.example.alumni_campus_visit_appointment_system.data.appointment


interface AppointmentsRepository {
    suspend fun insertAppointment(appointment: Appointment)
    suspend fun getAppointmentById(id: Int): List<Appointment>
    suspend fun deleteAppointment(appointment: Appointment)
    suspend fun updateAppointment(appointment: Appointment)
    suspend fun getAllAppointments():List<Appointment>
    suspend fun getAppointmentDetailsById(id: Int): Appointment

    suspend fun getAppointmentByUploadName(uploadName: String):List<Appointment>


}