package com.example.instagram_clone_try4.data

import com.example.instagram_clone_try4.InstaApplication

object StringProvider {//싱글턴
    fun getString(
        id: Int
    ): String{
        return InstaApplication.applicationContext().
                createConfigurationContext(
                    InstaApplication.applicationContext()
                    .resources.configuration).getString(id)
    }
}