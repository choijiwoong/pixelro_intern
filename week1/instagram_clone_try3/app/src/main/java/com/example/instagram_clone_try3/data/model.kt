package com.example.instagram_clone_try3.data

data class Post(
    val id: Int,
    val image: String,
    val user: User,
    val isLiked: Boolean = false,
    val likesCount: Int,
    val commentsCount: Int,
    val timeStamp: Long
)

data class Story(
    val image: String,
    val name: String,
    val isSeen: Boolean = false
)

val names = arrayOf(
    "Shivam",
    "Anuj",
    "Samson",
    "Prajwal",
    "Arijit",
    "Rajiv",
    "Suraj",
    "David",
    "Tushar",
    "Jyotirmoy",
    "Adam", "Alex", "Aaron", "Ben", "Carl", "Dan", "David", "Edward", "Fred", "Frank", "George", "Hal", "Hank", "Ike", "John", "Jack", "Joe", "Larry", "Monte", "Matthew", "Mark", "Nathan", "Otto", "Paul", "Peter", "Roger", "Roger", "Steve"
)