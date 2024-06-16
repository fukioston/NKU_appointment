package com.example.alumni_campus_visit_appointment_system.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alumni_campus_visit_appointment_system.R
import com.example.alumni_campus_visit_appointment_system.data.user.User
import com.example.alumni_campus_visit_appointment_system.ui.AppViewModelProvider
import com.example.alumni_campus_visit_appointment_system.ui.components.TopBar
import com.example.alumni_campus_visit_appointment_system.ui.viewmodel.UserViewModel
import kotlinx.coroutines.launch


@Composable
fun ProfileCard(name: String, major: String, year: Int, description: String) {



    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(100.dp)
                    .clip(shape = CircleShape)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Name: $name", style = MaterialTheme.typography.labelLarge)
            Text(text = "Major: $major", style = MaterialTheme.typography.labelLarge)
            Text(text = "Graduation Year: $year", style = MaterialTheme.typography.labelLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Description: $description", style = MaterialTheme.typography.labelLarge)
            Spacer(modifier = Modifier.height(24.dp))
            ModifyProfileButton()
        }
    }
}
data class  MenuItem(@DrawableRes val icon:Int, val title:String,val type:Int
)
@Composable
fun MineScreen(modifier: Modifier = Modifier, onNavigateToPage: (Int) -> Unit = {},
               viewModel: UserViewModel = viewModel(factory = AppViewModelProvider.Factory)          ) {
    var user by remember { mutableStateOf<User?>(null) }
    val coroutineScope = rememberCoroutineScope()
    var thisname by remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            user = viewModel.getUserInfoByUsername("777777")
             thisname = user!!.userName
        }
    }
    TopBar(15){
        Text(text = "南开大学线上预约平台", fontSize = 25.sp, fontWeight = FontWeight.Bold,
            color = Color.White,
            letterSpacing = 0.15.em )
    }

    val menus = listOf(
        MenuItem(R.drawable.a6, "个人信息",2),
        MenuItem(R.drawable.a3, "温馨提示",3),
        //MenuItem(R.drawable.a4, "车牌登记",4),
        MenuItem(R.drawable.a5, "南开问答",5)
    )

    Column(modifier = Modifier.fillMaxSize().padding(top = 50.dp)) {
        LazyColumn(modifier = Modifier.padding(16.dp)) {
            item {
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp)) {
                    Image(painter = painterResource(id = R.drawable.icon),
                        contentScale = ContentScale.Crop,
                        contentDescription = null,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                    )
                    Column(verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.padding(horizontal = 16.dp)) {
                        Text(thisname,
                            color = Color(0xFF222222),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold)
                    }
                }
            }
            items(menus) { menu ->
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp).clickable {

                            onNavigateToPage(menu.type)
                        }) {
                    Icon(
                        painter = painterResource(id = menu.icon),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Column(modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp)) {
                        Text(text = menu.title,
                            color = Color(0xFF333333),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.fillMaxWidth())
                    }
                    Icon(
                        painter = painterResource(id = R.drawable.right),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ModifyProfileButton() {
    Button(
        onClick = { /* TODO */},
        modifier = Modifier
    ) {
        Text(text = "Modify Profile")
    }
}
