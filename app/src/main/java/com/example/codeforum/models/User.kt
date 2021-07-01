package com.example.codeforum.models

data class User(
    val userId: String = "",
    val userDisplayName: String = "",
    val imageURL: Int = 0,
    val userDescription: String = "",
    val numberOfPosts: Int = 0,
    val followers: String = "0"
)
