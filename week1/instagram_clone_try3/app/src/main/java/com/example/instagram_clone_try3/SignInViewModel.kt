package com.example.instagram_clone_try3

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.instagram_clone_try3.data.SignInRepository
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
    application: Application,
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
            val result=1//강제 로그인 성공을 위함
            if(result!=null){//로그인 성공
                withContext(Dispatchers.Main) {
                    updateIsSignedIn(true)
                }
            } else{//로그인 실패
                withContext(Dispatchers.Main) {//메인 디스패처(스레드 오케스트레이터)의 스레드를 이용, 임시 컨텍스트 스위칭 진행
                    Toast.makeText(getApplication(), "아이디와 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun checkIsTextFilled(): Boolean{
        if(_email.value == ""){
            Toast.makeText(getApplication(), "이메일을 입력해주세요", Toast.LENGTH_SHORT).show()
            return false
        }
        if(_password.value == ""){
            Toast.makeText(getApplication(), "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    fun signOut(){
        viewModelScope.launch {

        }
    }
}