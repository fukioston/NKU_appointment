package com.example.alumni_campus_visit_appointment_system.ui.screens



import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.alumni_campus_visit_appointment_system.R
import com.example.alumni_campus_visit_appointment_system.data.user.User
import com.example.alumni_campus_visit_appointment_system.ui.AppViewModelProvider
import com.example.alumni_campus_visit_appointment_system.ui.viewmodel.UserViewModel
import kotlinx.coroutines.launch
import org.mindrot.jbcrypt.BCrypt


@Composable
fun LoginApp(onLoginSuccess: () -> Unit) {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(navController, startDestination = "login") {
            composable("login") { LoginContent(onLoginSuccess, navController) }
            composable("register") { RegisterContent(navController) }
        }
    }
}
@SuppressLint("SuspiciousIndentation", "StateFlowValueCalledInComposition")
@Composable
fun LoginContent(onLoginSuccess: () -> Unit, navController: NavController,
                 viewModel: UserViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    val coroutineScope = rememberCoroutineScope()
    var isLoggedIn by remember { mutableStateOf(false) }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var showErrorDialog by remember { mutableStateOf(false) }
    var showSuccessDialog by remember { mutableStateOf(false) }
    var usernameExists by remember { mutableStateOf(true) }
    var passworderror by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        VSpacer(50)
        LogIcon()
        VSpacer(30)

        Text(text = "欢迎访问！", fontWeight = FontWeight.Bold, fontSize = TextUnit(23f, TextUnitType.Sp))

        VSpacer(30)

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("用户名") },
            modifier = Modifier.width(350.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
        )

        if (!usernameExists)
            Text("用户名不存在", color = Color.Red, style = TextStyle(fontSize = 12.sp), modifier = Modifier
                .height(16.dp)
                .align(Alignment.Start))
        else
            VSpacer(5)

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("密码") },
            modifier = Modifier.width(350.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()
        )

        if (passworderror)
            Text("密码错误", color = Color.Red, style = TextStyle(fontSize = 12.sp), modifier = Modifier
                .height(16.dp)
                .align(Alignment.Start))
        else
            VSpacer(30)

        Button(
            onClick = {
                coroutineScope.launch {
                    if (username.length < 6 || password.length < 6) {
                        showErrorDialog = true
                        errorMessage = "用户名和密码必须至少包含6个字符。"
                    } else {
                        usernameExists = viewModel.getUserByUsername(username) > 0
                        if (!usernameExists) {
                            showErrorDialog = true
                            errorMessage = "用户名不存在"
                        } else {
                            if (viewModel.Login(username, password)) {
                                viewModel.setUserName(username, password)
                                viewModel.setIsLoggedIn()
                                val isLoggedIn = viewModel.getIsLoggedIn()
                                Log.d("nav222", isLoggedIn.toString())
                                showSuccessDialog = true
                            } else {
                                passworderror = true
                            }
                        }
                    }
                }
            },
            modifier = Modifier
                .width(100.dp)
                .height(40.dp)
                .clip(RoundedCornerShape(8.dp))
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.purple_500))

        ) {
            Text(text = "登录")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .clickable {
                    navController.navigate("register")
                }
                .align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "注册",
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(end = 1.dp)
            )
            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = "注册",
                modifier = Modifier.size(24.dp),
                tint = Color.Gray
            )
        }

        if (showErrorDialog) {
            AlertDialog(
                onDismissRequest = { showErrorDialog = false },
                title = { Text("登录失败") },
                text = { Text(errorMessage) },
                confirmButton = {
                    Button(
                        onClick = { showErrorDialog = false }
                    ) {
                        Text("重试")
                    }
                }
            )
        }

        if (showSuccessDialog) {
            AlertDialog(
                onDismissRequest = { showSuccessDialog = false },
                title = { Text("登录成功") },
                confirmButton = {
                    Button(
                        onClick = {
                            showSuccessDialog = false
                            onLoginSuccess()
                        }
                    ) {
                        Text("确定")
                    }
                }
            )
        }
    }
}


@Composable
fun LogIcon() {
    Image(
        painter = painterResource(id = R.drawable.people),
        contentDescription = "图标",
        modifier = Modifier
            .width(50.dp)
            .height(50.dp)
            .clip(CircleShape)
            .border(2.dp, colorResource(id = android.R.color.black), CircleShape)
    )
}

@Composable
fun VSpacer(height: Int) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(height.dp)
    )
}

@Composable
fun RegisterContent(navController: NavController,
                    viewModel: UserViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    val coroutineScope = rememberCoroutineScope()
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var showErrorDialog by remember { mutableStateOf(false) }
    var showSuccessDialog by remember { mutableStateOf(false) }
    var usernameExists by remember { mutableStateOf(false) }
    var passworderror by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(username) {
        coroutineScope.launch {
            usernameExists = viewModel.getUserByUsername(username) > 0
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        VSpacer(30)
        LogIcon()
        VSpacer(30)

        Text(
            text = "欢迎访问！",
            fontWeight = FontWeight.Bold,
            fontSize = TextUnit(23f, TextUnitType.Sp)
        )

        VSpacer(30)
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("用户名") },
            modifier = Modifier.width(350.dp),
            isError = usernameExists,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
        )
        if (usernameExists) Text(
            "用户名已存在",
            color = Color.Red,
            style = TextStyle(fontSize = 12.sp),
            modifier = Modifier
                .height(16.dp)
                .align(Alignment.Start)
        )
        else Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("密码") },
            modifier = Modifier.width(350.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()
        )

        VSpacer(5)

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("确认密码") },
            isError = passworderror,
            modifier = Modifier.width(350.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()
        )
        if (passworderror) Text(
            "两次密码不一样", color = Color.Red, style = TextStyle(fontSize = 12.sp),
            modifier = Modifier
                .height(16.dp)
                .align(Alignment.Start)
        )
        else Spacer(modifier = Modifier.height(16.dp))

        VSpacer(30)

        Button(
            onClick = {
                coroutineScope.launch {
                    if (username.length < 6 || password.length < 6 || confirmPassword.length < 6) {
                        showErrorDialog = true
                        errorMessage = "用户名和密码必须至少包含6个字符。"
                    } else if (password != confirmPassword) {
                        passworderror = true
                        showErrorDialog = true
                        errorMessage = "两次密码不一致。"
                    } else if (usernameExists) {
                        showErrorDialog = true
                        errorMessage = "用户名已存在。"
                    } else {
                        val user = User(
                            userImage = "",
                            userName = username,
                            userNumber = "",
                            userDepartment = "",
                            userDuration = "",
                            userPhone = "",
                            userEmail = "",
                            password = BCrypt.hashpw(password, BCrypt.gensalt())
                        )
                        viewModel.addUser(user)
                        showSuccessDialog = true
                    }
                }
            },
            modifier = Modifier
                .width(100.dp)
                .height(40.dp)
                .clip(RoundedCornerShape(8.dp))
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.purple_500))
        ) {
            Text(text = "注册")
        }

        VSpacer(15)

        Row(
            modifier = Modifier
                .clickable {
                    navController.navigate("login")
                }
                .align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "返回",
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(end = 1.dp)
            )
            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = "返回",
                modifier = Modifier.size(24.dp),
                tint = Color.Gray
            )
        }

        if (showErrorDialog) {
            AlertDialog(
                onDismissRequest = { showErrorDialog = false },
                title = { Text("注册失败") },
                text = { Text(errorMessage) },
                confirmButton = {
                    Button(
                        onClick = { showErrorDialog = false }
                    ) {
                        Text("重试")
                    }
                }
            )
        }

        if (showSuccessDialog) {
            AlertDialog(
                onDismissRequest = { showSuccessDialog = false },
                title = { Text("注册成功！") },
                text = { Text("请点击确定按钮返回登录界面") },
                confirmButton = {
                    Button(
                        onClick = {
                            showSuccessDialog = false
                            navController.navigate("login")
                        }
                    ) {
                        Text("确定")
                    }
                }
            )
        }
    }
}
