package com.example.alumni_campus_visit_appointment_system.model.entity

import com.squareup.moshi.JsonClass
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@JsonClass(generateAdapter = true)
data class ChatCompletionResponse(
    val id: String,
    val `object`: String,
    val created: Long,
    val result: String,
    @SerialName("is_truncated")
    val isTruncated: Boolean,
    @SerialName("need_clear_history")
    val needClearHistory: Boolean,
    @SerialName("finish_reason")
    val finishReason: String,
    val usage: Usage
)

@JsonClass(generateAdapter = true)
data class Usage(
    @SerialName("prompt_tokens")
    val promptTokens: Int,
    @SerialName("completion_tokens")
    val completionTokens: Int,
    @SerialName("total_tokens")
    val totalTokens: Int
)
@JsonClass(generateAdapter = true)
data class TokenResponse(
    val refresh_token: String,
    val expires_in: Int,
    val session_key: String,
    val access_token: String,
    val scope: String,
    val session_secret: String
)