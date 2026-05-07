package com.example.echat.presentation.screens.protectedchat

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.echat.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProtectedChat(navController: NavController) {
    Scaffold(containerColor = Color(0xFFFFFFFF), topBar = {
        TopAppBar(title = {
            Text(
                text = "David Wayne",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 10.dp)
            )

        }, navigationIcon = {
            Box(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .clickable {
                        navController.popBackStack()
                    }
                    .size(40.dp)
                    .shadow(
                        elevation = 1.dp,
                        shape = CircleShape,
                        clip = false
                    )
                    .background(
                        Color(0xFFFFFFFF),
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_left),
                    contentDescription = null,
                    tint = Color.Black
                )
            }

        }, actions = {
            Row(
                modifier = Modifier.padding(end = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .shadow(
                            elevation = 1.dp,
                            shape = CircleShape,
                            clip = false
                        )
                        .background(
                            Color(0xFFFFFFFF),
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.videocall),
                        contentDescription = "",
                        modifier = Modifier.size(42.dp)
                    )


                }

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .shadow(
                            elevation = 1.dp,
                            shape = CircleShape,
                            clip = false
                        )
                        .background(
                            Color(0xFFFFFFFF),
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.audiocall),
                        contentDescription = "",
                        modifier = Modifier.size(42.dp)
                    )
                }
            }
        }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFFFFFFF)))
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = it.calculateTopPadding(),
                    bottom = it.calculateBottomPadding()
                )
        ) {


        }

    }

}

@Composable
fun ProtectedChatItem(
    icon: Int,
    title: String,
    onCheckedChange: (Boolean) -> Unit,
) {


}