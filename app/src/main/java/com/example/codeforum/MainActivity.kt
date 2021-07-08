package com.example.codeforum

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.codeforum.composables.HomeFeed
import com.example.codeforum.composables.UserInfoScreen
import com.example.codeforum.composables.WelcomeScreen
import com.example.codeforum.ui.theme.CodeForumTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodeForumTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "welcome"
                ){
                    composable("welcome"){
                        WelcomeScreen(){
//                            Here we will call Sign In Method
                            navController.navigate(
                                "homeFeed"
                            )
                        }
                    }
                    composable("homeFeed"){
                        HomeFeed(
                            onClick = {
                                Log.d("HomeFeed", "User Id: $it received")
                                navController.navigate(
                                    "userInfo/$it"
                                )
                            }
                        )
                    }
                    composable(
                        route = "userInfo/{userId}",
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

                        Log.d("HomeFeed", "User Id: $userId parsed fromm navArguments")
                        if(userId != null) {
                            UserInfoScreen(
                                userId = userId!!,
                                modifier = Modifier.verticalScroll(scrollState)
                            )
                        }
                        else {
                            Log.d("HomeFeed", "User Id: $userId is Null")
                        }
                    }
                }

            }
        }
    }
}