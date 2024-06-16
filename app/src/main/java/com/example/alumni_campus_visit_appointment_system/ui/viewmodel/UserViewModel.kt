package com.example.alumni_campus_visit_appointment_system.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alumni_campus_visit_appointment_system.data.UserInfoRepository
import com.example.alumni_campus_visit_appointment_system.data.user.User
import com.example.alumni_campus_visit_appointment_system.data.user.UserRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.mindrot.jbcrypt.BCrypt


class UserViewModel(private val userRepository: UserRepository, private val userInfoRepository: UserInfoRepository) : ViewModel() {
//    private val userInfoManager =UserInfoManager(LocalContext.current)
    private var nowUser: User? =null

    var userName: StateFlow<String> = userInfoRepository.userName
        .map { userName ->
            userName ?: "默认用户名"  // 如果 userName 为 null，则使用默认值
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(1),
            initialValue = "加载中?..."  // 初始值
        )

    suspend fun getUserById(id:Int): User {
        return userRepository.getUserById(id)
    }

    suspend fun addUser(user:User) {
        userRepository.insertUser(user)
    }
    suspend fun updateUser(user:User){
        userRepository.updateUser(user)
    }
    suspend fun deleteAllUsers(){
        userRepository.deleteAllUsers()
    }
    suspend fun deleteById(id:Int){
        userRepository.deleteById(id)
    }
//这个是数字看有没有的
    suspend fun getUserByUsername(username:String): Int {
        return userRepository.getUserByUsername(username)
    }
    suspend fun getUserInfoByUsername(username:String): User {
//        这是获取现在的userinfo
        return userRepository.getUserInfoByUsername(userInfoRepository.getUsername().toString())
    }
    suspend fun getNowUsername():String{
        return userInfoRepository.getUsername().toString()
    }
    suspend fun Login(username: String, password: String): Boolean {
        Log.d("username",username)
        // 直接在挂起函数中调用另一个挂起函数
        val tem = userRepository.getUserInfoByUsername(username)
        return if (tem != null && BCrypt.checkpw(password, tem.password)) {
            // 成功验证密码后，清除密码并更新 nowUser
            tem.password = ""
            nowUser = tem
            Log.d("Login", "Logged in User: $nowUser")
            true
        } else {
            false
        }
    }


    fun getUserInfo(): User? {
        Log.d("ss", nowUser.toString())
        return nowUser

    }


    suspend fun getIsLoggedIn(): Boolean {
        return withContext(viewModelScope.coroutineContext) {
            userInfoRepository.getIsLoggedIn() == true
        }
    }

    fun setIsLoggedIn(){
        viewModelScope.launch {
             userInfoRepository.setIsLoggedIn()
        }
    }
    fun test(){
        viewModelScope.launch {
            userInfoRepository.test()
        }
    }
    fun logout(){
        viewModelScope.launch {
            userInfoRepository.logout()
        }
    }
    fun setUserName(userName2: String, userEmail: String) {
        viewModelScope.launch {
            userInfoRepository.saveUserInformation(userName2, userEmail)
        }

    }

}
