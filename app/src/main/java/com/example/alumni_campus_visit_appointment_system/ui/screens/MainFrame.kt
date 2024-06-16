package com.example.alumni_campus_visit_appointment_system.ui.screens

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.alumni_campus_visit_appointment_system.ui.screens.ui.theme.Alumni_Campus_Visit_Appointment_SystemTheme
import com.example.alumni_campus_visit_appointment_system.model.entity.NavigationItem
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainFrame(onNavigateToPage: (Int) -> Unit = {},onNavigateToDetails: (Int) -> Unit = {}) {

    val navigationItems = listOf(
        NavigationItem(title = "主页", icon = Icons.Filled.Home),
        NavigationItem(title = "预约日程", icon = Icons.Filled.DateRange),
        NavigationItem(title = "我的", icon = Icons.Filled.Person),
    )

    var currentNavigationIndex by remember {
        mutableStateOf(0)
    }

    Scaffold(
        bottomBar = {
            NavigationBar() {
                navigationItems.forEachIndexed { index, navigationItem ->
                    NavigationBarItem(
                        selected = currentNavigationIndex == index,
                        onClick = {
                            currentNavigationIndex = index
                        },
                        icon = {
                            Icon(imageVector = navigationItem.icon, contentDescription = null)
                        },
                        label = {
                            Text(text = navigationItem.title)
                        },
                        alwaysShowLabel = false,
                    )
                }
            }
        }
    ) {
        when(currentNavigationIndex) {
            0 -> AppointmentScreen(onNavigateToPage=onNavigateToPage)
            1 -> ScheduleScreen(onNavigateToDetails=onNavigateToDetails)
            2 -> MineScreen(onNavigateToPage=onNavigateToPage)
        }
    }
}





@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    Alumni_Campus_Visit_Appointment_SystemTheme {
        MainFrame()
    }
}