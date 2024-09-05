package com.example.instagram_clone_try4.data

data class User(
    var name: String,
    var username: String,
    var image: String
)

var currentUser = User(
    name = "Shivam Mishra",
    username = "mshivam019",
    image = "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__480.png"
)