package com.example.instagram_clone_try3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.instagram_clone_try3.ui.theme.Instagram_clone_try3Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Instagram_clone_try3Theme {
                SignInScreen()
            }
        }
    }
}