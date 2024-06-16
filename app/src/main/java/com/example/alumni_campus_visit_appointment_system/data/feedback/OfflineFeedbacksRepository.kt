package com.example.alumni_campus_visit_appointment_system.data.feedback

class OfflineFeedbacksRepository (private val feedbackDao: FeedbackDao) : FeedbacksRepository {

    override suspend fun insertFeedback(feedback: Feedback) =  feedbackDao.insert(feedback)


}