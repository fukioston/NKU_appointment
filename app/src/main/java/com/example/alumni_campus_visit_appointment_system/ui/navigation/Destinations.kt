package com.example.alumni_campus_visit_appointment_system.ui.navigation

sealed class Destinations(val route:String) {
    //首页大框架
    object HomeFrame:Destinations("MainActivity")

    object Precautions:Destinations("Precautions")
    object CarRegistration:Destinations("CarRegistration")
    object PersonalInfo:Destinations("PersonalInfo")

    object QaA:Destinations("QaA")

    object Record:Destinations("Record")

    object Booking:Destinations("Booking")

    object Feedback:Destinations("Feedback")

    object AppointmentDetails:Destinations("AppointmentDetails")
    object AppointmentUpdate:Destinations("AppointmentUpdate")

    object Login:Destinations("Login")

}