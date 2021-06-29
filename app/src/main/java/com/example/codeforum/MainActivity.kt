package com.example.codeforum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.codeforum.composables.HomeFeed
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
                    startDestination = "homeFeed"
                ){
                    composable("welcome"){
                        WelcomeScreen(){
//                            Here we will call Sign In Method
                        }
                    }
                    composable("homeFeed"){
                        HomeFeed()
                    }
                }

            }
        }
    }
}