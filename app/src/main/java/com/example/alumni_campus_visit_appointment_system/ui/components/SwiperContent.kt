package com.example.alumni_campus_visit_appointment_system.ui.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.alumni_campus_visit_appointment_system.ui.model.entity.MainViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SwiperContent(vm: MainViewModel) {
    //虚拟页数
    val virtualCount=Int.MAX_VALUE
    //实际页数
    val actualCount=vm.swiperData.size

    val initialIndex=virtualCount/2

    val pageState= rememberPagerState(initialPage=initialIndex)

//    DisposableEffect(Unit){
//        val timer=Timer()
//
//    }
    HorizontalPager(count = virtualCount,
        state=pageState
        , modifier = Modifier
            .padding(horizontal = 7.dp,vertical = 5.dp)
            .clip(RoundedCornerShape(8.dp))) {
            index->
        val actualIndex=index%actualCount
        Image(painter = painterResource(id= vm.swiperData[actualIndex].imageUrl), contentDescription =null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(7 / 5f),
            contentScale = ContentScale.Crop)

    }
}



