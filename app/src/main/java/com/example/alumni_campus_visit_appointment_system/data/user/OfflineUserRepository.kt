package com.example.alumni_campus_visit_appointment_system.data.user

class OfflineUserRepository (private val UserDao: UserDao) : UserRepository{

    override suspend fun insertUser(item: User) = UserDao.insertUser(item)

    override suspend fun deleteUser(item: User) = UserDao.deleteUser(item)

    override suspend fun updateUser(item: User) = UserDao.updateUser(item)
    override suspend fun getUserById(id: Int): User {
        return UserDao.getUserById(id)
    }

    override suspend fun deleteAllUsers() {
        UserDao.deleteAllUsers()
    }

    override suspend fun deleteById(id: Int) {
        UserDao.deleteById(id)
    }

    override suspend fun getUserByUsername(username: String): Int {
        return UserDao.getUserByUsername(username)
    }

    override suspend fun getUserInfoByUsername(username: String): User
    {
        return UserDao.getUserInfoByUsername(username)
    }
}