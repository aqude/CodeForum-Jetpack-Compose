package com.example.codeforum.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.codeforum.R
import com.example.codeforum.ui.theme.CodeForumTheme

@Composable
fun WelcomeScreen(
    btnClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(scrollState)
    ) {
        WelcomeBackground()

        WelcomeScreenContent(
            btnClick = btnClick
        )
    }
}

@Composable
private fun WelcomeScreenContent(
    btnClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(60.dp))

        PhoneImage()

        Spacer(modifier = Modifier.height(30.dp))

        LogoImage()

        AppSubtitle()
        
        Spacer(modifier = Modifier.height(30.dp))

        CodeForumButton(
            btnClick = btnClick,
            buttonText = "Sign up with Google",
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

    }
}

@Composable
private fun AppSubtitle() {
    Text(
        text = "Platform for Coding Enthusiasts",
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier
            .paddingFromBaseline(32.dp)
    )
}

@Composable
fun CodeForumButton(
    btnClick: () -> Unit,
    buttonText: String,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = {
            btnClick()
        },
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = buttonColors(
            backgroundColor = MaterialTheme.colors.secondary
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = buttonText
        )
    }
}

@Composable
private fun LogoImage() {

    Text(
        text = "Code Forum",
        style = MaterialTheme.typography.h1,
        modifier = Modifier
            .paddingFromBaseline(32.dp)
    )
}

@Composable
private fun PhoneImage() {
    val isLight = MaterialTheme.colors.isLight

    val leafImageRes = if (isLight) {
        R.drawable.ic_light_phone
    } else {
        R.drawable.ic_dark_phone
    }

    Image(
        painter = painterResource(id = leafImageRes),
        contentDescription = "Illios Leaf Image",
        modifier = Modifier
            .offset(x=88.dp)
    )
}

@Composable
private fun WelcomeBackground() {
    val isLight = MaterialTheme.colors.isLight
    val backgroundImageRes = if (isLight) {
        R.drawable.ic_light_welcome_bg
    } else {
        R.drawable.ic_dark_welcome_bg
    }

    Image(
        painter = painterResource(id = backgroundImageRes),
        contentDescription = null,
        contentScale = ContentScale.FillHeight,
        modifier = Modifier
            .offset(y=18.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewDarkWelcomeScreen() {
    CodeForumTheme(darkTheme=true) {
        WelcomeScreen() {

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLightWelcomeScreen() {
    CodeForumTheme(darkTheme=false) {
        WelcomeScreen() {

        }
    }
}