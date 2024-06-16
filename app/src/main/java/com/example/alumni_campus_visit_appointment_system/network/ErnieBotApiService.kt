package com.example.alumni_campus_visit_appointment_system.network

import com.example.alumni_campus_visit_appointment_system.model.entity.TokenResponse
import com.squareup.moshi.JsonClass
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Headers
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

private val json = Json { ignoreUnknownKeys = true }
val okHttpClient = OkHttpClient.Builder()
    .connectTimeout(100, TimeUnit.SECONDS)
    .readTimeout(100, TimeUnit.SECONDS)
    .writeTimeout(100, TimeUnit.SECONDS)
    .build()
object Network {

    //    数据请求url
    private const val baseUrl = "https://aip.baidubce.com/"
    //    /oauth/2.0/token?client_id=0t1q99rcSeUhNx4TKddLARqn&client_secret=BfFok6F21XaLExGvPoqUAMqnuogLw5Ce&grant_type=client_credentials/
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
            )
        )
        .client(okHttpClient)
        .build()

    fun <T> createService(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }
}
@JsonClass(generateAdapter = true)
data class Message(
    val role: String,
    val content: String
)

@JsonClass(generateAdapter = true)
data class Payload(
    val temperature: Double,
    val top_p: Double,
    val penalty_score: Int,
    val disable_search: Boolean,
    val enable_citation: Boolean,
    val enable_trace: Boolean,
    var messages: List<Message>,
    var stream: Boolean
)
interface HomeService {
    @GET("oauth/2.0/token")
    suspend fun getToken(
        @Query("client_id") clientId: String="0t1q99rcSeUhNx4TKddLARqn",
        @Query("client_secret") clientSecret: String="BfFok6F21XaLExGvPoqUAMqnuogLw5Ce",
        @Query("grant_type") grantType: String = "client_credentials"
    ): TokenResponse
    @Headers("Content-Type: application/json")
    @POST("rpc/2.0/ai_custom/v1/wenxinworkshop/chat/ernie-4.0-8k-0329")
    suspend fun getResponse(
        @Query("access_token") accessToken: String,
        @Body payload: Payload
    ): ResponseBody
    companion object {
        fun instance(): HomeService {
            return Network.createService(HomeService::class.java)
        }
    }
}
