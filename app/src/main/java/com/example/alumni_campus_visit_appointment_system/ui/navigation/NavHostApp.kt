package com.example.alumni_campus_visit_appointment_system.ui.navigation

import QaA
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.alumni_campus_visit_appointment_system.ui.AppViewModelProvider
import com.example.alumni_campus_visit_appointment_system.ui.screens.AppointmentDetails
import com.example.alumni_campus_visit_appointment_system.ui.screens.AppointmentUpdate
import com.example.alumni_campus_visit_appointment_system.ui.screens.Booking
import com.example.alumni_campus_visit_appointment_system.ui.screens.CarRegistration
import com.example.alumni_campus_visit_appointment_system.ui.screens.Feedback
import com.example.alumni_campus_visit_appointment_system.ui.screens.LoginApp
import com.example.alumni_campus_visit_appointment_system.ui.screens.MainFrame
import com.example.alumni_campus_visit_appointment_system.ui.screens.PersonalInfo
import com.example.alumni_campus_visit_appointment_system.ui.screens.Precautions
import com.example.alumni_campus_visit_appointment_system.ui.viewmodel.UserViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavHostApp(viewModel: UserViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val isLoggedIn = remember { mutableStateOf<Boolean?>(null) }

    // 使用 LaunchedEffect 来调用 suspend 函数
    LaunchedEffect(key1 = Unit) {
        isLoggedIn.value = viewModel.getIsLoggedIn()
    }

    // 监听 isLoggedIn 状态变化
    val startDestination = when (isLoggedIn.value) {
        true -> Destinations.HomeFrame.route
        else -> Destinations.Login.route
    }
    if (isLoggedIn.value != null) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(Destinations.Login.route) {
            LoginApp(onLoginSuccess = {
                navController.navigate(Destinations.HomeFrame.route) {
                    popUpTo(Destinations.Login.route) { inclusive = true }
                }
            })
        }
        composable(Destinations.HomeFrame.route) {
            MainFrame(onNavigateToPage = { pageType ->
                when (pageType) {
                    1 -> navController.navigate(Destinations.Record.route)
                    2 -> navController.navigate(Destinations.PersonalInfo.route)
                    3 -> navController.navigate(Destinations.Precautions.route)
                    4 -> navController.navigate(Destinations.CarRegistration.route)
                    5 -> navController.navigate(Destinations.QaA.route)
                    6 -> navController.navigate(Destinations.Booking.route)
                    7 -> navController.navigate(Destinations.Feedback.route)

                    else -> { /* 处理未知页面类型 */ }
                }
            },
                onNavigateToDetails = { appointmentId ->
                    navController.navigate("${Destinations.AppointmentDetails.route}/$appointmentId")
                }
            )
        }

        composable(Destinations.Precautions.route) {
            Precautions(navigateBack = { navController.popBackStack() })
        }
        composable(Destinations.CarRegistration.route) {
            CarRegistration(navigateBack = { navController.popBackStack() })
        }
        composable(Destinations.PersonalInfo.route) {
            PersonalInfo(navigateBack = { navController.popBackStack() })
        }
        composable(Destinations.QaA.route) {
            QaA(navigateBack = { navController.popBackStack() })
        }
        composable(Destinations.Booking.route) {
            Booking(navigateBack = { navController.popBackStack() })
        }
        composable(Destinations.Feedback.route) {
            Feedback(navigateBack = { navController.popBackStack() })
        }
        composable(
            route = "${Destinations.AppointmentDetails.route}/{appointmentId}",
            arguments = listOf(navArgument("appointmentId") { type = NavType.IntType })
        ) { backStackEntry ->
            val appointmentId = backStackEntry.arguments?.getInt("appointmentId") ?: 0
            AppointmentDetails(appointmentId = appointmentId, navigateBack = { navController.popBackStack() },
                onNavigateToUpdate = { appointmentId ->
                    navController.navigate("${Destinations.AppointmentUpdate.route}/$appointmentId")
                })
        }
        composable(
            route = "${Destinations.AppointmentUpdate.route}/{appointmentId}",
            arguments = listOf(navArgument("appointmentId") { type = NavType.IntType })
        ) { backStackEntry ->
            val appointmentId = backStackEntry.arguments?.getInt("appointmentId") ?: 0
            AppointmentUpdate(appointmentId = appointmentId, navigateBack = { navController.popBackStack() })
        }
    }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun NavHostPreview() {
    NavHostApp()
}
