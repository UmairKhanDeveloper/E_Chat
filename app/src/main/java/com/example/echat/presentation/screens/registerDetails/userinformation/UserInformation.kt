package com.example.echat.presentation.screens.registerDetails.userinformation

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.echat.R
import com.example.echat.googlefibase.GoogleAuthUiClient

@Composable
fun UserInformation(navController: NavController) {
    val context = LocalContext.current
    val googleAuthUiClient = remember {
        GoogleAuthUiClient(context)
    }

    val userData = googleAuthUiClient.getSignedInUser()
    val titleFont = FontFamily(Font(R.font.robot_black))
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }

    Box(modifier = Modifier.fillMaxSize()) {

        Column {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color(0xFF0aaaf5))
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color(0xFFedf9ff))
            )
        }


        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp, start = 28.dp, end = 28.dp, bottom = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {


                Button(
                    onClick = { },
                    modifier = Modifier
                        .height(50.dp)
                        .width(140.dp),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.arrow_left),
                            contentDescription = "Back",
                            tint = Color(0xFF2196F3),
                            modifier = Modifier.size(20.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "Login",
                            color = Color(0xFF2196F3),
                            fontSize = 16.sp
                        )
                    }
                }

                Text(
                    text = "Register",
                    fontFamily = titleFont,
                    color = Color.White,
                    fontSize = 34.sp, modifier = Modifier
                )
            }

            Box(
                modifier = Modifier
                    .padding(bottom = 140.dp)
                    .align(alignment = Alignment.CenterHorizontally)
                    .size(140.dp),
                contentAlignment = Alignment.Center
            ) {

                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .clip(CircleShape)
                        .background(Color(0xFF87D5FA)),
                    contentAlignment = Alignment.Center
                ) {
                    when {
                        selectedImageUri != null -> {
                            Image(
                                painter = rememberAsyncImagePainter(selectedImageUri),
                                contentDescription = "Selected Image",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }

                        !userData?.profilePictureUrl.isNullOrEmpty() -> {
                            AsyncImage(
                                model = userData?.profilePictureUrl,
                                contentDescription = "Google Image",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop,
                                placeholder = painterResource(R.drawable.user_circle),
                                error = painterResource(R.drawable.user_circle)
                            )
                        }

                        else -> {
                            Image(
                                painter = painterResource(id = R.drawable.user_circle),
                                contentDescription = "Profile",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }

                IconButton(
                    onClick = {
                        launcher.launch("image/*")
                    },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(x = (-6).dp, y = (-6).dp)
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF0F4888))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.pen),
                        contentDescription = "Edit",
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            ProfileItem(navController)


        }
    }

}

@Composable
fun ProfileItem(navController: NavController) {

    val googleAuthUiClient = remember { GoogleAuthUiClient(navController.context) }
    val userData = googleAuthUiClient.getSignedInUser()

    var userName by remember { mutableStateOf(userData?.username ?: "") }

    val isEnabled = userName.isNotEmpty()


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp, vertical = 12.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .background(Color(0xFF475569), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile",
                    tint = Color(0xFFCBD5F5),
                    modifier = Modifier.size(18.dp)
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            BasicTextField(
                value = userName,
                onValueChange = { userName = it },
                singleLine = true,
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp
                ),
                modifier = Modifier.weight(1f),
                decorationBox = { innerTextField ->

                    if (userName.isEmpty()) {
                        Text(
                            text = "Your Name",
                            color = Color(0xFF94A3B8),
                            fontSize = 16.sp
                        )
                    }

                    innerTextField()
                }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Divider(
            color = if (isEnabled) Color(0xFF40C4FF) else Color(0xFF334155),
            thickness = 1.dp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .align(Alignment.End)
                .size(60.dp)
                .clip(CircleShape)
                .background(
                    brush = Brush.linearGradient(
                        colors = if (isEnabled) {
                            listOf(Color(0xFF40C4FF), Color(0xFF03A9F4))
                        } else {
                            listOf(Color(0xFF9ED9F6), Color(0xFF7CC4E8))
                        }
                    )
                )
                .clickable(enabled = isEnabled) {
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}