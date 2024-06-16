package com.example.alumni_campus_visit_appointment_system.data.feedback

interface FeedbacksRepository {
    /**
     * 获取搜索列表
     */
//    fun getSearchHistoryList(): MutableList<SearchHistory> {
//        return db.searchHistoryDao().getAllHistory()
//    }

    /**
     * 新增搜索历史记录
     */
    suspend fun insertFeedback(feedback: Feedback)


}