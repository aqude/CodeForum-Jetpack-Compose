package com.example.codeforum.utils

sealed class Screen(val route: String) {
    object WelcomeScreen: Screen("welcome")
    object HomeFeed: Screen("homeFeed")
    object UserInfoScreen: Screen("userInfo")
    object NewPost: Screen("newPost")
    object EditPost: Screen("editPost")
}
