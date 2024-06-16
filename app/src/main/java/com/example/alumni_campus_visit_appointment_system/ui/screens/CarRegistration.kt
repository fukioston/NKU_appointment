package com.example.alumni_campus_visit_appointment_system.ui.screens


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alumni_campus_visit_appointment_system.R
import com.example.alumni_campus_visit_appointment_system.ui.components.TopBar2
import java.time.LocalDateTime


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CarRegistration(navigateBack: () -> Unit) {
    var carPlate by remember { mutableStateOf("") }
    var studentId by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var campusType by remember { mutableStateOf("") }
    var vehicleType by remember { mutableStateOf("") }

    var startDate by remember { mutableStateOf(LocalDateTime.now()) }
    var endDate by remember { mutableStateOf(LocalDateTime.now().plusYears(1)) }
    var department by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var laneAuthorization by remember { mutableStateOf("") }

    val carPlates = remember { mutableStateListOf<String>() }
    val vehicleTypes = listOf("普通车辆", "小型车辆", "轻型车辆","大型专用车","小型专用车")
    val campusTypes= listOf("八里台","津南","泰达")

    LazyColumn {

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(color = Color(0xFF701E5E)),
                contentAlignment = Alignment.Center
            ) {
                TopBar2(
                    title = stringResource(R.string.carregistration),
                    canNavigateBack = true,
                    navigateUp = navigateBack
                )
            }
            Column(
                modifier = Modifier
                    .background(Color.Transparent)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                Text(
                    "已申请车牌",
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Color.Gray
                    )
                )
                LazyColumn(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(10.dp)
                        )
                        .fillMaxWidth()
                        .height(120.dp)
                        .background(color = Color.LightGray)

                ) {
                    item {

                    }

                    items(carPlates) { plate ->
                        Text(plate)
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    "新车牌申请",
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Color.Gray
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                TextField(
                    value = studentId,
                    onValueChange = { studentId = it },
                    label = { Text("学号") },
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent, // 去掉聚焦时的下划线
                        unfocusedIndicatorColor = Color.Transparent // 去掉未聚焦时的下划线
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("姓名") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(15.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent, // 去掉聚焦时的下划线
                        unfocusedIndicatorColor = Color.Transparent // 去掉未聚焦时的下划线
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = department,
                    onValueChange = { department = it },
                    label = { Text("所属部门") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(15.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent, // 去掉聚焦时的下划线
                        unfocusedIndicatorColor = Color.Transparent // 去掉未聚焦时的下划线
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    value = carPlate,
                    onValueChange = { carPlate = it },
                    label = { Text("车牌号码") },
                    shape = RoundedCornerShape(15.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent, // 去掉聚焦时的下划线
                        unfocusedIndicatorColor = Color.Transparent // 去掉未聚焦时的下划线
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))
                var expanded by remember { mutableStateOf(false) }
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier.clip(RoundedCornerShape(15.dp))
                        .fillMaxWidth()
                        .background(color = Color(0xFFdce3e9))
                )
                {
                    TextButton(
                        onClick = { expanded = true },
                        modifier = Modifier
                            .height(56.dp)
                    ) {
                        Text(
                            text = if (vehicleType.isEmpty()) "车辆类型" else vehicleType,
                            color = Color(0xFF454d52),
                            style = TextStyle(fontSize = 16.sp)
                        )
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        vehicleTypes.forEach { type ->
                            DropdownMenuItem(text = { Text(text = type) }, onClick = {
                                vehicleType = type
                                expanded = false
                            })
                        }
                    }
                }
                var expanded2 by remember { mutableStateOf(false) }
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier.clip(RoundedCornerShape(15.dp))
                        .fillMaxWidth()
                        .background(color = Color(0xFFdce3e9))
                )
                {
                    TextButton(
                        onClick = { expanded2 = true },
                        modifier = Modifier
                            .height(56.dp)
                    ) {
                        Text(
                            text = if (campusType.isEmpty()) "访问校区" else campusType,
                            color = Color(0xFF454d52),
                            style = TextStyle(fontSize = 16.sp)
                        )
                    }
                    DropdownMenu(
                        expanded = expanded2,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        campusTypes.forEach { type ->
                            DropdownMenuItem(text = { Text(text = type) }, onClick = {
                                campusType = type
                                expanded2 = false
                            })
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    label = { Text("手机号") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(15.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent, // 去掉聚焦时的下划线
                        unfocusedIndicatorColor = Color.Transparent // 去掉未聚焦时的下划线
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text("备注") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(15.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent, // 去掉聚焦时的下划线
                        unfocusedIndicatorColor = Color.Transparent // 去掉未聚焦时的下划线
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            if (carPlate.isNotBlank()) {
                                carPlates.add(carPlate)
                                carPlate = ""
                            }
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF701E5E), // 设置底色为南开
                            contentColor = Color.White
                        ) // 设置文字颜色为白色
                    ) {
                        Text("保存")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { /* Return to previous screen */ },
                        modifier = Modifier.height(48.dp)
                            .align(Alignment.CenterHorizontally),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF701E5E), // 设置底色为南开
                            contentColor = Color.White
                        ) // 设置文字颜色为白色
                    ) {
                        Text("退出")
                    }

            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun CarRegistrationPreview() {
//    CarRegistration()
}
