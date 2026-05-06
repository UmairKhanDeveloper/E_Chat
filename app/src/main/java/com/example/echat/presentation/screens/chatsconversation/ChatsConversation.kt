@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.echat.presentation.screens.chatsconversation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import com.example.echat.R
import com.example.echat.presentation.navigation.Screens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChatsConversation(navController: NavController) {
    var massage by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    Scaffold(contentColor = Color(0xFFFFFFFF), topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                text = "Message",
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        }, navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .shadow(
                            elevation = 10.dp,
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
            }
        }, actions = {
            IconButton(onClick = { navController.popBackStack() }) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .shadow(
                            elevation = 10.dp,
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
                        painter = painterResource(R.drawable.menu),
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }
        }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFFFFFFF)))
    }, bottomBar = {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {


            Image(
                painter = painterResource(id = R.drawable.add),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clickable { expanded = true }
            )

            TextField(
                value = massage,
                onValueChange = {
                    massage = it
                }, placeholder = {
                    Text(
                        text = "Type a message ...",
                        fontSize = 16.sp,
                        color = Color(0xFFc5c5c9)
                    )
                },
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .width(237.dp)
                    .height(56.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color(0xFFf0f0f2),
                    unfocusedContainerColor = Color(0xFFf0f0f2),
                    cursorColor = Color.Black
                )
            )

            Image(painter = painterResource(id = R.drawable.send), contentDescription = "")


        }
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFFFFFFF))
                .padding(top = it.calculateTopPadding(), bottom = it.calculateBottomPadding())
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.userimage),
                    contentDescription = "",
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                )
                Column(
                    modifier = Modifier
                        .clickable { navController.navigate(Screens.UserInformationScreen.route) }
                        .padding(start = 12.dp)
                        .weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "David Wayne",
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = "(+44) 50 9285 3022",
                        fontSize = 12.sp,
                        color = Color(0xFF686A8A),
                    )
                }


                Image(
                    painter = painterResource(id = R.drawable.videocall),
                    contentDescription = "",
                    modifier = Modifier.size(42.dp)
                )


                Spacer(modifier = Modifier.width(8.dp))


                Image(
                    painter = painterResource(id = R.drawable.audiocall),
                    contentDescription = "",
                    modifier = Modifier.size(42.dp)
                )

            }
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(562.dp)
                    .background(color = Color(0xFFf0f0f2))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {

                    ChatBubble("Hi!", "10:10", true)

                    ChatBubble(
                        "Awesome, thanks for letting me know! Can't wait for my delivery. 🎉",
                        "10:11",
                        true
                    )

                    ChatBubble(
                        "No problem at all! I'll be there in about 15 minutes.",
                        "10:11",
                        false
                    )

                    ChatBubble(
                        "I'll text you when I arrive.",
                        "10:11",
                        false
                    )

                }
            }
            if (expanded) {
                Popup(
                    alignment = Alignment.BottomCenter,
                    onDismissRequest = { expanded = false }
                ) {
                    Box(
                        modifier = Modifier
                            .padding(bottom = 20.dp)
                            .width(320.dp)
                            .background(Color.White, RoundedCornerShape(12.dp))
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(20.dp)
                        ) {

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                OptionItem(R.drawable.camera, "Camera")
                                OptionItem(R.drawable.microphone, "Record")
                                OptionItem(R.drawable.user, "Contact")
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                OptionItem(R.drawable.gallery, "Gallery")
                                OptionItem(R.drawable.map_point, "Location")
                                OptionItem(R.drawable.file_text, "Document")
                            }
                        }
                    }
                }
            }

        }


    }
}


@Composable
fun ChatBubble(
    message: String,
    time: String,
    isSender: Boolean
) {

    val bubbleShape = if (isSender) {
        RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp,
            bottomStart = 16.dp,
            bottomEnd = 4.dp
        )
    } else {
        RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp,
            bottomStart = 4.dp,
            bottomEnd = 16.dp
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = if (isSender) Arrangement.End else Arrangement.Start
    ) {
        Column(
            modifier = Modifier
                .clip(bubbleShape)
                .background(
                    if (isSender) Color(0xFF2D6CDF) else Color(0xFFEAEAEA)
                )
                .padding(12.dp)
                .widthIn(max = 250.dp)
        ) {
            Text(
                text = message,
                color = if (isSender) Color.White else Color.Black,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = time,
                fontSize = 10.sp,
                color = if (isSender) Color.White.copy(alpha = 0.7f)
                else Color.Gray,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}

@Composable
fun OptionItem(icon: Int, title: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { }
    ) {
        Box(
            modifier = Modifier
                .size(42.dp)
                .background(Color(0xFF3AA9E3), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = title,
                tint = Color.White,
                modifier = Modifier.size(18.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = title,
            fontSize = 12.sp,
            color = Color.Black
        )
    }
}