package com.example.codeforum.composables

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codeforum.data.DummyData.userList
import com.example.codeforum.models.User
import com.example.codeforum.ui.theme.CodeForumTheme

@Composable
fun EditPost(
    onButtonClick: () -> Unit,
    textFieldHint: String = "What do you want to talk about?",
    buttonText: String
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        var appBarTitle: String = "Share Post"

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UserInfoRow()

            Spacer(modifier = Modifier.height(8.dp))

            postTextField(
                textFieldHint = textFieldHint
            )
            
            Spacer(modifier = Modifier.height(32.dp))

            CodeForumButton(
                btnClick = onButtonClick,
                buttonText = buttonText,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
private fun postTextField(
    textFieldHint: String
) {
    var postTextState by remember {
        mutableStateOf("")
    }

    TextField(
        value = postTextState,
        onValueChange = {
            postTextState = it
        },
        placeholder = {
            Text(
                text = textFieldHint,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(5.dp)
            )
            .clip(RoundedCornerShape(5.dp)),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.primary
        )
    )
}

@Composable
private fun UserInfoRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        UserImageAndDetails(
            user = userList[3],
            onClick = { },
            belowUserDisplayName = {
                Text(text = "Public")
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewDarkHomeFeed() {
    CodeForumTheme(darkTheme=true) {
        EditPost(
            onButtonClick = {

            },
            textFieldHint = "What do you want to talk about?",
            buttonText = "Post"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLightHomeFeed() {
    CodeForumTheme(darkTheme=false) {
        EditPost(
            onButtonClick = {

            },
            textFieldHint = "What do you want to talk about?",
            buttonText = "Post"
        )
    }
}