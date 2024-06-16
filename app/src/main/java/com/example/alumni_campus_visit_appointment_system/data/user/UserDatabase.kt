package com.example.alumni_campus_visit_appointment_system.data.user
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [User::class], version = 2, exportSchema = false)
abstract class UserDatabase : RoomDatabase()
{
    abstract fun userDao(): UserDao
    companion object {
        @Volatile
        private var Instance:UserDatabase? = null
        private val MIGRATION_1_2 = object : Migration(1,2){
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE `$USERS` ADD COLUMN `$PASSWORD` TEXT")
            }
        }
        fun getDatabase(context: Context): UserDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return UserDatabase.Instance ?: synchronized(this) {
                Room.databaseBuilder(context, UserDatabase::class.java, "nk2")
                    .addMigrations(MIGRATION_1_2)
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { UserDatabase.Instance = it }
            }
        }
    }
}
