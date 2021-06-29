package com.example.codeforum.models

data class User(
    val userId: String = "",
    val userDisplayName: String = "",
    val imageURL: String = "",
    val userDescription: String = "",
    val numberOfPosts: Int = 0
)
