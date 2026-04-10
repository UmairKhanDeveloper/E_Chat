package com.example.echat.presentation.screens.onboarding

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.echat.R
import kotlinx.coroutines.launch
import kotlin.text.compareTo

@Composable
fun Onboarding(navController: NavController) {

    val items = listOf(
        OnboardingItem(R.drawable.onboarding_1, "Group Chatting", "Connect with multiple members in group chats."),
        OnboardingItem(R.drawable.onboarding_2, "Video and Voice Calls", "Instantly connect via video and voice calls."),
        OnboardingItem(R.drawable.onboarding_3, "Message Encryption", "Ensure privacy with encrypted messages."),
        OnboardingItem(R.drawable.onboarding_4, "Cross-Platform Compatibility", "Access chats on any device seamlessly.")
    )

    val pagerState = rememberPagerState(pageCount = { items.size })
    val scope = rememberCoroutineScope()

    val currentPage = pagerState.currentPage

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { page ->

                val item = items[page]
                val isVisible = currentPage == page
                val imageScale by animateFloatAsState(
                    targetValue = if (isVisible) 1f else 0.8f,
                    animationSpec = tween(600, easing = FastOutSlowInEasing),
                    label = ""
                )

                val imageAlpha by animateFloatAsState(
                    targetValue = if (isVisible) 1f else 0f,
                    animationSpec = tween(600),
                    label = ""
                )

                val titleOffset by animateDpAsState(
                    targetValue = if (isVisible) 0.dp else 30.dp,
                    animationSpec = tween(700, easing = FastOutSlowInEasing),
                    label = ""
                )

                val titleAlpha by animateFloatAsState(
                    targetValue = if (isVisible) 1f else 0f,
                    animationSpec = tween(700),
                    label = ""
                )

                val desAlpha by animateFloatAsState(
                    targetValue = if (isVisible) 1f else 0f,
                    animationSpec = tween(900),
                    label = ""
                )

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Spacer(modifier = Modifier.height(60.dp))

                    Image(
                        painter = painterResource(id = item.icon),
                        contentDescription = null,
                        modifier = Modifier
                            .size(220.dp)
                            .scale(imageScale)
                            .alpha(imageAlpha)
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    Text(
                        text = item.title,
                        fontSize = 24.sp,
                        color = Color(0xFF1565C0),
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = titleOffset)
                            .alpha(titleAlpha),
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.robot_black)),
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = item.des,
                        fontSize = 15.sp,
                        color = Color(0xFF1565C0),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .padding(horizontal = 20.dp)
                            .alpha(desAlpha)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (pagerState.currentPage == items.lastIndex) {
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    contentPadding = PaddingValues(),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                brush = Brush.horizontalGradient(
                                    listOf(Color(0xFF40C4FF), Color(0xFF03A9F4))
                                ),
                                shape = RoundedCornerShape(50)
                            )
                            .padding(vertical = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Get started", color = Color.White, fontSize = 18.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                TextButton(
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(items.lastIndex)
                        }
                    }
                ) {
                    Text("Skip", color = Color(0xFF3AB2E8))
                }

                Row {
                    repeat(items.size) { index ->

                        val isSelected = pagerState.currentPage == index

                        val size by animateDpAsState(
                            if (isSelected) 10.dp else 8.dp,
                            label = ""
                        )

                        val color by animateColorAsState(
                            if (isSelected) Color(0xFF29B6F6) else Color(0xFF7FD7FF),
                            label = ""
                        )

                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .size(size)
                                .background(color, CircleShape)
                        )
                    }
                }

                TextButton(
                    onClick = {
                        scope.launch {
                            val next = pagerState.currentPage + 1
                            if (next < items.size) {
                                pagerState.animateScrollToPage(next)
                            }
                        }
                    },
                    shape = CircleShape,
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color(0xFF7FD7FF),
                        contentColor = Color(0xFF0277BD)
                    ),
                    modifier = Modifier.size(60.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = "Next",
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

data class OnboardingItem(
    val icon: Int,
    val title: String,
    val des: String
)