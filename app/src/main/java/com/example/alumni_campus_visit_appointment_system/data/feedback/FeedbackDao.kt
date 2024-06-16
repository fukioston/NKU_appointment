package com.example.alumni_campus_visit_appointment_system.data.feedback

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

@Dao
interface FeedbackDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(feedback: Feedback)

    @Update
    suspend fun update(feedback: Feedback)

    @Delete
    suspend fun delete(feedback: Feedback)
}