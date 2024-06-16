package com.example.alumni_campus_visit_appointment_system.data.feedback

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Feedback::class], version = 1, exportSchema = false)
abstract class FeedbackDatabase: RoomDatabase()
{
    abstract fun feedbackDao(): FeedbackDao
    companion object {
        @Volatile
        private var Instance: FeedbackDatabase? = null

        fun getDatabase(context: Context): FeedbackDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, FeedbackDatabase::class.java, "feedback")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}



