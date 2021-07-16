package com.example.codeforum

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.codeforum.composables.EditPost
import com.example.codeforum.composables.HomeFeed
import com.example.codeforum.composables.UserInfoScreen
import com.example.codeforum.composables.WelcomeScreen
import com.example.codeforum.ui.theme.CodeForumTheme
import com.example.codeforum.utils.Screen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodeForumTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screen.WelcomeScreen.route
                ){
                    composable(Screen.WelcomeScreen.route){
                        WelcomeScreen(){
//                            Here we will call Sign In Method
                            navController.navigate(
                                route = Screen.HomeFeed.route
                            )
                        }
                    }
                    composable(Screen.HomeFeed.route){
                        HomeFeed(
                            onClick = {
                                navController.navigate(
                                    Screen.UserInfoScreen.route + "/$it"
                                )
                            },
                            onFABClick = {
                                navController.navigate(
                                    Screen.NewPost.route
                                )
                            },
                            onEditButtonPressed = {
                                navController.navigate(
                                    Screen.EditPost.route + "/$it"
                                )
                            }
                        )
                    }
                    composable(
                        route = Screen.UserInfoScreen.route + "/{userId}",
                        arguments = listOf(
                            navArgument("userId"){
                                type = NavType.IntType
                            }
                        )
                    ){
                        var userId = remember{
                            it.arguments?.getInt("userId")
                        }
                        val scrollState = rememberScrollState()
                        UserInfoScreen(
                            userId = userId!!,
                            modifier = Modifier.verticalScroll(scrollState)
                        )
                    }
                    composable(
                        route = Screen.NewPost.route
                    ){
                        EditPost(
                            onButtonClick = {
                                navController.navigate(Screen.HomeFeed.route)
                                Toast.makeText(this@MainActivity, "Posting...", Toast.LENGTH_SHORT).show()
                            },
                            textFieldHint = "What do you want to talk about?",
                            buttonText = "Post"
                        )
                    }
                    composable(
                        route = Screen.EditPost.route + "/{postText}",
                        arguments = listOf(
                            navArgument("postText"){
                                type = NavType.StringType
                            }
                        )
                    ){
                        var textFieldHint = remember {
                            it.arguments?.getString("postText")
                        }

                        Log.d("editPost", "Text: $textFieldHint parsed fromm navArguments")
                        EditPost(
                            onButtonClick = {
                                navController.navigate(Screen.HomeFeed.route)
                                Toast.makeText(this@MainActivity, "Updating...", Toast.LENGTH_SHORT).show()
                            },
                            textFieldHint = textFieldHint!!,
                            buttonText = "Update",
                            postTextValue = textFieldHint
                        )
                    }
                }

            }
        }
    }
}