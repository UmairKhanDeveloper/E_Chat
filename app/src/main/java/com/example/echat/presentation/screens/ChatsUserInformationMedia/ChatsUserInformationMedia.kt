package com.example.echat.presentation.screens.ChatsUserInformationMedia

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.echat.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatsUserInformationMedia(navController: NavController) {
    Scaffold(containerColor = Color(0xFFFFFFFF), topBar = {
        TopAppBar(title = {
            Text(
                text = "David Wayne",
                color = Color.Black,
                fontSize = 22.sp,
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
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                CustomTabBar()
            }

        }

    }

}

@Composable
fun CustomTabBar() {

    val tabs = listOf("Media", "Links", "Documents")
    var selectedTab by remember { mutableStateOf(0) }

    Column {
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = Color.White,
            contentColor = Color.Black,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[selectedTab]),
                    height = 2.dp,
                    color = Color.Black
                )
            },
            divider = {}
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = {
                        Text(
                            text = title,
                            fontSize = 14.sp,
                            fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal,
                            color = if (selectedTab == index) Color.Black else Color.Gray
                        )
                    }
                )
            }
        }

        when (selectedTab) {
            0 -> MediaScreen()
            1 -> LinksScreen()
            2 -> DocumentsScreen()
        }
    }
}

@Composable
fun MediaScreen() {

    val todayImages: List<Int> = listOf(
        R.drawable.media,
        R.drawable.media
    )

    val yesterdayImages: List<Int> = listOf(
        R.drawable.media,
        R.drawable.media,
        R.drawable.media,
        R.drawable.media
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Today",
            fontSize = 16.sp,
            color = Color(0xFF6B7280),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.height(140.dp)
        ) {
            items(todayImages) { image ->
                MediaItem(image)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Yesterday",
            fontSize = 16.sp,
            color = Color(0xFF6B7280),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.height(90.dp)
        ) {
            items(yesterdayImages) { image ->
                MediaItem(image)
            }
        }
    }
}

@Composable
fun LinksScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Links Content")
    }
}

@Composable
fun DocumentsScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Documents Content")
    }
}

@Composable
fun MediaItem(image: Int) {
    Image(
        painter = painterResource(id = image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(80.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(16.dp))
    )
}