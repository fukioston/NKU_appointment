package com.example.alumni_campus_visit_appointment_system.data.user

/**
 * Repository that provides insert, update, delete, and retrieve of [Item] from a given data source.
 */
interface UserRepository {

    /**
     * Insert item in the data source
     */
    suspend fun insertUser(item: User)

    /**
     * Delete item from the data source
     */
    suspend fun deleteUser(item: User)

    /**
     * Update item in the data source
     */
    suspend fun updateUser(item: User)
    suspend fun getUserById(id:Int):User
    suspend fun deleteAllUsers()
    suspend fun deleteById(id:Int)
    suspend fun getUserByUsername(username:String):Int

    suspend fun getUserInfoByUsername(username: String): User
}