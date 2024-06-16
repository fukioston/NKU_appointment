package com.example.alumni_campus_visit_appointment_system.ui.viewmodel

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import com.example.alumni_campus_visit_appointment_system.data.feedback.Feedback
import com.example.alumni_campus_visit_appointment_system.data.feedback.FeedbacksRepository

class FeedbackViewModel(private val feedbacksRepository: FeedbacksRepository) : ViewModel() {
    private val TAG = "FeedbackViewModel"
suspend fun addFeedback(feedback: Feedback) {

        feedbacksRepository.insertFeedback(feedback)

    }




}
@Stable
data class FeedbackState(
    var id: Long = -1L,
    var content: String = "",

)


