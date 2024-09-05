package com.example.instagram_clone_try4.ui

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.instagram_clone_try4.R
import com.example.instagram_clone_try4.common.component.AnimLikeButton
import com.example.instagram_clone_try4.common.component.PostIconButton
import com.example.instagram_clone_try4.common.horizontalPadding
import com.example.instagram_clone_try4.common.verticalPadding
import com.example.instagram_clone_try4.data.Post

@Composable
fun PostView (//저장된 샘플 포스트를 가시화하는 뷰
    post: Post,
    onDoubleClick: (Post)->Unit,
    onLikeToggle: (Post)->Unit
) {
    Column {//포스트는 세로기에
        PostHeader(post)//이름이랑 팔로우랑 나오는 헤더
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(color = Color.LightGray)
        ) {
            Image(//포스트 이미지
                painter = rememberImagePainter(post.image),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
//            DoubleTapPhotoLikeAnimation(
//                onDoubleTap = {
//                    onDoubleClick.invoke(post)
//                }
//            )
        }
        PostFooter(post, onLikeToggle)//포스트 밑의 좋아요 댓글 등등
        Divider()//구분자(Metarial3)
    }
}

@Composable
private fun PostHeader(post: Post) {
    Row(//항목들은 가로로 나열
        modifier = Modifier
            .fillMaxWidth()
            .padding(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(color = Color.LightGray, shape = CircleShape)
                    .clip(CircleShape)
            ) {
                Image(//프로필 이미지 그리기
                    painter = rememberImagePainter(post.user.image),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.width(10.dp))//사회적 거리두고
            Text(text = post.user.username, style = MaterialTheme.typography.titleMedium)//유저 아이디 출력
        }
        Icon(Filled.MoreVert, "")//??
    }
}

@Composable
private fun PostFooter(
    post: Post,
    onLikeToggle: (Post)->Unit
) {
    PostFooterIconSection(post, onLikeToggle)//좋아요 댓글 디엠 등등 있는 아이콘 들
    PostFooterTextSection(post)// 댓글보기 댓글달기 같은 글자 들
}

@Composable
private fun PostFooterIconSection(//포스트 하단에 라이크 퍼레이드
    post: Post,
    onLikeToggle: (Post) -> Unit// 라이크 토글로 눌렀을 때 callback
) {
    Row(//가로로 배열
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(// 사실 이중 row쓰는 이유까진 모르겠음 좌우 정렬을 위한건가봄
            verticalAlignment = Alignment.CenterVertically
        ) {
            AnimLikeButton(post, onLikeToggle)

            PostIconButton {
                Icon(ImageBitmap.imageResource(id =  R.drawable.ic_outlined_comment), "")
            }

            PostIconButton {
                Icon(ImageBitmap.imageResource(id = R.drawable.ic_dm), "")
            }
        }

        PostIconButton {
            Icon(ImageVector.vectorResource(id = R.drawable.ic_outlined_bookmark), "")
        }
    }
}

@Composable
private fun PostFooterTextSection(post: Post) {
    Column(
        modifier = Modifier.padding(
            start = horizontalPadding,
            end = horizontalPadding,
            bottom = verticalPadding
        )
    ) {
        androidx.compose.material3.Text(
            "${post.likesCount} likes",
            style = MaterialTheme.typography.titleMedium
        )

        androidx.compose.material3.Text(
            "View all ${post.commentsCount} comments",
            style = MaterialTheme.typography.labelSmall
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            post.timeStamp.getTimeElapsedText(),
            style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp)
        )
    }
}

private fun Long.getTimeElapsedText(): String{
    return "ji"
}