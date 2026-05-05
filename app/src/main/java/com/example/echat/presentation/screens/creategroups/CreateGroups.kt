package com.example.echat.presentation.screens.creategroups

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.echat.R

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CreateGroups(navController: NavController) {

    val titleFont = FontFamily(Font(R.font.robot_black))

    var group by remember { mutableStateOf("") }
    var showBottomSheet by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState()
    var searchText by remember { mutableStateOf("") }
    val selectedFriends = remember { mutableStateListOf<Friend2>() }

    val friends = listOf(
        Friend2("David Wayne", "(+44) 50 9265 3022", R.drawable.avatar),
        Friend2("Edward Davidson", "(+44) 12 8025 2090", R.drawable.avatar),
        Friend2("Angela Kelly", "(+44) 23 6091 2214", R.drawable.avatar),
        Friend2("Jean Dare", "(+1) 633 983 5730", R.drawable.avatar),
        Friend2("Dennis Borer", "(+1) 112 919 2225", R.drawable.avatar),
        Friend2("Cayla Rath", "(+61) 797 982 529", R.drawable.avatar),
        Friend2("Erin Turcotte", "(+61) 362 901 559", R.drawable.avatar),
        Friend2("Rodolfo Walter", "(+1) 529 100 2355", R.drawable.avatar)
    )

    Scaffold(
        containerColor = Color.White,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Create Group", color = Color.White, fontSize = 22.sp)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(
                                    Color.White.copy(alpha = 0.2f),
                                    CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.arrow_left),
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF135CAF)
                )
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Text(
                "Name Group",
                fontFamily = titleFont,
                fontSize = 14.sp,
                color = Color(0xFF686A8A),
                modifier = Modifier.padding(24.dp, 24.dp, 24.dp, 8.dp)
            )

            Box(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .height(56.dp)
                    .fillMaxWidth()
                    .border(1.dp, Color(0xFFD0D1DB), RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.CenterStart
            ) {
                BasicTextField(
                    value = group,
                    onValueChange = { group = it },
                    singleLine = true,
                    modifier = Modifier.padding(horizontal = 14.dp),
                    decorationBox = {
                        if (group.isEmpty()) Text("Enter Name Group")
                        it()
                    }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { showBottomSheet = true },
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFEDF9FF)
                ),
                shape = RoundedCornerShape(10.dp),
                elevation = ButtonDefaults.buttonElevation(0.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(R.drawable.plus),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Add members to group", color = Color(0xFF03A9F4))
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .padding(24.dp)
                    .height(60.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(30.dp))
                    .background(
                        Brush.horizontalGradient(
                            listOf(Color(0xFF40C4FF), Color(0xFF03A9F4))
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text("Create Group", color = Color.White, fontSize = 18.sp)
            }
        }
    }


    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState,
            containerColor = Color.White
        ) {

            Column(
                modifier = Modifier
                    .fillMaxHeight(0.9f)
            ) {

                Text(
                    "Add members to group",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .height(52.dp)
                        .fillMaxWidth()
                        .border(1.dp, Color(0xFFD0D1DB), RoundedCornerShape(10.dp)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Search, null, tint = Color.Gray,
                        modifier = Modifier.padding(start = 12.dp)
                    )

                    BasicTextField(
                        value = searchText,
                        onValueChange = { searchText = it },
                        modifier = Modifier.padding(start = 8.dp),
                        decorationBox = {
                            if (searchText.isEmpty()) Text("Search", color = Color.Gray)
                            it()
                        }
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // 🔹 List
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(
                        friends.filter {
                            it.name.contains(searchText, true)
                        }
                    ) { friend ->

                        val isSelected = selectedFriends.contains(friend)

                        FriendItem2(
                            friend,
                            isSelected
                        ) {
                            if (it) selectedFriends.add(friend)
                            else selectedFriends.remove(friend)
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(52.dp)
                            .clip(RoundedCornerShape(26.dp))
                            .background(Color(0xFFE6EEF5)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Cancel", color = Color(0xFF5B8DBA))
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(52.dp)
                            .clip(RoundedCornerShape(26.dp))
                            .background(
                                Brush.horizontalGradient(
                                    listOf(Color(0xFF4FC3F7), Color(0xFF0288D1))
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Add", color = Color.White)
                    }
                }
            }
        }
    }
}

data class Friend2(
    val name: String,
    val phone: String,
    val image: Int
)

@Composable
fun FriendItem2(
    friend: Friend2,
    isSelected: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCheckedChange(!isSelected) }
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
                color = Color(0xFF2C2D3A),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = friend.phone,
                color = Color(0xFF9CA3AF),
                fontSize = 13.sp
            )
        }

        Checkbox(
            checked = isSelected,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = Color(0xFF2F80ED)
            )
        )
    }
}