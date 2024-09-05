package com.example.instagram_clone_try3

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(name: String){
    Surface(color = MaterialTheme.colorScheme.primary) {
        Text (text = "Hello $name!")
    }
}