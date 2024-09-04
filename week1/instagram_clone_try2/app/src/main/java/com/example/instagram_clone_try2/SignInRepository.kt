package com.example.instagram_clone_try2

import com.google.android.gms.common.api.Response
import javax.sql.DataSource

class SignInRepository (
    private val remoteDataSource: DataSource,
    private val sharedPreferencesDataSource: DataSource
){
    suspend fun getSignInResult(email: String, pw: String){

    }

    suspend fun updateLocationId(locationId: Int){

    }

    suspend fun updateScreenSaverVideoURI(uri: String){

    }

    suspend fun signOut(){

    }
}