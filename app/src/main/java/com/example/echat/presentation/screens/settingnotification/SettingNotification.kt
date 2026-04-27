package com.example.echat.presentation.screens.settingnotification


import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.echat.R
import com.example.echat.presentation.navigation.Screens
import kotlinx.coroutines.delay

@Composable
fun SettingNotification(
    navController: NavController
) {

    var showDialog by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(Unit) {

        delay(2000)

        showDialog = false

        navController.navigate(Screens.Home.route) {
            popUpTo(Screens.SettingNotification.route) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }

    if (showDialog) {
        CongratulationsDialog(
            onDismiss = {
                showDialog = false

                navController.navigate(Screens.Home.route) {
                    popUpTo(Screens.SettingNotification.route) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
        )
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
                        animation = tween (
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


