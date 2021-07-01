package com.example.codeforum.data

import com.example.codeforum.R
import com.example.codeforum.models.Post
import com.example.codeforum.models.User

object DummyData {

    val userList = listOf<User>(
        User(
            userId = "1",
            userDisplayName = "Linus Torvalds",
            imageURL = R.drawable.dp_torvalds,
            userDescription = "Works at Linux Foundation",
            numberOfPosts = 2,
            followers = "50.7K"
        ),
        User(
            userId = "2",
            userDisplayName = "Sundar Pichai",
            imageURL = R.drawable.dp_sundar_pichai,
            userDescription = "CEO, Google and Alphabet",
            numberOfPosts = 3,
            followers = "3.8M"
        ),
        User(
            userId = "3",
            userDisplayName = "Elon Musk",
            imageURL = R.drawable.dp_elon,
            userDescription = "Who let the doge out",
            numberOfPosts = 1,
            followers = "57.7M"
        ),
        User(
            userId = "4",
            userDisplayName = "Shiba Inu",
            imageURL = R.drawable.dp_shiba_inu,
            userDescription = "I am not a crypto currency",
            numberOfPosts = 1,
            followers = "8.6K"
        )
    )

    val postList = listOf<Post>(
        Post(
            postText = "I am the Doge Father",
            createdBy = userList[2],
            createdAt = 1625062662397L,
            likedBy = arrayListOf(
                "3"
            )
        ),
        Post(
            postText = "No. Elon isn't my father",
            createdBy = userList[3],
            createdAt = 1625062661397L,
            likedBy = arrayListOf(
                "1",
                "2",
                "5"
            )
        ),
        Post(
            postText = "Talk is cheap, Show me the code",
            createdBy = userList[0],
            createdAt = 1625062642397L,
            likedBy = arrayListOf(
                "1",
                "2",
                "3"
            )
        ),
        Post(
            postText = "Microsoft isn't evil, they just make really crappy operating systems.",
            createdBy = userList[0],
            createdAt = 1625062640397L,
            likedBy = arrayListOf(
                "1",
                "2",
                "3"
            )
        ),
        Post(
            postText = "Wear your failure as a badge of honor!",
            createdBy = userList[1],
            createdAt = 1625062620397L,
            likedBy = arrayListOf(
                "1",
                "3"
            )
        ),
        Post(
            postText = "Thanks for dropping by @MKBHD! \n\nGlad we got to chat about all things #GoogleIO. Come back next time and I'll show you my new daily driver:)",
            createdBy = userList[1],
            createdAt = 1625062220397L,
            likedBy = arrayListOf(
                "1",
                "3"
            )
        ),
        Post(
            postText = "Welcome to my hometown @englandcricket wish was there for the game, should be a great series",
            createdBy = userList[1],
            createdAt = 1625062020397L,
            likedBy = arrayListOf(
                "1",
                "3"
            )
        ),
    )

}