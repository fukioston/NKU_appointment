package com.example.alumni_campus_visit_appointment_system.data.appointment

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "appointments")
data class Appointment (
    @ColumnInfo(name = "upload_name")
    val uploadName: String?,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "id_card")
    val idCard: String,

    @ColumnInfo(name = "entry_date")
    val entryDate: String,

    @ColumnInfo(name = "campus")
    val campus: String,

    @ColumnInfo(name = "phone_number")
    val phoneNumber: String,

    @ColumnInfo(name = "license_plate")
    val licensePlate: String,


)
const val APPOINTMENT = "appointments"
const val UPLOADNAME = "upload_name"
