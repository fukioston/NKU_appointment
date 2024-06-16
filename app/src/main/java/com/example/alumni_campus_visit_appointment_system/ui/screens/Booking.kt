package com.example.alumni_campus_visit_appointment_system.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alumni_campus_visit_appointment_system.R
import com.example.alumni_campus_visit_appointment_system.data.appointment.Appointment
import com.example.alumni_campus_visit_appointment_system.ui.AppViewModelProvider
import com.example.alumni_campus_visit_appointment_system.ui.components.TopBar2
import com.example.alumni_campus_visit_appointment_system.ui.viewmodel.AppointmentViewModel
import com.example.alumni_campus_visit_appointment_system.ui.viewmodel.UserViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*



@SuppressLint("UnrememberedMutableState", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Booking(
    navigateBack: () -> Unit,
    viewModel: AppointmentViewModel = viewModel(factory = AppViewModelProvider.Factory),
            viewModel2: UserViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    var name by remember { mutableStateOf("") }
    var idCard by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var licensePlate by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val campusOptions = listOf("八里台校区", "津南校区", "泰达校区")
    var selectedCampus by remember { mutableStateOf(campusOptions[0]) }
    val openDialog = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    var uploadName by remember { mutableStateOf("") }
    var isNameValid by remember { mutableStateOf(true) }
    var isIDValid by remember { mutableStateOf(true) }
    var isPhoneValid by remember { mutableStateOf(true) }
    var isCarNumberValid by remember { mutableStateOf(true) }
    var showSuccessDialog by remember { mutableStateOf(false) }
    var showErrorDialog by remember { mutableStateOf(false) }
    coroutineScope.launch {
        uploadName=viewModel2.getNowUsername()
    }
    LaunchedEffect(name) {
        coroutineScope.launch {
            isNameValid = name == "" || name.length >= 2
        }
    }

    LaunchedEffect(idCard) {
        coroutineScope.launch {
            isIDValid = idCard == "" || idCard.length == 18 && idCard.substring(0, 17).all { char -> char.isDigit() } && (idCard.last().isDigit() || idCard.last().toUpperCase() == 'X')
        }
    }

    LaunchedEffect(phoneNumber) {
        coroutineScope.launch {
            isPhoneValid = phoneNumber == "" || phoneNumber.length == 11 && phoneNumber.startsWith("1")
        }
    }
    LaunchedEffect(licensePlate) {
        coroutineScope.launch {
            isCarNumberValid = licensePlate == "" || licensePlate.length == 7 && licensePlate[0].isChinese() && licensePlate[1].isUpperCase() && licensePlate.substring(2).all { char -> char.isLetterOrDigit() }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBar2(
            title = stringResource(R.string.booking),
            canNavigateBack = true,
            navigateUp = navigateBack
        )

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("姓名") },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.people),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            )
            if (!isNameValid)
                Text("您的姓名应当至少包含两个字符", color = Color.Red, style = TextStyle(fontSize = 12.sp), modifier = Modifier
                    .height(16.dp)
                    .align(Alignment.Start))
            else
                VSpacer(16)


            OutlinedTextField(
                value = idCard,
                onValueChange = { idCard = it },
                label = { Text("身份证") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.card),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            )

            if (!isIDValid)
                Text("身份证号必须是18位，前17位必须是数字，第18位可以是数字或字母“X”", color = Color.Red, style = TextStyle(fontSize = 12.sp), modifier = Modifier
                    .height(16.dp)
                    .align(Alignment.Start))
            else
                VSpacer(16)

            OutlinedTextField(
                value = date,
                onValueChange = { date = it; openDialog.value = true },
                label = { Text("入校日期") },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { openDialog.value = true },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.acg),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { openDialog.value = true }
                    )
                }
            )

            VSpacer(16)

            Box(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = selectedCampus,
                    onValueChange = {},
                    label = { Text("校区") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expanded = true },
                    readOnly = true,
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.school),
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable { expanded = true }
                        )
                    }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    campusOptions.forEachIndexed { index, campus ->
                        DropdownMenuItem(
                            text = { Text(campus) },
                            onClick = {
                                selectedCampus = campus
                                expanded = false
                            }
                        )
                    }
                }
            }

            VSpacer(16)

            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("手机号") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.phone),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            )
            if (!isPhoneValid)
                Text("手机号必须为11位，并且以1开头", color = Color.Red, style = TextStyle(fontSize = 12.sp), modifier = Modifier
                    .height(16.dp)
                    .align(Alignment.Start))
            else
                VSpacer(16)

            OutlinedTextField(
                value = licensePlate,
                onValueChange = { licensePlate = it },
                label = { Text("车牌号") },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.car),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text
                )
            )

            if (!isCarNumberValid) // 这里要改成 isCarNumberValid
                Text("车牌号必须有7位，第一个字符必须是中文，第二个字符必须是大写英文字母", color = Color.Red, style = TextStyle(fontSize = 12.sp), modifier = Modifier
                    .height(16.dp)
                    .align(Alignment.Start))
            else
                VSpacer(16)


            Button(
                onClick = {
                    if (
                        name != "" &&
                        idCard != "" &&
                        date != "" &&
                        selectedCampus != "" &&
                        phoneNumber != "" &&
                        licensePlate != "" &&
                        isNameValid &&
                        isIDValid &&
                        isPhoneValid &&
                        isCarNumberValid
                        ) {
                            showSuccessDialog = true
                            val appointment = Appointment(
                                name = name,
                                idCard = idCard,
                                entryDate = date,
                                campus = selectedCampus,
                                phoneNumber = phoneNumber,
                                licensePlate = licensePlate,
                                uploadName = uploadName
                            )
                            coroutineScope.launch {
                                viewModel.addAppointment(appointment)
                            }
                    } else {
                        showErrorDialog = true
                    }

                },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 16.dp)
            ) {
                Text("提交")
            }
        }
    }

    if (openDialog.value) {
        val datePickerState = rememberDatePickerState()
        val confirmEnabled = derivedStateOf { datePickerState.selectedDateMillis != null }
        DatePickerDialog(
            onDismissRequest = { openDialog.value = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        datePickerState.selectedDateMillis?.let { selectedDateMillis ->
                            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                            date = sdf.format(Date(selectedDateMillis))
                        }
                    },
                    enabled = confirmEnabled.value
                ) {
                    Text("确定")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { openDialog.value = false }
                ) {
                    Text("取消")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    if (showErrorDialog) {
        AlertDialog(
            onDismissRequest = { showErrorDialog = false },
            title = { Text("提交失败！") },
            text = { Text("请检查您的信息") },
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
            title = { Text("提交成功！") },
            text = { Text("请点击确定按钮返回主界面") },
            confirmButton = {
                Button(
                    onClick = {
                        showSuccessDialog = false
                        navigateBack()
                    }
                ) {
                    Text("确定")
                }
            }
        )
    }
}
private fun Char.isChinese(): Boolean {
    val block = Character.UnicodeBlock.of(this)
    return block == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS ||
            block == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS ||
            block == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A ||
            block == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B ||
            block == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_C ||
            block == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_D ||
            block == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT
}
@Preview(showBackground = true)
@Composable
fun BookingPreview() {
    Booking(navigateBack = {})
}
