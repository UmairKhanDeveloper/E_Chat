package com.example.echat.presentation.screens.addfriend

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.echat.R

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddFriend(navController: NavController) {

    var searchText by remember { mutableStateOf("") }

    Scaffold(
        containerColor = Color(0xFFFFFFFF),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Add Friend",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Image(
                            painter = painterResource(id = R.drawable.arrowback),
                            contentDescription = "",
                            modifier = Modifier
                                .size(42.dp)
                                .padding(start = 10.dp)
                        )
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
                    bottom = it.calculateBottomPadding()
                )
        ) {

            PhoneInputField(
                value = searchText,
                onValueChange = { searchText = it }
            )

            FriendList(searchText)
        }
    }
}

@Composable
fun PhoneInputField(
    value: String,
    onValueChange: (String) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp, start = 24.dp, end = 24.dp)
            .height(60.dp)
            .border(
                width = 1.dp,
                color = Color(0xFFD0D1DB),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(id = R.drawable.united_kingdom),
            contentDescription = "",
            modifier = Modifier
                .size(26.dp)
                .clip(RoundedCornerShape(4.dp))
        )

        Spacer(modifier = Modifier.width(6.dp))

        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = "",
            tint = Color.Gray
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "(+44)",
            color = Color(0xFFD0D1DB),
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.width(10.dp))

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            contentAlignment = Alignment.CenterStart
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = true, // ✅ important
                textStyle = TextStyle(
                    color = Color(0xFF9CA3AF),
                    fontSize = 14.sp
                ),
                modifier = Modifier.fillMaxWidth(),
                decorationBox = { innerTextField ->
                    if (value.isEmpty()) {
                        Text(
                            text = "Enter Phone Number",
                            color = Color(0xFFD0D1DB),
                            fontSize = 14.sp
                        )
                    }
                    innerTextField()
                }
            )
        }
    }
}

@Composable
fun FriendList(searchText: String) {

    val friends = listOf(
        Friend("David Wayne", "(+44) 50 9265 3022", R.drawable.avatar),
        Friend("Edward Mint", "(+44) 50 9265 2090", R.drawable.avatar),
        Friend("May H.G. Kang", "(+44) 50 9265 2214", R.drawable.avatar),
        Friend("Lily Dare", "(+44) 50 9265 5530", R.drawable.avatar),
        Friend("Dennis Dang", "(+44) 50 9265 2225", R.drawable.avatar),
        Friend("Cayla Reiji", "(+44) 50 9265 2529", R.drawable.avatar),
        Friend("Erin Turcotte", "(+44) 50 9265 1559", R.drawable.avatar),
        Friend("Bob Walter", "(+44) 50 9265 2355", R.drawable.avatar)
    )

    val filteredList = friends.filter {
        it.phone.contains(searchText, ignoreCase = true)
    }

    LazyColumn(
        modifier = Modifier.padding(top = 16.dp)
    ) {
        items(filteredList) { friend ->
            FriendItem(friend)
        }
    }
}

data class Friend(
    val name: String,
    val phone: String,
    val image: Int
)

@Composable
fun FriendItem(friend: Friend) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(id = friend.image),
            contentDescription = "",
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(50))
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {

            Text(
                text = friend.name,
                color = Color(0xFF9CA3AF),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = friend.phone,
                color = Color(0xFF9CA3AF),
                fontSize = 13.sp
            )
        }


            Icon(
                painter = painterResource(id = R.drawable.add),
                contentDescription = "",
                tint = Color(0xFF2F80ED),
                modifier = Modifier.size(36.dp)
            )

    }
}