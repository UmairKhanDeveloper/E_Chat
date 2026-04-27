package com.example.echat.presentation.screens.registerDetails.userinformation

import PrefManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.echat.R
import com.example.echat.googlefibase.GoogleAuthUiClient
import com.example.echat.presentation.navigation.Screens
import kotlinx.coroutines.delay

@Composable
fun UserInformation(navController: NavController) {
    val context = LocalContext.current
    val googleAuthUiClient = remember {
        GoogleAuthUiClient(context)
    }

    val userData = googleAuthUiClient.getSignedInUser()
    val titleFont = FontFamily(Font(R.font.robot_black))
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val profileImageUrl = userData?.profilePictureUrl
        ?.replace("s96-c", "s400-c")
        ?.replace("s100-c", "s400-c")

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
                horizontalArrangement = Arrangement.spacedBy(30.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {


                Button(
                    onClick = { navController.navigate(Screens.Login.route) },
                    modifier = Modifier
                        .height(48.dp)
                        .width(48.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    ),
                    contentPadding = PaddingValues(0.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 4.dp
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_left),
                        contentDescription = "Back",
                        tint = Color(0xFF2196F3),
                        modifier = Modifier.size(22.dp)
                    )
                }

                Text(
                    text = "User Profile",
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
                                model = profileImageUrl,
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
    val context = LocalContext.current
    val googleAuthUiClient = remember { GoogleAuthUiClient(navController.context) }
    val userData = googleAuthUiClient.getSignedInUser()

    var userName by remember { mutableStateOf(userData?.username ?: "") }
    val prefManager = remember { PrefManager(context) }

    val isEnabled = userName.isNotEmpty()

    var showDialog by remember {
        mutableStateOf(false)
    }

    if (showDialog) {

        LaunchedEffect(Unit) {

            delay(3000)

            showDialog = false

            navController.navigate(Screens.Home.route) {
                popUpTo(Screens.UserInformation.route) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        }

        CongratulationsDialog(
            onDismiss = {
                showDialog = false

                navController.navigate(Screens.Home.route) {
                    popUpTo(Screens.UserInformation.route) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
        )
    }


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
                    contentDescription = null,
                    tint = Color(0xFFCBD5F5)
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            BasicTextField(
                value = userName,
                onValueChange = {
                    userName = it
                },
                singleLine = true,
                modifier = Modifier.weight(1f),
                decorationBox = { innerTextField ->

                    if (userName.isEmpty()) {
                        Text("Your Name")
                    }

                    innerTextField()
                }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Divider(
            color = if (isEnabled)
                Color(0xFF40C4FF)
            else
                Color(0xFF334155)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Arrow Button
        Box(
            modifier = Modifier
                .align(Alignment.End)
                .size(60.dp)
                .clip(CircleShape)
                .background(
                    Brush.linearGradient(
                        colors = if (isEnabled) {
                            listOf(
                                Color(0xFF40C4FF),
                                Color(0xFF03A9F4)
                            )
                        } else {
                            listOf(
                                Color(0xFF9ED9F6),
                                Color(0xFF7CC4E8)
                            )
                        }
                    )
                )
                .clickable(enabled = isEnabled) {

                    val prefManager = PrefManager(navController.context)
                    prefManager.setFirstTime(false)
                    prefManager.setRegistered(true)

                    showDialog = true
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


@Composable
fun CongratulationsDialog(
    onDismiss: () -> Unit
) {

    Dialog(
        onDismissRequest = { }
    ) {

        Box(
            modifier = Modifier
                .width(330.dp)
                .wrapContentHeight()
                .background(
                    Color.White,
                    shape = RoundedCornerShape(18.dp)
                )
                .padding(
                    horizontal = 22.dp,
                    vertical = 24.dp
                )
        ) {

            IconButton(
                onClick = {
                    onDismiss()
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color(0xFFC5C5C5)
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Congratulations!",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2B1D0E)
                )

                Spacer(modifier = Modifier.height(30.dp))

                Image(
                    painter = painterResource(id = R.drawable.success_people),
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                )

                Spacer(modifier = Modifier.height(28.dp))

                Text(
                    text = "Your account is now ready to use.",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "You will be redirected to the Home\npage shortly.",
                    fontSize = 18.sp,
                    color = Color(0xFF7A7A7A),
                    textAlign = TextAlign.Center,
                    lineHeight = 26.sp
                )

                Spacer(modifier = Modifier.height(40.dp))

                val infiniteTransition = rememberInfiniteTransition(label = "")

                val angle by infiniteTransition.animateFloat(
                    initialValue = 0f,
                    targetValue = 360f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(
                            durationMillis = 1200,
                            easing = LinearEasing
                        ),
                        repeatMode = RepeatMode.Restart
                    ),
                    label = ""
                )

                Image(
                    painter = painterResource(id = R.drawable.loading),
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .rotate(angle)
                )

                Spacer(modifier = Modifier.height(18.dp))
            }
        }
    }
}




