package com.example.instagram_clone_try3.data

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object PostsRepository {//싱글턴
    private val _posts=mutableStateOf<List<Post>>(emptyList())
    val posts: State<List<Post>> = _posts

    private fun populatePosts() {//출력할 포스트 들로, 몇가지는 Model Post내부적으로 샘플 데이터를 저장해두었음
        val posts = ArrayList<Post>()//포스트를 저장하는 리스트
        (0..9).forEach { index ->//10개의 반복에 대해서 샘플 데이터를 생성하여 posts에 저장한다.
            val post = Post(
                id = index + 1,
                image = "https://source.unsplash.com/random/400x300?$index",
                user = User(
                    name = names[index],
                    username = names[index],
                    image = "https://randomuser.me/api/portraits/men/${index + 1}.jpg"
                ),
                likesCount = index + 100,
                commentsCount = index + 20,
                timeStamp = System.currentTimeMillis() - (index * 60000)
            )
            posts.add(post)
        }

        _posts.value = posts//값 갱신.
    }

    init {//생성과 동시에 10개의 샘플 데이터 생성
        populatePosts()
    }

    suspend fun toggleLike(postId: Int) {
        updateLike(postId, true)
    }

    suspend fun performLike(postId: Int) {
        updateLike(postId, false)
    }

    private suspend fun updateLike(
        postId: Int,
        isToggle: Boolean
    ) {
        withContext(Dispatchers.IO){
            val posts=_posts.value.toMutableList()//mutable state인 값을 가져와 list로

            for ((index, value) in posts.withIndex()){//인덱스를 포함하여 순회하는데
                if(value.id==postId){//like를 원하는 포스트를 찾았다면
                    //참고로 isLiked이나 likesCount등은 Post내부에 정의되어있음
                    val isLiked= if (isToggle) !value.isLiked else true//isToggle은 뭐지..?

                    if(isLiked != value.isLiked){
                        val likesCount=if (isLiked) value.likesCount.plus(1) else value.likesCount.minus(1)
                        posts[index]=value.copy(isLiked=isLiked, likesCount = likesCount)
                    }
                    break
                }
            }
            _posts.value=posts //변경시마다 상태값 업데이트 해줘야함
        }
    }
}