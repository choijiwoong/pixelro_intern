package com.example.instagram_clone_try4

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.instagram_clone_try4.data.Constants
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun InstaApp(
    viewModel: InstaViewModel = hiltViewModel(),
    navController: NavHostController= rememberNavController()
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Constants.ROUTE_SIGN_IN,
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxSize()
    ){
        composable(Constants.ROUTE_SIGN_IN){
            SignInScreen(updateIsSignedIn = {
                viewModel.updateIsSignedIn(it)
                navController.navigate(Constants.ROUTE_HOME)
            })
        }

        composable(Constants.ROUTE_HOME){
            HomeScreen(name = "Hello World!")
        }
    }
}