package com.example.instagram_clone_try3.data

import com.example.instagram_clone_try3.InstaApplication

object StringProvider {//싱글턴
    fun getString(
        id: Int
    ): String{
        return InstaApplication.applicationContext().
                createConfigurationContext(InstaApplication.applicationContext()
                    .resources.configuration).getString(id)
    }
}