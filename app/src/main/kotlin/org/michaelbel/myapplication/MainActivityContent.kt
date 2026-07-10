package org.michaelbel.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.foundation.lazy.TvLazyColumn
import androidx.tv.foundation.lazy.TvLazyRow
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import androidx.tv.material3.WideButton
import androidx.tv.material3.WideCard
import androidx.tv.material3.rememberWideCardState

data class VideoItem(
    val id: Long,
    val title: String,
    val playCount: String,
    val duration: String = ""
)

private val mockVideos = listOf(
    VideoItem(1, "【独家】2024年度最佳动画混剪", "91w"),
    VideoItem(2, "原神新角色实战演示", "78w"),
    VideoItem(3, "AC娘带你逛漫展", "65w"),
    VideoItem(4, "科技前沿：AI绘画技术突破", "52w"),
    VideoItem(5, "游戏攻略：艾尔登法环全BOSS打法", "48w"),
    VideoItem(6, "搞笑配音合集", "42w"),
    VideoItem(7, "美食制作：米其林大厨教程", "38w"),
    VideoItem(8, "动漫推荐：十月新番盘点", "35w"),
    VideoItem(9, "音乐MV：原创歌曲首发", "32w")
)

private val categories = listOf("首页", "番剧", "动画", "游戏", "科技", "娱乐", "生活")

@Composable
fun MainActivityContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TvLazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(
                start = 24.dp,
                top = 80.dp,
                end = 24.dp,
                bottom = 24.dp
            )
        ) {
            item {
                TopHeader()
            }
            
            item {
                CategoryTabs()
            }
            
            item {
                SectionTitle(title = "推荐")
            }
            
            item {
                VideoGrid()
            }
            
            item {
                SectionTitle(title = "热门")
            }
            
            item {
                HorizontalVideoList(title = "热门动画")
            }
            
            item {
                HorizontalVideoList(title = "游戏直播")
            }
        }
    }
}

@Composable
fun TopHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "ACFUN",
                color = Color(0xFFFF2C55),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "TV",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal
            )
        }
        
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            HeaderButton(text = "搜索")
            HeaderButton(text = "登录")
            HeaderButton(text = "历史记录")
        }
    }
}

@Composable
fun HeaderButton(text: String) {
    WideButton(
        onClick = {},
        modifier = Modifier.padding(4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            color = Color.White
        )
    }
}

@Composable
fun CategoryTabs() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        categories.forEachIndexed { index, category ->
            WideButton(
                onClick = {},
                modifier = Modifier.padding(4.dp),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = category,
                    fontSize = 20.sp,
                    color = if (index == 0) Color(0xFFFF2C55) else Color.White
                )
            }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        color = Color.White,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
fun VideoGrid() {
    androidx.compose.foundation.lazy.LazyVerticalGrid(
        columns = androidx.compose.foundation.lazy.GridCells.Fixed(3),
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(mockVideos.size) { index ->
            VideoCard(video = mockVideos[index])
        }
    }
}

@Composable
fun VideoCard(video: VideoItem) {
    val cardState = rememberWideCardState()
    
    WideCard(
        onClick = {},
        modifier = Modifier
            .width(320.dp),
        state = cardState
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF555555))
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(8.dp)
                        .background(Color.Black.copy(alpha = 0.7f))
                        .padding(4.dp)
                ) {
                    Text(
                        text = video.duration.ifEmpty { "00:00" },
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = video.title,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 2,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = "播放量 ${video.playCount}",
                color = Color(0xFF999999),
                fontSize = 14.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

@Composable
fun HorizontalVideoList(title: String) {
    Column {
        SectionTitle(title = title)
        
        Spacer(modifier = Modifier.height(8.dp))
        
        TvLazyRow(
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(mockVideos.size) { index ->
                HorizontalVideoCard(video = mockVideos[index])
            }
        }
    }
}

@Composable
fun HorizontalVideoCard(video: VideoItem) {
    val cardState = rememberWideCardState()
    
    WideCard(
        onClick = {},
        modifier = Modifier
            .width(280.dp),
        state = cardState
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(157.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF555555))
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(8.dp)
                        .background(Color.Black.copy(alpha = 0.7f))
                        .padding(4.dp)
                ) {
                    Text(
                        text = video.duration.ifEmpty { "00:00" },
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = video.title,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 2,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = "播放量 ${video.playCount}",
                color = Color(0xFF999999),
                fontSize = 14.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}