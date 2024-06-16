package com.example.alumni_campus_visit_appointment_system.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException

class UserInfoRepository(
    private val dataStore: DataStore<Preferences>
) {
    private companion object {
        val IS_LOGGED_IN= booleanPreferencesKey("isLoggedIn")
        val USER_NAME = stringPreferencesKey("user_name")
        val USER_EMAIL = stringPreferencesKey("user_email")
        const val TAG = "UserPreferencesRepo"
    }
    val isLoggedIn: Flow<Boolean?> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e(TAG, "Error reading preferences.", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[IS_LOGGED_IN] // 返回用户名，如果未设置则为 null
        }

    val userName: Flow<String?> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e(TAG, "Error reading preferences.", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[USER_NAME] // 返回用户名，如果未设置则为 null
        }

    val userEmail: Flow<String?> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e(TAG, "Error reading preferences.", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[USER_EMAIL] // 返回用户邮箱，如果未设置则为 null
        }

    suspend fun saveUserInformation(userName2: String, userEmail: String) {
        dataStore.edit { preferences ->
            preferences[USER_NAME] = userName2
            preferences[USER_EMAIL] = userEmail
        }
        val currentUserName = userName.first()  // 这会挂起，直到 Flow 发出第一个元素
        Log.d(TAG, "Current UserName: $currentUserName")

    }
    suspend fun getUsername(): String? {

        return userName.first()

    }
    suspend fun getIsLoggedIn(): Boolean? {

        return isLoggedIn.first()

    }
    suspend fun setIsLoggedIn() {

        dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN] = true
        }
        val currentUserName = userName.first()  // 这会挂起，直到 Flow 发出第一个元素
        Log.d(TAG, "Current UserName: $currentUserName")

    }
    suspend fun test(){
        val currentUserName = userName.first()  // 这会挂起，直到 Flow 发出第一个元素
        Log.d("test", "Current UserName: $currentUserName")
    }
    suspend fun logout(){
        dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN] = false
        }
        val currentUserName = userName.first()  // 这会挂起，直到 Flow 发出第一个元素
        Log.d(TAG, "Current UserName: $currentUserName")
    }
}