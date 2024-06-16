package com.example.alumni_campus_visit_appointment_system.data.feedback

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feedbacks")
data class Feedback (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "content")
    val content: String,

)