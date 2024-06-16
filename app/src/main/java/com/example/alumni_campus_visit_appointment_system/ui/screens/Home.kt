package com.example.alumni_campus_visit_appointment_system.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alumni_campus_visit_appointment_system.ui.components.SwiperContent
import com.example.alumni_campus_visit_appointment_system.ui.components.TopBar
import com.example.alumni_campus_visit_appointment_system.ui.model.entity.MainViewModel


@ExperimentalComposeUiApi
@Composable
fun AppointmentScreen(vm: MainViewModel =viewModel(),onNavigateToPage: (Int) -> Unit = {}) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(15){
            Text(text = "南开大学线上预约平台", fontSize = 25.sp, fontWeight = FontWeight.Bold,
                color = Color.White,
                letterSpacing = 0.15.em )
        }
        SwiperContent(vm)

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(1.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(onClick = {  onNavigateToPage(6) },
                modifier = Modifier
                    .fillMaxWidth() // 按钮宽度为手机屏幕宽度
                    .padding(horizontal = 7.dp,vertical = 3.dp)
                    .height(58.dp), // 设置按钮高度
                shape = RoundedCornerShape(15.dp), // 圆角矩形形状
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF701E5E), // 设置底色为南开
                    contentColor = Color.White, // 设置文字颜色为白色
                )
            )
            {
                Text(text = "前往预约")
            }

            Button(onClick = { onNavigateToPage(7)  },
                modifier = Modifier
                    .fillMaxWidth() // 按钮宽度为手机屏幕宽度
                    .padding(horizontal = 7.dp,vertical = 3.dp)
                    .height(58.dp), // 设置按钮高度
                shape = RoundedCornerShape(15.dp), // 圆角矩形形状
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF701E5E), // 设置底色为南开
                    contentColor = Color.White // 设置文字颜色为白色
                )
            ){
                Text(text = "进行反馈")
            }

            Button(onClick = { onNavigateToPage(3) },
                modifier = Modifier
                    .fillMaxWidth() // 按钮宽度为手机屏幕宽度
                    .padding(horizontal = 7.dp,vertical = 3.dp)
                    .height(58.dp), // 设置按钮高度
                shape = RoundedCornerShape(15.dp), // 圆角矩形形状
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF701E5E), // 设置底色为南开
                    contentColor = Color.White // 设置文字颜色为白色
                )
            ){
                Text(text = "查看通知")
            }
        }
    }
}

