package com.example.alumni_campus_visit_appointment_system

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.alumni_campus_visit_appointment_system.data.AppContainer
import com.example.alumni_campus_visit_appointment_system.data.AppDataContainer
import com.example.alumni_campus_visit_appointment_system.data.UserInfoRepository

private const val LAYOUT_PREFERENCE_NAME = "layout_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = LAYOUT_PREFERENCE_NAME
)
class MyAppApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer
    lateinit var userInfoRepository: UserInfoRepository
    override fun onCreate() {
        Log.i("sss","sss")
        super.onCreate()
        userInfoRepository = UserInfoRepository(dataStore)
        container = AppDataContainer(this)
    }
}
