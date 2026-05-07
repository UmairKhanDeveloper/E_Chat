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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileDownload
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
import androidx.compose.ui.text.style.TextOverflow
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

    val todayImages = listOf(
        R.drawable.media,
        R.drawable.media
    )

    val yesterdayImages = listOf(
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
            columns = GridCells.Fixed(4), // same like yesterday
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.height(90.dp)
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
            modifier = Modifier.height(180.dp)
        ) {
            items(yesterdayImages) { image ->
                MediaItem(image)
            }
        }
    }
}

@Composable
fun LinksScreen() {

    val todayLinks = listOf(
        LinkItem(
            R.drawable.linkimage,
            "160+ FREE Tab Bar Component Types",
            "https://www.figma.com/community/file/1312921033225014799"
        ),
        LinkItem(
            R.drawable.linkimage,
            "Speedy Chow | Food Delivery App UI Kit",
            "https://www.figma.com/community/file/136436831565295794"
        )
    )

    val yesterdayLinks = listOf(
        LinkItem(
            R.drawable.linkimage,
            "150+ FREE Stepper / Wizard Components",
            "https://www.figma.com/community/file/134403852380855664"
        ),
        LinkItem(
            R.drawable.linkimage,
            "100+ FREE Search Bar Component Types",
            "https://www.figma.com/community/file/1319887930637567259"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {


            Text(
                text = "Today",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF6B6B8A),
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                todayLinks.forEach {
                    LinkCard(item = it)
                }
            }



            Text(
                text = "Yesterday",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF6B6B8A),
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                yesterdayLinks.forEach {
                    LinkCard(item = it)
                }
            }

    }
}

@Composable
fun DocumentsScreen() {

    val documentSections = listOf(
        "Today" to listOf(
            DocumentItemData("doc", "Don Quixote", "24 MB", Color(0xFFFFA726)),
            DocumentItemData("cdr", "Design System", "1.5 GB", Color(0xFF64DD17)),
            DocumentItemData("pdf", "The Lord of the Rings", "20.8 GB", Color(0xFFFF1744)),
            DocumentItemData("pdf", "War and Peace", "14.2 GB", Color(0xFFFF1744)),
        ),

        "Yesterday" to listOf(
            DocumentItemData("psd", "Engineer Character", "100 GB", Color(0xFF536DFE)),
            DocumentItemData("psd", "Tank Character", "120 GB", Color(0xFF536DFE)),
        ),

        "Last Month" to listOf(
            DocumentItemData("zip", "The Odyssey by Homer", "22.1 GB", Color(0xFF9C4DFF)),
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF))
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {

        documentSections.forEach { section ->

            Text(
                text = section.first,
                color = Color(0xFF66678A),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(vertical = 12.dp)
            )

            section.second.forEach { document ->

                DocumentItem(document)

                Spacer(modifier = Modifier.height(14.dp))
            }
        }
    }
}

data class DocumentItemData(
    val type: String,
    val title: String,
    val size: String,
    val color: Color
)

@Composable
fun DocumentItem(item: DocumentItemData) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(Color(0xFFE9E9EC))
            .padding(horizontal = 14.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(width = 38.dp, height = 48.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(item.color),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = item.type,
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = item.title,
                color = Color(0xFF2C2C35),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = item.size,
                color = Color(0xFF8D8D99),
                fontSize = 14.sp
            )
        }

        Box(
            modifier = Modifier
                .size(50.dp)
                .shadow(
                    elevation = 6.dp,
                    shape = CircleShape,
                    ambientColor = Color.LightGray,
                    spotColor = Color.LightGray
                )
                .clip(CircleShape)
                .background(Color.White)
                .clickable { },
            contentAlignment = Alignment.Center
        ) {

            Icon(
                imageVector = Icons.Default.FileDownload,
                contentDescription = null,
                tint = Color(0xFF22B8FF),
                modifier = Modifier.size(26.dp)
            )
        }
    }
}



@Composable
fun MediaItem(image: Int) {
    Image(
        painter = painterResource(id = image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(RoundedCornerShape(16.dp))
    )
}

data class LinkItem(
    val image: Int,
    val title: String,
    val link: String
)


@Composable
fun LinkCard(item: LinkItem) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFFF0F0F3))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(id = item.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(10.dp))
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = item.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF000000),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = item.link,
                fontSize = 13.sp,
                color = Color(0xFF9A9BB1),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}