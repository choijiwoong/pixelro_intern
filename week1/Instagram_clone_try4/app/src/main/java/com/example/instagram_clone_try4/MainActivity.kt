package com.example.instagram_clone_try4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.instagram_clone_try4.ui.theme.Instagram_clone_try4Theme
import com.example.instagram_clone_try4.InstaApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Instagram_clone_try4Theme {
                InstaApp()
            }
        }
    }
}