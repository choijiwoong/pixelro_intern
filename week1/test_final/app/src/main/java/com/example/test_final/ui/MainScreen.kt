package com.example.test_final.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.test_final.R

@ExperimentalFoundationApi
@Composable
fun MainScreen {
    val sectionState = remember { mutableStateOf(HomeSection.Home) }

    val navItems=HomeSection.values()
        .toList()
    Scaffold (
        bottomBar= {
            BottomBar(
                items = navItems,
                currentSection = sectionState.value,
                onSectionSelected = { sectionState.value = it },
            )
        }) { innerPadding ->
        val modifier= Modifier.padding(innerPadding)
        Crossfade(
            modifier=modifier,
            targetState=sectionState.value)
        { section ->
            when (section){
                Home -> Home()
                Reels -> Reels()
                Add -> Content(title="Add Post options")
                Favorite -> Content(title="Profile")
            }
        }
    }
}

@Composable
private fun Content(title: String){
    Box(
        modifier=Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text=title,
            textAlign= TextAlign.Center,
            style=MaterialTheme.typography.headlineSmall
        )
    }
}
@Composable
private fun BottomBar(
    items: List<HomeSection>,
    currntSection: HomeSection,
    onSectionSelected: (HomeSection) -> Uint,
){
    BottomNavigation(
        modifier=Modifier.height(bottomBarHeight),
        backgroundColor=MeterialTheme.
    )
}

private enum class HomeSection(//하단 바 이미지
    val icon: Int,
    val selectedIcon: Int
) {
    Home(R.drawable.ic_outlined_home, R.drawable.ic_filled_home),
    Reels(R.drawable.ic_outlined_reels, R.drawable.ic_filled_reels),
    Add(R.drawable.ic_outlined_add, R.drawable.ic_outlined_add),
    Favorite(R.drawable.ic_outlined_favorite, R.drawable.ic_filled_favorite),
    Profile(R.drawable.ic_outlined_reels, R.drawable.ic_outlined_reels)
}