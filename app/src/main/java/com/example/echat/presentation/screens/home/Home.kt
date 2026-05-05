package com.example.echat.presentation.screens.home

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.echat.R
import com.example.echat.presentation.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Home(navController: NavController) {

    var isSearchActive by remember { mutableStateOf(false) }
    var isMenuExpanded by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }

    Scaffold(
        containerColor = Color(0xFFFFFFFF),
        topBar = {

            TopAppBar(
                title = {

                    if (isSearchActive) {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth().padding(end = 10.dp)
                                .height(40.dp)
                                .background(
                                    color = Color(0xFFF3F3F3),
                                    shape = RoundedCornerShape(20.dp)
                                )
                                .padding(horizontal = 16.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {

                            BasicTextField(
                                value = searchText,
                                onValueChange = {
                                    searchText = it
                                },
                                singleLine = true,

                                textStyle = LocalTextStyle.current.copy(
                                    color = Color.Black,
                                    fontSize = 13.sp,
                                    lineHeight = 13.sp
                                ),

                                modifier = Modifier.fillMaxWidth(),
                                cursorBrush = SolidColor(Color.Gray),
                                decorationBox = { innerTextField ->

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(40.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Start
                                    ) {

                                        if (searchText.isEmpty()) {
                                            Text(
                                                text = "Search",
                                                color = Color(0xFFBDBDBD),
                                                fontSize = 13.sp
                                            )
                                        }

                                        Box(
                                            modifier = Modifier.weight(1f),
                                            contentAlignment = Alignment.CenterStart
                                        ) {
                                            innerTextField()
                                        }
                                    }
                                }
                            )
                        }
                    }
                },

                navigationIcon = {

                    if (!isSearchActive) {

                        Image(
                            painter = painterResource(
                                id = R.drawable.logo_e_chat__2_
                            ),
                            contentDescription = null
                        )
                    }
                },

                actions = {

                    Row(
                        modifier = Modifier.padding(end = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        IconButton(
                            onClick = {
                                isSearchActive = !isSearchActive
                            }
                        ) {

                            if (isSearchActive) {

                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .background(color = Color(0xFFFFFFFF).copy(alpha = 0.20f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Close Menu",
                                        modifier = Modifier.size(30.dp),
                                        tint = Color.White
                                    )
                                }


                            } else {

                                Image(
                                    painter = painterResource(id = R.drawable.search),
                                    contentDescription = "Search",
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }

                        Box {

                            IconButton(
                                onClick = {
                                    isMenuExpanded = !isMenuExpanded
                                }
                            ) {

                                if (isMenuExpanded) {
                                    Box(
                                        modifier = Modifier
                                            .size(40.dp)
                                            .background(color = Color(0xFFFFFFFF).copy(alpha = 0.20f)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = "Close Menu",
                                            modifier = Modifier.size(30.dp),
                                            tint = Color.White
                                        )
                                    }

                                } else {

                                    Icon(
                                        painter = painterResource(id = R.drawable.plus),
                                        contentDescription = "Open Menu",
                                        modifier = Modifier.size(36.dp),
                                        tint = Color.White
                                    )
                                }
                            }

                            DropdownMenu(
                                expanded = isMenuExpanded,
                                onDismissRequest = {
                                    isMenuExpanded = false
                                },
                                containerColor = Color.White,
                                shape = RoundedCornerShape(10.dp)
                            ) {

                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            "Add Friend",
                                            color = Color(0xFF3F3F46),
                                            modifier = Modifier.padding(start = 8.dp)
                                        )
                                    },
                                    onClick = {
                                        navController.navigate(Screens.AddFriend.route)
                                        isMenuExpanded = false
                                    },
                                    leadingIcon = {
                                        Icon(
                                            painter = painterResource(id = R.drawable.plus),
                                            contentDescription = null,
                                            tint = Color(0xFF9CA3AF),
                                            modifier = Modifier.size(22.dp)
                                        )
                                    }
                                )

                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            "Create Group",
                                            color = Color(0xFF3F3F46),
                                            modifier = Modifier.padding(start = 8.dp)
                                        )
                                    },
                                    onClick = {
                                        navController.navigate(Screens.CreateGroups.route)
                                        isMenuExpanded = false
                                    },
                                    leadingIcon = {
                                        Icon(
                                            painter = painterResource(id = R.drawable.group),
                                            contentDescription = null,
                                            tint = Color(0xFF9CA3AF),
                                            modifier = Modifier.size(22.dp)
                                        )
                                    }
                                )
                            }
                        }
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF135CAF)
                )
            )
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = it.calculateTopPadding(),
                    bottom = it.calculateTopPadding()
                )
        ) {
            ChatListScreen(navController = navController)
        }
    }
}


data class ChatItem(
    val name: String,
    val message: String,
    val time: String,
    val unreadCount: Int,
    val image: Int
)


@Composable
fun ChatItemUI(item: ChatItem,navController: NavController) {

    Row(
        modifier = Modifier.clickable{navController.navigate(Screens.ChatsConversation.route)}
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(id = item.image),
            contentDescription = null,
            modifier = Modifier
                .size(55.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = item.name,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = item.message,
                color = Color.Gray,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = item.time,
                color = Color.Gray,
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            if (item.unreadCount > 0) {
                Box(
                    modifier = Modifier
                        .background(Color(0xFF2979FF), shape = CircleShape)
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = item.unreadCount.toString(),
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Composable
fun ChatListScreen(navController: NavController) {

    val chatList = listOf(
        ChatItem(
            "David Wayne",
            "Thanks a bunch! Have a great day! 😊",
            "10:25",
            5,
            R.drawable.avatar
        ),
        ChatItem(
            "Edward Davidson",
            "Great, thanks so much 👋",
            "22:20 09/05",
            12,
            R.drawable.avatar
        ),
        ChatItem(
            "Angela Kelly",
            "Appreciate it! See you soon! 🚀",
            "10:45 08/05",
            1,
            R.drawable.avatar
        ),
        ChatItem("Jean Dare", "Hooray! 🎉", "20:10 05/05", 0, R.drawable.avatar),
        ChatItem(
            "Dennis Borer",
            "Your order has been successfully delivered",
            "17:02 05/05",
            0,
            R.drawable.avatar
        ),
        ChatItem("Cayla Rath", "See you soon!", "11:20 05/05", 0, R.drawable.avatar),


        )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(chatList) {
            ChatItemUI(it,navController)
        }
    }
}