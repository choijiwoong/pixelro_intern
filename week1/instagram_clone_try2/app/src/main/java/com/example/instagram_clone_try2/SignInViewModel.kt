package com.example.instagram_clone_try2

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application){

    private val _email= MutableStateFlow("")
    val email: StateFlow<String> = _email
    private val _password=MutableStateFlow("")
    val password: StateFlow<String> = _password

    fun updateEmail(text: String){
        _email.update { text } //상태를 업데이트 한다.
    }
    fun updatePassword(text: String){
        _password.update { text }
    }

    fun signIn(
        updateIsSignedIn: (Boolean)->Unit
    ){
        viewModelScope.launch(Dispatchers.IO){
            val result=1
            if(result!=null){//로그인 성공
                updateIsSignedIn(true)
            } else{//로그인 실패
                withContext(Dispatchers.Main) {//메인 디스패처(스레드 오케스트레이터)의 스레드를 이용, 임시 컨텍스트 스위칭 진행
                    Toast.makeText(getApplication(), "아이디와 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}