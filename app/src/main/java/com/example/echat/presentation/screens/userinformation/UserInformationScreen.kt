package com.example.echat.presentation.screens.userinformation

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.echat.R
import com.example.echat.presentation.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserInformationScreen(navController: NavController) {
    Scaffold(containerColor = Color(0xFFFFFFFF), topBar = {
        TopAppBar(title = {

        }, navigationIcon = {
            Box(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .clickable {
                        navController.popBackStack()
                    }
                    .size(40.dp)
                    .shadow(
                        elevation = 2.dp,
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
                            elevation = 2.dp,
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
                            elevation = 2.dp,
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
        LazyColumn(
            modifier = Modifier
                .padding(
                    top = it.calculateTopPadding(),
                    bottom = it.calculateBottomPadding()
                )
                .fillMaxSize()
                .background(Color(0xFFFFFFFF)), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Image(
                    painter = painterResource(id = R.drawable.imageuser),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(top = 32.dp, bottom = 24.dp)
                        .size(160.dp)
                )

            }
            item {
                Text(
                    text = "David Wayne",
                    fontSize = 22.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(16.dp)
                )
            }
            item {
                Row(
                    modifier = Modifier.padding(bottom = 39.dp),
                    horizontalArrangement = Arrangement.spacedBy(32.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "(+44)  20 1234 5689",
                        color = Color.Black,
                        fontSize = 18.sp,
                    )

                    Image(painter = painterResource(id = R.drawable.copy), contentDescription = "")
                }
            }

            item {
                Divider(
                    thickness = 1.dp, color = Color(0xFFe3e3e6),
                    modifier = Modifier.padding(
                        start = 24.dp, end = 24.dp, bottom = 8.dp
                    )
                )
            }

            item {
                ChatSettingsScreen(navController)
            }
            item {
                UserItem(icon = R.drawable.danger_circle, "Report")
            }
            item {
                UserItem(icon = R.drawable.forbidden_circle, "Report")
            }


        }

    }

}

@Composable
fun ChatSettingsScreen(navController: NavController) {

    val switches = remember {
        mutableStateMapOf(
            "mute" to false,
            "protected" to false,
            "hideChat" to false,
            "hideHistory" to false
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 12.dp)
    ) {

        SettingsItem(
            title = "Media, Links & Documents",
            icon = R.drawable.gallery,
            trailingText = "152",
            showArrow = true,
            onClickable = { navController.navigate(Screens.ChatsUserInformationMedia.route) }
        )

        SettingsSwitchItem(
            title = "Mute Notification",
            icon = R.drawable.volume_loud,
            checked = switches["mute"] ?: false,
            onCheckedChange = { switches["mute"] = it },
            onClickable = {},
            navController = navController
        )

        SettingsItem(
            title = "Custom Notification",
            icon = R.drawable.bell,
            showArrow = true,
            onClickable = {}

        )

        SettingsSwitchItem(
            title = "Protected Chat",
            icon = R.drawable.shield_user,
            checked = switches["protected"] ?: false,
            onCheckedChange = { switches["protected"] = it },
            showArrow = true,
            onClickable = { navController.navigate(Screens.ProtectedChat.route) },
            navController = navController

        )

        SettingsSwitchItem(
            title = "Hide Chat",
            icon = R.drawable.eye,
            checked = switches["hideChat"] ?: false,
            onCheckedChange = { switches["hideChat"] = it },
            showArrow = true,
            onClickable = {},
            navController = navController
        )

        SettingsSwitchItem(
            title = "Hide Chat History",
            icon = R.drawable.eye,
            checked = switches["hideHistory"] ?: false,
            onCheckedChange = { switches["hideHistory"] = it },
            showArrow = true,
            onClickable = {},
            navController = navController
        )

        SettingsItem(
            title = "Add To Group",
            icon = R.drawable.users_group_two_rounded,
            showArrow = true,
            onClickable = { navController.navigate(Screens.AddToGroups.route) }

        )

        ColorItem(
            title = "Custom Color Chat", icon = R.drawable.pallete_2,
            color = Color(0xFF1976D2)
        )

        ColorItem(
            title = "Custom Background Chat", icon = R.drawable.gallery_edit,
            color = Color.LightGray
        )
    }
}

@Composable
fun SettingsItem(
    title: String,
    icon: Int,
    trailingText: String? = null,
    showArrow: Boolean = false,
    onClickable: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClickable() }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .size(22.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = title,
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )

        trailingText?.let {
            Text(text = it, color = Color.Gray)
        }

        if (showArrow) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}

@Composable
fun SettingsSwitchItem(
    title: String,
    icon: Int,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    showArrow: Boolean = false,
    onClickable: () -> Unit,
    navController: NavController

) {
    Row(
        modifier = Modifier
            .clickable { onClickable() }
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(22.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = title,
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,

            modifier = Modifier.scale(0.9f),

            colors = SwitchDefaults.colors(

                checkedThumbColor = Color.White,
                checkedTrackColor = Color(0xFF00B8FF),

                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Color(0xFFD9D9D9),

                checkedBorderColor = Color(0xFF00B8FF),
                uncheckedBorderColor = Color.Transparent
            )
        )

        if (showArrow) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}

@Composable
fun ColorItem(
    title: String,
    icon: Int,
    color: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(22.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = title,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )

        Box(
            modifier = Modifier
                .size(24.dp)
                .background(color, RoundedCornerShape(4.dp))
        )
    }
}


@Composable
fun UserItem(icon: Int, title: String) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(22.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = title,
            color = Color(0xFFF44336),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
    }


}