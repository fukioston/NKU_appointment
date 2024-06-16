package com.example.alumni_campus_visit_appointment_system.data.appointment

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Appointment::class], version = 2, exportSchema = false)
abstract class AppointmentDatabase: RoomDatabase()
{
    abstract fun appointmentDao(): AppointmentDao
    companion object {
        @Volatile
        private var Instance: AppointmentDatabase? = null
        private val MIGRATION_1_2 = object : Migration(1,2){
            override fun migrate(db: SupportSQLiteDatabase) {
                    db.execSQL("ALTER TABLE `$APPOINTMENT` ADD COLUMN `$UPLOADNAME` TEXT")
            }
        }
        fun getDatabase(context: Context): AppointmentDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppointmentDatabase::class.java, "nk")
                    .addMigrations(MIGRATION_1_2)
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}



