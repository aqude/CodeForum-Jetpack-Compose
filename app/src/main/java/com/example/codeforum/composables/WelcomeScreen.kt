package com.example.codeforum.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
fun WelcomeScreen() {
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier
            .fillMaxSize()
    ) {
        WelcomeBackground()

        WelcomeScreenContent()
    }
}

@Composable
private fun WelcomeScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(72.dp))

        LeafImage()

        Spacer(modifier = Modifier.height(48.dp))

        LogoImage()

        AppSubtitle()
        
        Spacer(modifier = Modifier.height(40.dp))

        SignUpButton()
        
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
private fun SignUpButton() {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 16.dp),
        colors = buttonColors(
            backgroundColor = MaterialTheme.colors.secondary
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = "Sign up with Google"
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
private fun LeafImage() {
    val isLight = MaterialTheme.colors.isLight

    val leafImageRes = if (isLight) {
        R.drawable.ic_light_welcome_illos
    } else {
        R.drawable.ic_dark_welcome_illos
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
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .fillMaxSize()
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewDarkWelcomScreen() {
    CodeForumTheme(darkTheme=true) {
        WelcomeScreen()
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLightWelcomScreen() {
    CodeForumTheme(darkTheme=false) {
        WelcomeScreen()
    }
}