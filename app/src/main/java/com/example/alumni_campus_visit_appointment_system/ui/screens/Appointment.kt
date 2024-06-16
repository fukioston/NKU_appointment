package com.example.alumni_campus_visit_appointment_system.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alumni_campus_visit_appointment_system.data.appointment.Appointment
import com.example.alumni_campus_visit_appointment_system.ui.AppViewModelProvider
import com.example.alumni_campus_visit_appointment_system.ui.components.TopBar
import com.example.alumni_campus_visit_appointment_system.ui.viewmodel.AppointmentViewModel
import com.example.alumni_campus_visit_appointment_system.ui.viewmodel.UserViewModel
import kotlinx.coroutines.launch

data class ScheduleItem(
    val title: String,
    val time: String,
    val description: String
)

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ScheduleList(
    scheduleItems: List<ScheduleItem>,
    viewModel: AppointmentViewModel = viewModel(factory = AppViewModelProvider.Factory),
    viewModel2: UserViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onNavigateToDetails: (Int) -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()
    var appointmentList by remember { mutableStateOf<List<Appointment>>(emptyList()) }
    var uploadName by remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            uploadName=viewModel2.getNowUsername()
            appointmentList = viewModel.getAppointmentByUploadName(uploadName)
            Log.d("ScheduleList", "Appointment: $appointmentList")
        }
    }

    TopBar(15) {
        Text(
            text = "南开大学线上预约平台",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            letterSpacing = 0.15.em
        )
    }

    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(top = 50.dp),
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 8.dp)
    ) {
        items(appointmentList) { appointment ->
            AppointmentItemCard(appointment = appointment,onNavigateToDetails=onNavigateToDetails)
        }
    }
}

@Composable
fun AppointmentItemCard(appointment: Appointment,onNavigateToDetails: (Int) -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = appointment.name,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = appointment.entryDate,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Text(
                text = appointment.campus,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {onNavigateToDetails(appointment.id)  },
                ) {
                    Text(text = "查看详情")
                }
            }
        }
    }
}

@Composable
fun ScheduleScreen(onNavigateToDetails: (Int) -> Unit) {
    val scheduleItems = listOf(
        ScheduleItem("Meeting", "9:00 AM - 10:00 AM", "Discuss project updates"),
        ScheduleItem("Lunch", "12:00 PM - 1:00 PM", "Grab lunch with team members"),
        ScheduleItem("Presentation", "2:00 PM - 3:00 PM", "Present project proposal")
    )

    ScheduleList(scheduleItems = scheduleItems,onNavigateToDetails=onNavigateToDetails)
}
