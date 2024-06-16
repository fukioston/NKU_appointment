package com.example.alumni_campus_visit_appointment_system.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alumni_campus_visit_appointment_system.R
import com.example.alumni_campus_visit_appointment_system.data.appointment.Appointment
import com.example.alumni_campus_visit_appointment_system.ui.AppViewModelProvider
import com.example.alumni_campus_visit_appointment_system.ui.components.TopBar2
import com.example.alumni_campus_visit_appointment_system.ui.viewmodel.AppointmentViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentDetails(
    navigateBack: () -> Unit,
    appointmentId: Int,
    viewModel: AppointmentViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onNavigateToUpdate: (Int) -> Unit = {},
) {
    val coroutineScope = rememberCoroutineScope()
    var appointment by remember { mutableStateOf<Appointment?>(null) }
    var id by remember { mutableStateOf(0) }
    Log.d("AppointmentDetails", "Appointment: $appointment")
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            appointment = viewModel.getAppointmentDetailsById(appointmentId)
            Log.d("AppointmentDetails", "Appointment: $appointment")
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 56.dp, start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            appointment?.let {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        DetailTextField(label = "姓名", value = it.name)
                        DetailTextField(label = "身份证", value = it.idCard)
                        DetailTextField(label = "入校日期", value = it.entryDate)
                        DetailTextField(label = "校区", value = it.campus)
                        DetailTextField(label = "电话", value = it.phoneNumber)
                        DetailTextField(label = "车牌", value = it.licensePlate)
                        id = it.id
                        it.uploadName?.let { it1 -> DetailTextField(label = "申请人", value = it1) }
                    }
                }
            } ?: run {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    onNavigateToUpdate(id)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(
                    text = "修改",
                    color = Color.White
                )
            }

            Button(
                onClick = {
                    coroutineScope.launch {
                        appointment?.let { viewModel.deleteAppointment(it) }
                    }

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(
                    text = "删除",
                    color = Color.White
                )
            }
        }

        TopBar2(
            title = stringResource(R.string.appointmentdetails),
            canNavigateBack = true,
            navigateUp = navigateBack,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTextField(label: String, value: String) {
    OutlinedTextField(
        value = value,
        onValueChange = {},
        label = { Text(label) },
        readOnly = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    )
}
