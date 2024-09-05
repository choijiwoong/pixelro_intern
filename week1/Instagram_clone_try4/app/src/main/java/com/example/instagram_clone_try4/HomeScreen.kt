package com.example.instagram_clone_try4

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.instagram_clone_try4.data.Post
import com.example.instagram_clone_try4.data.PostsRepository
import com.example.instagram_clone_try4.data.StoriesRepository
import com.example.instagram_clone_try4.data.Story
import com.example.instagram_clone_try4.ui.PostView
import com.example.instagram_clone_try4.ui.StoryImage
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(name: String){
    val coroutineScope = rememberCoroutineScope()//코루틴을 사용하기 위한 범위 생성

    Scaffold(
        topBar = { Toolbar() }) { innerPadding ->
        val posts by PostsRepository.posts
        val stories by StoriesRepository.observeStories()

        LazyColumn (
            contentPadding = innerPadding
        ){//지연하여 가져오는(통째로x) 열들
            item {//항목으로는 
                StoriesSection(stories)//스토리 영역과
                Divider()//구분영역을 가진다
            }
            itemsIndexed(posts) { _, post ->//다음 항목으론 Post가 있는데 각 포스트별로
                Post(post,//화면에 표시하며
                    onDoubleClick = {//더블클릭하면 코루틴으로 좋아요 처리를
                        coroutineScope.launch {
                            PostsRepository.performLike(post.id)
                        }
                    },
                    onLikeToggle = {//직접 좋아요를 누르면 코루틴으로 좋아요 처리를 수행한다. 두개의 처리를 다르게
                        coroutineScope.launch {
                            PostsRepository.toggleLike(post.id)
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun Toolbar() {//맨 상단에 인스타 아이콘과 디엠이 있을 툴바
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.padding(6.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                ImageVector.vectorResource(id = R.drawable.ic_instagram),//상단에 인스타그램 아이콘부터 박고
                contentDescription = ""
            )
        }
        Icon(
            ImageBitmap.imageResource(id = R.drawable.ic_dm),//디엠 사진도 박는다
            modifier = Modifier,
            contentDescription = ""
        )
    }
}

@Composable
private fun StoriesSection(stories: List<Story>) {
    Column {
        StoriesList(stories)// 맨 처음에 리포지토리에서 가져온 스토리들을 꾸밈을 위해 스토리 리스트에 넣는다.
        Spacer(modifier = Modifier.height(10.dp))//하단 패딩
    }
}

@Composable
private fun StoriesList(stories: List<Story>) {//스토리들을 가지런히 보이게 할 리스트 ui
    LazyRow {//지연 row형식으로 
        itemsIndexed(stories) { index, story ->//각 스토리들을 index와 함께 가져와서

            if (index == 0) {//맨앞 스토리면 padding한번 주고 시작
                Spacer(modifier = Modifier.width(6.dp))
            }

            Column(//세로로 사진과 여백과 아이디가 떠야하나 column
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(vertical = 5.dp, horizontal = 6.dp)
            ) {
                StoryImage(imageUrl = story.image)//별도 디자인 처리된 스토리 이미지를 통해 디자인
                Spacer(modifier = Modifier.height(5.dp))//패딩
                Text(story.name, style = androidx.compose.material3.MaterialTheme.typography.labelSmall)//androidx.compose.material.MaterialTheme.typography.caption)
                //닉네임 출력
            }

            if (index == stories.size.minus(1)) {//만약 마지막 스토리에 다다랐다면
                Spacer(modifier = Modifier.width(6.dp))//패딩
            }
        }
    }
}

@Composable
private fun Post(
    post: Post,
    onDoubleClick: (Post)->Unit,
    onLikeToggle: (Post)->Unit
){
    PostView(post, onDoubleClick, onLikeToggle)
}