package com.example.codeforum.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import com.example.codeforum.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codeforum.ui.theme.AndroidEuclid
import com.example.codeforum.ui.theme.CodeForumTheme
import com.example.codeforum.ui.theme.PinkText
import java.lang.reflect.Type

@Composable
fun HomeFeed() {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxSize(),
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(
                count = 5
            ) {
                PostItem(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(5.dp)
                        .shadow(10.dp, RoundedCornerShape(10.dp))
                        .clip(RoundedCornerShape(10.dp))
                        .background(MaterialTheme.colors.primary)
                        .padding(16.dp),
                )
            }
        }
    }
}

@Composable
fun PostItem(
    modifier: Modifier,
) {
    Column(modifier) {
        UserInfoSection()

        PostMessage()

        Row() {
            LikeButtonIcon()

            Text(
                text = "254",
                modifier = Modifier.padding(top = 12.dp),
                fontWeight = FontWeight.SemiBold
            )
            
        }
    }
}

@Composable
private fun EditPostButtonIcon(
    isAuthor: Boolean = true
) {
    val isLight = MaterialTheme.colors.isLight

    val tint = if (isLight) {
        Color.Magenta
    } else {
        Color.Yellow
    }
    if(isAuthor) {
        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = "User Image",
            tint = tint
        )
    }
}


@Composable
private fun LikeButtonIcon(

) {
    var isLiked by remember {
        mutableStateOf(false)
    }

    val imageVector = if (isLiked) {
        Icons.Default.Favorite
    } else {
        Icons.Default.FavoriteBorder
    }

    val likedTint = if (isLiked) {
        Color.Red
    } else {
        MaterialTheme.colors.onPrimary
    }

    Icon(
        imageVector = imageVector,
        contentDescription = "Like Button",
        tint = likedTint,
        modifier = Modifier
            .padding(
                top = 8.dp,
                end = 8.dp
            )
            .clickable {
                isLiked = !isLiked
            }
    )
}

@Composable
private fun PostMessage(
    postMessage: String = "When you give Sir Ravindra Jadeja one ball to get 2 runs he will win it with one ball to spare !! \n\nSir Ravindra Jadeja an Absolute Legend!!"
) {
    Text(
        text = postMessage,
        modifier = Modifier
            .padding(
                top = 12.dp
            ),
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    )
}

@Composable
private fun UserInfoSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        UserImageAndDetails()

        EditPostButtonIcon()
    }
}

@Composable
private fun UserImageAndDetails() {
    Row() {
        UserDisplayImage()

        UserDetailsColumn()
    }
}

@Composable
private fun UserDisplayImage(
    userImage: Painter = painterResource(id = R.drawable.ic_user)
) {
    Icon(
        painter = userImage,
        contentDescription = "User Image",
        modifier = Modifier
            .size(48.dp)
            .clip(MaterialTheme.shapes.medium)
    )
}

@Composable
private fun UserDetailsColumn() {
    Column(
        modifier = Modifier
            .wrapContentWidth()
            .padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.Top
    ) {
        UserDisplayName()

        CreatedAt()
    }
}

@Composable
private fun CreatedAt() {

    val isLight = MaterialTheme.colors.isLight

    val textColor = if(isLight) {
        PinkText
    } else {
        Color.LightGray
    }

    Text(
        text = "Just now",
        fontWeight = FontWeight.Light,
        textAlign = TextAlign.Center,
        color = textColor,
        maxLines = 1
    )
}

@Composable
private fun UserDisplayName(
    name: String = "Mahendra Singh Dhoni"
) {
    Text(
        text = name,
        fontFamily = AndroidEuclid,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.secondary,
        fontSize = 16.sp,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewDarkHomeFeed() {
    CodeForumTheme(darkTheme=true) {
        HomeFeed()
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLightHomeFeed() {
    CodeForumTheme(darkTheme=false) {
        HomeFeed()
    }
}