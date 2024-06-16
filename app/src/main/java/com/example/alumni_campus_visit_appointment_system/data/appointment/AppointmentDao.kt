package com.example.alumni_campus_visit_appointment_system.data.appointment

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface AppointmentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(appointment: Appointment)

    @Update
    suspend fun update(appointment: Appointment)

    @Delete
    suspend fun deleteAppointment(appointment: Appointment)
    @Query("SELECT * FROM appointments WHERE id =1 or id =:id ")
    suspend fun getAppointmentById(id: Int): List<Appointment>

    @Query("SELECT * FROM appointments WHERE id=:id ")
    suspend fun getAppointmentDetailsById(id: Int): Appointment
    @Query("SELECT * FROM appointments")
    suspend fun getAllAppointments(): List<Appointment>
    @Query("SELECT * FROM appointments WHERE upload_name=:uploadName ")
    suspend fun getAppointmentByUploadName(uploadName: String): List<Appointment>
}