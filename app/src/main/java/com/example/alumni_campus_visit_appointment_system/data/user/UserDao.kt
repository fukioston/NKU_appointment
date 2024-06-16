package com.example.alumni_campus_visit_appointment_system.data.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM users WHERE id=:id")
    suspend fun getUserById(id:Int):User
    @Query("SELECT COUNT(*) FROM users WHERE user_name=:username")
    suspend fun getUserByUsername(username: String): Int
    @Query("SELECT * FROM users WHERE user_name=:username")
    suspend fun getUserInfoByUsername(username: String): User


    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()

    @Query("DELETE FROM users WHERE id=:id")
    suspend fun deleteById(id:Int)
}