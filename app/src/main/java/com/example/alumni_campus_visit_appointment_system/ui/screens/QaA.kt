import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alumni_campus_visit_appointment_system.R
import com.example.alumni_campus_visit_appointment_system.ui.AppViewModelProvider
import com.example.alumni_campus_visit_appointment_system.ui.components.TopBar2
import com.example.alumni_campus_visit_appointment_system.ui.viewmodel.QaAViewModel
import kotlinx.coroutines.launch

data class Message(var content: String, val isUser: Boolean)

//写死，后面可调用数据库
val sampleMessages = listOf(
    Message("你好！我是南开大学小助手，您可以向我提问哦！", false)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QaA(navigateBack: () -> Unit) {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            ChatScreen(messages = sampleMessages, navigateBack = navigateBack)
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(messages: List<Message>, navigateBack: () -> Unit,
               viewModel: QaAViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    var messages by remember { mutableStateOf(sampleMessages.toMutableList()) }
    var textState by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current
    val listState = rememberLazyListState()

    Column {
        TopBar2(
            title = stringResource(R.string.qaa),
            canNavigateBack = true,
            navigateUp = navigateBack
        )
        LazyColumn(state = listState, modifier = Modifier.weight(1f).padding(8.dp)) {
            items(messages.size) { index ->
                MessageView(message = messages[index])
            }
        }

        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = textState,
                onValueChange = { textState = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("输入消息") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Send),
                keyboardActions = KeyboardActions(onSend = {
                    if (textState.isNotEmpty()) {
                        messages = (messages + Message(textState, true)).toMutableList()
                        textState = ""
                        keyboardController?.hide()
                    }
                })
            )
            Button(
                onClick = {
                    if (textState.isNotEmpty()) {
                        messages = (messages + Message(textState, true)).toMutableList()
                        val text = textState
                        textState = ""
                        keyboardController?.hide()
                        coroutineScope.launch {
                            // 滚动到最后一个
                            listState.animateScrollToItem(index = messages.size)
                        }
                        coroutineScope.launch {
                            // 调用流式输出函数
                            messages = (messages + Message("", false)).toMutableList()
                            viewModel.sendMessage(text = text) { response ->
                                messages[messages.size - 1].content += response
                                // 触发重新渲染
                                messages = messages.toMutableList()
                                Log.d("sss22",messages[messages.size-1].content)
                                coroutineScope.launch {
                                    // 滚动到最后一个
                                    listState.animateScrollToItem(index = messages.size)
                                }
                            }
                        }
                    }
                },
                modifier = Modifier.padding(start = 8.dp),
                content = { Text("发送") }
            )
        }
    }
}

@Composable
fun MessageView(message: Message) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = if (message.isUser) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = if (message.isUser) Color(0xFF6200EE) else Color.LightGray,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(
                text = message.content,
                fontSize = 16.sp,
                color = if (message.isUser) Color.White else Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewQaA() {
    // QaA()
}
