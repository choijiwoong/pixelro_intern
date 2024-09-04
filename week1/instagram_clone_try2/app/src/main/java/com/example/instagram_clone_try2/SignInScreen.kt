package com.example.instagram_clone_try2

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignInScreen(
    signInViewModel: SignInViewModel= hiltViewModel()
){
    val email=signInViewModel.email.collectAsState().value
    val password=signInViewModel.password.collectAsState().value

    Box() {
        Column(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxSize()
                .background(color = Color.Black),
        ) {
            Image(
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp),
                painter = painterResource(id = R.drawable.ic_instagram_logo_2),
                contentDescription = ""
            )

            //아이디 입력
            BasicTextField(
                modifier = Modifier.width(500.dp),
                value = email,
                onValueChange = { newText ->//입력값이 변경될 때 마다 VM의 email값을 업데이트 한다.
                    signInViewModel.updateEmail(newText)
                },
                textStyle = TextStyle(
                    fontSize = 30.sp
                ),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .width(500.dp)
                            .height(60.dp)
                            .border(
                                border = BorderStroke(
                                    width = 1.dp,
                                    color = Color(0xffc3c3c3)
                                ),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(start = 20.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (email.isEmpty()) {//이메일 상태값이 비어있다면(수시로 변경이 업데이트)
                            Text(
//재촉 문자 표시
                                text = "이메일을 입력해주세요",
                                fontSize = 24.sp,
                                color = Color.LightGray,
                            )
                        }
                        innerTextField()//키보드 커서를 노출시킨다.
                    }
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next//다음 입력 필드로 포커스 이동
                )
            )

            Spacer(//누가봐도 그냥 여백. 가로 채우고 높이 10 여백
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
            )

            //비밀번호
        }
    }
}