package com.example.instagram_clone_try4

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.instagram_clone_try4.data.StringProvider

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignInScreen(
    updateIsSignedIn: (Boolean) -> Unit,
    signInViewModel: SignInViewModel= hiltViewModel()
){
    val email=signInViewModel.email.collectAsState().value
    val password=signInViewModel.password.collectAsState().value

    Box() {
        Column(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxSize()
                .background(color = Color.Black)
                .padding(36.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(100.dp))

            Box(
                modifier=Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Image(
                    modifier = Modifier
                        .width(200.dp)
                        .height(50.dp),
                    painter = painterResource(id = R.drawable.ic_instagram_logo_2),
                    contentDescription = "",
                    )
            }

            //이메일 입력
            BasicTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = { newText ->//입력값이 변경될 때 마다 VM의 email값을 업데이트 한다.
                    signInViewModel.updateEmail(newText)
                },
                textStyle = TextStyle(
                    fontSize = 30.sp,
                    color = Color.White
                ),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
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
                                text = "Email",
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

            Spacer(modifier = Modifier.height(10.dp))

            //비밀번호
            BasicTextField(
                modifier = Modifier.fillMaxWidth(),
                value=password,
                onValueChange={ newText->
                    signInViewModel.updatePassword(newText)
                },
                visualTransformation = PasswordVisualTransformation(),
                textStyle=TextStyle(
                    fontSize=30.sp,
                    color = Color.White
                ),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
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
                    ){
                        if (password.isEmpty()) {//이메일 상태값이 비어있다면(수시로 변경이 업데이트)
                            Text(
                                //재촉 문자 표시
                                text = "Password",
                                fontSize = 24.sp,
                                color = Color.LightGray,
                            )
                        }
                        innerTextField()//키보드 커서를 노출시킨다.
                    }
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            //비밀번호 잊었니 글자
            Box(
                modifier= Modifier
                    .fillMaxWidth()
                    .clickable {

                    }
            ) {
                Text(
                    text="Forgot password?",
                    modifier=Modifier.align(Alignment.CenterEnd),
                    color = Color(0xffffffff),
                    style= TextStyle(
                        textDecoration = TextDecoration.Underline
                    )
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 로그인 버튼
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = Color(0xff3399ff), shape = RoundedCornerShape(8.dp))
                    .clickable {
                        if (!signInViewModel.checkIsTextFilled())
                            return@clickable
                        signInViewModel.signIn(
                            updateIsSignedIn = updateIsSignedIn
                        )
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = StringProvider.getString(R.string.signin),
                    fontSize = 20.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            // 페북 로그인 글자
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_facebook),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = "Log in with Facebook",
                    color = Color(0xff1e90ff),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold
                    )
                )
            }


            Spacer(modifier = Modifier.height(30.dp))


            //or 구분 글자
            Text(
                text = "OR",
                color = Color.White,
            )

            Spacer(modifier = Modifier.height(30.dp))

            //회원가입 글자
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Don't have an account?",
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))

                Box(
                    modifier = Modifier
                        .clickable {
                            // 로그인 버튼 클릭 시 동작
                        },
                ) {
                    Text(
                        text = "Sign Up",
                        color = Color.White,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}