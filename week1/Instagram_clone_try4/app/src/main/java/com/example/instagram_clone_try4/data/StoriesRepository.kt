package com.example.instagram_clone_try4.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

object StoriesRepository {
    private val stories=mutableStateOf<List<Story>>(emptyList())

    private fun populateStories(){//샘플데이터 10개 생성
        val _stories=ArrayList<Story>()
        _stories.add(
            Story(
                image= currentUser.image,
                name="Your Story"
            )
        )

        (0 .. 9).forEach{ index->
            val story=Story(
                image="https://randomuser.me/api/portraits/men/${index + 1}.jpg",
                name=names[index]
            )
            _stories.add(story)
        }
        stories.value=_stories
    }

    init{//시작하자자마 샘플 10개 생성
        populateStories()
    }

    fun observeStories(): MutableState<List<Story>> = stories// 저장돼있는 스토리 mutableState로 반환
}