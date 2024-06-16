package com.example.alumni_campus_visit_appointment_system.ui.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alumni_campus_visit_appointment_system.R
import com.example.alumni_campus_visit_appointment_system.data.user.User
import com.example.alumni_campus_visit_appointment_system.ui.AppViewModelProvider
import com.example.alumni_campus_visit_appointment_system.ui.components.TopBar2
import com.example.alumni_campus_visit_appointment_system.ui.viewmodel.UserViewModel
import kotlinx.coroutines.launch



@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalInfo(
    navigateBack: () -> Unit,
    viewModel: UserViewModel =viewModel(factory = AppViewModelProvider.Factory)) {
    var editedName by remember { mutableStateOf("") }
    var editedNum by remember { mutableStateOf("") }
    var editedDepartment by remember { mutableStateOf("") }
    var editedDuration by remember { mutableStateOf("") }
    var editedPhone by remember { mutableStateOf("") }
    var editedEmail by remember { mutableStateOf("") }

    var password by remember { mutableStateOf("") }
    var id by remember { mutableStateOf(0) }

    // 是否显示对话框的状态
    val showDialog = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    var user by remember { mutableStateOf<User?>(null) }
    viewModel.logout()
//    这是退出函数，测试用的
    LaunchedEffect(Unit) {
        coroutineScope.launch {
//我这里是为了作弊，生成一个用户
//            所以生成完请注释
            //这里开始
            Log.d("ScheduleList", "Appointment: ${viewModel.getUserById(1)}")
            //val user = User(
            //    userImage = "",
            //    userName = "userName",
            //    userNumber = "userNumber",
            //    userDepartment = "userDepartment",
            //    userDuration = "userDuration",
            //    userPhone = "userPhone",
            //    userEmail = "userEmail",
            //    password = "111111"
            //)
            //    viewModel.addUser(user)
            user = viewModel.getUserInfoByUsername("777777")
            id= user!!.id
            password= user!!.password.toString()
            editedName= user!!.userName
            editedNum= user!!.userNumber
            editedDepartment=user!!.userDepartment
            editedDuration=user!!.userDuration
            editedPhone=user!!.userPhone
            editedEmail=user!!.userEmail

//            这里结束，都注释了
        }
    }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            user?.let {
            TopBar2(
                title = stringResource(R.string.personal_info),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(60.dp)
                    .padding(8.dp)
            ) {
                Text(
                    "姓名", modifier = Modifier.weight(1f),
                    fontSize = 18.sp
                )
                Text(editedName, modifier = Modifier.weight(1f), textAlign = TextAlign.End)
            }
            Divider(color = Color.LightGray, thickness = 1.dp)

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(60.dp)
                    .padding(8.dp)
            ) {
                Text(
                    "学号", modifier = Modifier.weight(1f),
                    fontSize = 18.sp
                )
                Text(editedNum, modifier = Modifier.weight(1f), textAlign = TextAlign.End)
            }
            Divider(color = Color.LightGray, thickness = 1.dp)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(60.dp)
                    .padding(8.dp)
            ) {
                Text(
                    "学院 ", modifier = Modifier.weight(1f),
                    fontSize = 18.sp
                )
                Text(editedDepartment, modifier = Modifier.weight(1f), textAlign = TextAlign.End)
            }
            Divider(color = Color.LightGray, thickness = 1.dp)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(60.dp)
                    .padding(8.dp)
            ) {
                Text(
                    "就读时间", modifier = Modifier.weight(1f),
                    fontSize = 18.sp
                )
                Text(editedDuration, modifier = Modifier.weight(1f), textAlign = TextAlign.End)
            }
            Divider(color = Color.LightGray, thickness = 1.dp)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(60.dp)
                    .padding(8.dp)
            ) {
                Text(
                    "联系方式", modifier = Modifier.weight(1f),
                    fontSize = 18.sp
                )
                Text(editedPhone, modifier = Modifier.weight(1f), textAlign = TextAlign.End)
            }
            Divider(color = Color.LightGray, thickness = 1.dp)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(60.dp)
                    .padding(8.dp)
            ) {
                Text(
                    "E-mail", modifier = Modifier.weight(1f),
                    fontSize = 18.sp
                )
                Text(editedEmail, modifier = Modifier.weight(1f), textAlign = TextAlign.End)
            }
            Divider(color = Color.LightGray, thickness = 1.dp)

            TextButton(
                onClick = {
                    showDialog.value = true
                          },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 7.dp, vertical = 3.dp),

                ) {
                Text(
                    "设置个人信息",
                    modifier = Modifier.weight(1f),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            }
                if (showDialog.value) {
                    AlertDialog(
                        onDismissRequest = {
                            showDialog.value = false
                        },
                        title = {
                            Text("设置个人信息")
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    val user = User(
                                        password =password,
                                        id= id,
                                        userImage = "",
                                        userName = editedName,
                                        userNumber = editedNum,
                                        userDepartment = editedDepartment,
                                        userDuration = editedDuration,
                                        userPhone = editedPhone,
                                        userEmail = editedEmail,
                                    )
                                    // 点击确认按钮时，将用户输入的姓名保存到 editedName 中，并关闭对话框
                                    coroutineScope.launch {
                                        viewModel.updateUser(user)
                                    }
                                    showDialog.value = false

                                }
                            ) {
                                Text("确认")
                            }
                        },
                        dismissButton = {
                            Button(
                                onClick = {
                                    // 点击取消按钮时，关闭对话框
                                    showDialog.value = false
                                }
                            ) {
                                Text("取消")
                            }
                        },
                        // 对话框内容：输入框
                        text = {
                            Column {
                                TextField(
                                    value = editedName,
                                    onValueChange = {
                                        editedName = it
                                    },
                                    label = {
                                        Text("请输入新的姓名")
                                    }
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                // 下拉菜单
                                TextField(
                                    value = editedNum,
                                    onValueChange = {
                                        editedNum = it
                                    },
                                    label = {
                                        Text("请输入学号")
                                    }
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                TextField(
                                    value = editedDepartment,
                                    onValueChange = {
                                        editedDepartment = it
                                    },
                                    label = {
                                        Text("请输入学院")
                                    }
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                TextField(
                                    value = editedDuration,
                                    onValueChange = {
                                        editedDuration = it
                                    },
                                    label = {
                                        Text("请输入就读年份")
                                    }
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                TextField(
                                    value = editedPhone,
                                    onValueChange = {
                                        editedPhone = it
                                    },
                                    label = {
                                        Text("请输入电话号码")
                                    }
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                TextField(
                                    value = editedEmail,
                                    onValueChange = {
                                        editedEmail = it
                                    },
                                    label = {
                                        Text("请输入Email")
                                    }
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        }
                    )
                }
            }}
    }


@Composable
fun InfoItem(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(
            text = "$label:",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PersonalInfoPreview() {
    MaterialTheme {
//        PersonalInfo()
    }
}
