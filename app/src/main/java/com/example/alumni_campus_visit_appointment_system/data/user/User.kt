package com.example.alumni_campus_visit_appointment_system.data.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "USERS")
data class User (
    @ColumnInfo(name = "password")
    var password: String?,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "user_image")
    val userImage: String,
    @ColumnInfo(name = "user_name")
    var userName: String,
    @ColumnInfo(name = "user_number")
    var userNumber: String,
    @ColumnInfo(name = "user_department")
    var userDepartment: String,
    @ColumnInfo(name = "user_Duration")
    var userDuration: String,
    @ColumnInfo(name = "user_phone")
    var userPhone: String,
    @ColumnInfo(name = "user_email")
    var userEmail: String,


    )

const val USERS = "users"
const val PASSWORD = "password"