package com.example.alumni_campus_visit_appointment_system.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alumni_campus_visit_appointment_system.R
import com.example.alumni_campus_visit_appointment_system.ui.components.TopBar2

data class Message(val content: String, val author: String, val link: String)

val messages = listOf(
    Message("南开大学商学院校友入校指南", "南开大学商学院校友会", "https://mp.weixin.qq.com/s/enFaA1gkUWepuMcKAmWM0A"),
    Message("南开人最全校园生活服务指南", "南开学生会组织", "https://mp.weixin.qq.com/s/_m5tuJ5tPOardBT35oop5g"),
    Message("南开大学校友返校入校入院指南", "南开MBA之家", "https://mp.weixin.qq.com/s/TtEA7aZCLbuYaWTxU1bi4Q"),
    Message("开学在即，去往南开区这些学校通行有变-->","南开区委宣传会","https://mp.weixin.qq.com/s/dL0i-6P-4wdDLegocLCmZw"),
    Message("食在南开，校园就餐指南来啦~","南开后勤服务","https://mp.weixin.qq.com/s/Bil_hOkIOxzQARdGU0_tPg"),
    Message("收藏！南开人最全校园生活服务指南","南开学生会组织","https://mp.weixin.qq.com/s/_m5tuJ5tPOardBT35oop5g"),
    Message("南开大学校园生活设施","Tianjin-Edu","https://mp.weixin.qq.com/s/DDTQV4eoA8EHXfp4UyioFQ"),
    Message("在南开吃喝玩乐第一弹——生活特辑（八里台校区）","南开化学家","https://mp.weixin.qq.com/s/Bh08VrEgYpMeXlAyJ8__tw")
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Precautions(navigateBack: () -> Unit) {
    Column{

        TopBar2(
            title = stringResource(R.string.precautions),
            canNavigateBack = true,
            navigateUp = navigateBack
        )

        Spacer(modifier = Modifier.height(8.dp))
        MessageList(messages = messages)
    }
}

@Composable
fun MessageList(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageItem(message = message)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun MessageItem(message: Message) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .clickable { openLink(context, message.link) }
            .padding(16.dp)
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Text(
            text = message.content,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "作者: ${message.author}",
            style = TextStyle(color = Color.Gray, fontSize = 16.sp)
        )
    }
}

fun openLink(context: Context, link: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
    context.startActivity(intent)
}

@Composable
@Preview
fun PrecautionsPreview() {
//    Precautions()
}