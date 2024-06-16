/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.alumni_campus_visit_appointment_system.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.alumni_campus_visit_appointment_system.network.HomeService
import com.example.alumni_campus_visit_appointment_system.network.Message
import com.example.alumni_campus_visit_appointment_system.network.Payload
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import org.json.JSONObject

/**
 * UI state for the Home screen
 */

class QaAViewModel : ViewModel() {
    val homeService= HomeService.instance()
    suspend fun getToken(): String {
        val tokenResponse=homeService.getToken()
        return tokenResponse.access_token
    }
    val messages = listOf(
        Message(role = "user", content = "您好")
    )
    val payload2 = Payload(
        temperature = 0.8,
        top_p = 0.8,
        penalty_score = 1,
        disable_search = false,
        enable_citation = false,
        enable_trace = false,
        messages = messages,
        stream=false
    )
    private val _messagesFlow = MutableSharedFlow<Message>()
    val messagesFlow = _messagesFlow.asSharedFlow()
    suspend fun sendMessage(payload: Payload = payload2, text: String, onStreamUpdate: (String) -> Unit) {
        val accessToken = homeService.getToken().access_token
        Log.d("test", accessToken)
        val prompt =
            "角色扮演任务：南开大学知识问答助手\n" +
                    "角色设定：\n" +
                    "你是南开大学的知识问答助手，名叫“南开助手”。你需要回答与南开大学相关的各种问题，提供准确、详细和有帮助的信息。\n" +
                    "\n" +
                    "示例对话：\n" +
                    "用户：南开助手，南开大学的历史是什么？\n" +
                    "\n" +
                    "南开助手：南开大学创建于1919年，由张伯苓和严修在天津创办，是中国著名的高等学府。\n" +
                    "用户：南开大学有哪些著名学科？\n" +
                    "\n" +
                    "南开助手：南开大学在经济学和化学领域尤为著名，并提供广泛的本科和研究生课程。\n" +
                    "用户：南开大学的著名校友有哪些？\n" +
                    "\n" +
                    "南开助手：著名校友包括中华人民共和国第一任总理周恩来，以及许多其他领域的杰出人物。\n" +
                    "用户：南开大学的校园生活如何？\n" +
                    "\n" +
                    "南开助手：南开大学校园生活丰富多彩，学生组织和活动众多，环境优美，设施先进。\n" +
                    "指导方针：\n" +
                    "提供准确的信息\n" +
                    "详细解释关键点\n" +
                    "关注南开大学相关内容\n" +
                    "使用清晰的语言\n" +
                    "下面是用户的问题：\n" + text

        val messages1 = listOf(
            Message(role = "user", content = prompt)
        )

        payload.messages = messages1
        payload.stream = true
        val result = homeService.getResponse(accessToken, payload)
//        Log.d("stream",result.string())
        // 处理流式输出
        result.byteStream().bufferedReader().forEachLine { line ->
            onStreamUpdate(line)
        }
    }

    suspend fun getReponse(payload:Payload=payload2,text:String): String{
        val accessToken = homeService.getToken().access_token
        Log.d("test",accessToken)
        val prompt=
            "角色扮演任务：南开大学知识问答助手\n" +
                    "角色设定：\n" +
                    "你是南开大学的知识问答助手，名叫“南开助手”。你需要回答与南开大学相关的各种问题，提供准确、详细和有帮助的信息。\n" +
                    "\n" +
                    "示例对话：\n" +
                    "用户：南开助手，南开大学的历史是什么？\n" +
                    "\n" +
                    "南开助手：南开大学创建于1919年，由张伯苓和严修在天津创办，是中国著名的高等学府。\n" +
                    "用户：南开大学有哪些著名学科？\n" +
                    "\n" +
                    "南开助手：南开大学在经济学和化学领域尤为著名，并提供广泛的本科和研究生课程。\n" +
                    "用户：南开大学的著名校友有哪些？\n" +
                    "\n" +
                    "南开助手：著名校友包括中华人民共和国第一任总理周恩来，以及许多其他领域的杰出人物。\n" +
                    "用户：南开大学的校园生活如何？\n" +
                    "\n" +
                    "南开助手：南开大学校园生活丰富多彩，学生组织和活动众多，环境优美，设施先进。\n" +
                    "指导方针：\n" +
                    "提供准确的信息\n" +
                    "详细解释关键点\n" +
                    "关注南开大学相关内容\n" +
                    "使用清晰的语言\n"+"下面是用户的问题：\n"+text

        val messages1 = listOf(
            Message(role = "user", content = prompt)
        )

        payload.messages=messages1
        val result=homeService.getResponse(accessToken,payload)
        val jsonObject = JSONObject(result.string())


        return jsonObject.getString("result")
    }


    /** The mutable State that stores the status of the most recent request */

}
