package com.example.alumni_campus_visit_appointment_system.ui.screens


import android.os.Build
import androidx.annotation.RequiresApi
//import androidx.compose.foundation.layout.ColumnScopeInstance.align
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alumni_campus_visit_appointment_system.R
import com.example.alumni_campus_visit_appointment_system.data.feedback.Feedback
import com.example.alumni_campus_visit_appointment_system.ui.AppViewModelProvider
import com.example.alumni_campus_visit_appointment_system.ui.components.TopBar2
import com.example.alumni_campus_visit_appointment_system.ui.viewmodel.FeedbackViewModel
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Feedback(
    navigateBack: () -> Unit,
    viewModel: FeedbackViewModel= viewModel(factory = AppViewModelProvider.Factory) // 假设这里正确实现了 ViewModel 和 ViewModelProvider.Factory
) {
    val coroutineScope = rememberCoroutineScope()
    var feedbackText by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
//        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            TopBar2(
                title = stringResource(R.string.feedback),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        }
        item {
            Spacer(modifier = Modifier.height(16.dp)) // 用于添加顶部栏和输入框之间的空间
            TextField(
                value = feedbackText,
                onValueChange = { feedbackText = it },
                label = { Text("请输入您的反馈意见") },
                singleLine = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp).padding(12.dp)
            )
            Spacer(modifier = Modifier.height(16.dp)) // 输入框和按钮之间的间距
            Button(
                onClick = {
                    val feedback = Feedback(
                        content = feedbackText
                    )
                    coroutineScope.launch {
                        viewModel.addFeedback(feedback)
                    }
                    feedbackText = "" // 点击按钮时清空输入框
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF701E5E), // 设置底色为您提供的颜色
                    contentColor = Color.White // 设置文字颜色为白色
                )
            ) {
                Text("提交反馈")
            }
        }
    }
}
@Preview
@Composable
fun FeedbackPreview() {
//    Feedback()
}

