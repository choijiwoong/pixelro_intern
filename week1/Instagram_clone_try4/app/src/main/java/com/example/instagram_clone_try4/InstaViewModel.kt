package com.example.instagram_clone_try4

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class InstaViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {
    //Sign in
    private val _isSignedIn= MutableStateFlow(false)
    val isSignedIn: StateFlow<Boolean> = _isSignedIn

    fun updateIsSignedIn(isSignedIn: Boolean){
        _isSignedIn.update { isSignedIn }
    }
}