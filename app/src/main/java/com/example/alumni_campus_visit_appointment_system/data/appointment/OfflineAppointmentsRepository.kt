package com.example.alumni_campus_visit_appointment_system.data.appointment


class OfflineAppointmentsRepository (private val appointmentDao: AppointmentDao) : AppointmentsRepository {

    override suspend fun insertAppointment(appointment: Appointment) {
        appointmentDao.insert(appointment)
    }
    override suspend fun getAppointmentById(id: Int): List<Appointment> {
        return appointmentDao.getAppointmentById(id)
    }

    override suspend fun deleteAppointment(appointment: Appointment) {
        appointmentDao.deleteAppointment(appointment)
    }

    override suspend fun updateAppointment(appointment: Appointment) {
        appointmentDao.update(appointment)
    }

    override suspend fun getAppointmentDetailsById(id: Int): Appointment {
        return appointmentDao.getAppointmentDetailsById(id)
    }

    override suspend fun getAppointmentByUploadName(uploadName: String): List<Appointment> {
        return appointmentDao.getAppointmentByUploadName(uploadName)
    }

    override suspend fun getAllAppointments(): List<Appointment> {
        return appointmentDao.getAllAppointments()
    }

    }


