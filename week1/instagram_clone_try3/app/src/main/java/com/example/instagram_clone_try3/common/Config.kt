package com.example.instagram_clone_try3.common

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

val verticalPadding = 12.dp//포스트 내부 아이콘 바 만들 때 사용할 패딩 변수들
val horizontalPadding = 10.dp
val bottomBarHeight = 50.dp

fun Modifier.icon() = this.size(24.dp)

fun Modifier.defaultPadding() = this.padding(
    horizontal = horizontalPadding,
    vertical = verticalPadding
)