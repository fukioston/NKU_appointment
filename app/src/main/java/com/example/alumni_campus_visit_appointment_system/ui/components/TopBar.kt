package com.example.alumni_campus_visit_appointment_system.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.alumni_campus_visit_appointment_system.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun TopBar(statusBarHeight:Int, modifier: Modifier= Modifier, content: @Composable ()-> Unit) {
//    val systemUiController= rememberSystemUiController()
//    LaunchedEffect(key1 = Unit) {
//        systemUiController.setStatusBarColor(Color.Transparent)
//    }
    val statusBarHeightDp=with(LocalDensity.current){
        statusBarHeight.toDp()
    }
    Row(
        modifier= Modifier
            .background(
                Brush.linearGradient(
                    listOf(
                        Color(0xFF701E5E), // 南开色
                        Color(0xFF701E5E)
                    )
                )
            )
            .fillMaxWidth()
            .height(56.dp + statusBarHeightDp)
            .padding(top = statusBarHeightDp),
        horizontalArrangement = Arrangement.Center
    ){
        content()
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar2(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {}
) {
    val topAppBarColors = TopAppBarDefaults.smallTopAppBarColors(
        containerColor = Color(0xFF701E5E), // 设置顶部栏背景颜色
        titleContentColor = Color.White, // 设置标题颜色
        navigationIconContentColor = Color.White // 设置导航图标颜色
    )

    CenterAlignedTopAppBar(
        title = { Text(title) },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        colors = topAppBarColors,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}
@Preview
@Composable
fun TopBarPreview() {
//    TopBar()
}

