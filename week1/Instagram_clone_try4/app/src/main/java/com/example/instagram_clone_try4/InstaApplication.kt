package com.example.instagram_clone_try4

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import android.content.Context

@HiltAndroidApp
class InstaApplication: Application() {
    init{
        instance = this//인스턴스로 사용할 항목을 현재 객체로 지정
    }

    companion object{
        lateinit var instance: InstaApplication// 늦은 초기화 즉 이 앱이 실행되면 초기화시킨다.
        fun applicationContext(): Context{
            return instance.applicationContext//이 인스턴스의 context를 반환시킨다.
        }
    }
}