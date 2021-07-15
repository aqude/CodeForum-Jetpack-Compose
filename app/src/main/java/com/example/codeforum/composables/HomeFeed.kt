package com.example.codeforum.composables

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import com.example.codeforum.MainActivity
import com.example.codeforum.data.DummyData
import com.example.codeforum.data.DummyData.postList
import com.example.codeforum.models.Post
import com.example.codeforum.models.User
import com.example.codeforum.ui.theme.*
import com.example.codeforum.utils.Utils.Companion.getTimeAgo
import java.lang.reflect.Type

@Composable
fun HomeFeed(
    onClick: (Int) -> Unit,
    onFABClick: () -> Unit,
    onEditButtonPressed: (String) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            AddNewFAB(
                onFABClick = onFABClick
            )
        }
    ) {
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier
                .fillMaxSize(),
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                val count = postList.size
                items(
                    count = count
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
                        post = postList[it],
                        onClick = onClick,
                        onEditButtonPressed = onEditButtonPressed
                    )
                }
            }
        }
    }
}

@Composable
private fun AddNewFAB(
    onFABClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onFABClick
    ) {
       Icon(
           imageVector = Icons.Default.Add,
           contentDescription = null
       )
    }
}

@Composable
fun PostItem(
    modifier: Modifier,
    post: Post,
    onClick: (Int) -> Unit,
    onEditButtonPressed: (String) -> Unit
) {
    var likedBy by remember {
        mutableStateOf(post.likedBy.size)
    }

    Column(modifier) {
        UserInfoSection(
            user = post.createdBy,
            createdAt = getTimeAgo(post.createdAt),
            onClick = onClick,
            onEditButtonPressed = onEditButtonPressed,
            postMessage = post.postText
        )

        PostMessage(
            postMessage = post.postText
        )

        Row() {
            LikeButtonIcon(){
                if(it) {
                    likedBy++
                } else {
                    likedBy--
                }
            }

            Text(
                text = "$likedBy",
                modifier = Modifier.padding(top = 12.dp),
                fontWeight = FontWeight.SemiBold
            )
            
        }
    }
}

@Composable
private fun EditPostButtonIcon(
    isAuthor: Boolean,
    onEditButtonPressed: (String) -> Unit,
    postMessage: String
) {
    val isLight = MaterialTheme.colors.isLight

    val tint = if (isLight) {
        Pink900
    } else {
        Green300
    }
    if(isAuthor) {
        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = "User Image",
            tint = tint,
            modifier = Modifier
                .clickable {
                    onEditButtonPressed(postMessage)
                }
        )
    }
}


@Composable
private fun LikeButtonIcon(
    onLiked: (Boolean) -> Unit
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
                onLiked(isLiked)
            }
    )
}

@Composable
private fun PostMessage(
    postMessage: String
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
private fun UserInfoSection(
    user: User,
    createdAt: String,
    onClick: (Int) -> Unit,
    onEditButtonPressed: (String) -> Unit,
    postMessage: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        UserImageAndDetails(
            user = user,
            onClick = onClick,
            belowUserDisplayName = {
                CreatedAt(createdAt = createdAt)
            }
        )

        EditPostButtonIcon(
            user.userDisplayName.equals("Shiba Inu"),
            onEditButtonPressed = onEditButtonPressed,
            postMessage = postMessage
        )
    }
}

@Composable
fun UserImageAndDetails(
    user: User,
    onClick: (Int) -> Unit,
    belowUserDisplayName: @Composable () -> Unit,
) {
    Row(
        modifier = Modifier
            .clickable {
                var userId = user.userId.toInt()
                Log.d("HomeFeed", "User Id: $userId pressed")
                onClick(userId)
            }
    ) {
        UserDisplayImage(
            userImage = painterResource(id = user.imageURL)
        )

        UserDetailsColumn(
            belowUserDisplayName = belowUserDisplayName,
            createdBy = user.userDisplayName
        )
    }
}

@Composable
private fun UserDisplayImage(
    userImage: Painter
) {
    Image(
        painter = userImage,
        contentDescription = "User Image",
        modifier = Modifier
            .size(48.dp)
            .clip(MaterialTheme.shapes.medium)
    )
}

@Composable
private fun UserDetailsColumn(
    createdBy: String,
    belowUserDisplayName: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentWidth()
            .padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.Top
    ) {
        UserDisplayName(
            name = createdBy
        )

        belowUserDisplayName()
    }
}

@Composable
private fun CreatedAt(
    createdAt: String
) {

    val isLight = MaterialTheme.colors.isLight

    val textColor = if(isLight) {
        PinkText
    } else {
        Color.LightGray
    }

    Text(
        text = createdAt,
        fontWeight = FontWeight.Light,
        textAlign = TextAlign.Center,
        color = textColor,
        maxLines = 1
    )
}

@Composable
private fun UserDisplayName(
    name: String
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
        HomeFeed(
            onClick = {

            },
            onFABClick = {

            },
            onEditButtonPressed = {

            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLightHomeFeed() {
    CodeForumTheme(darkTheme=false) {
        HomeFeed(
            onClick = {

            },
            onFABClick = {

            },
            onEditButtonPressed = {

            }
        )
    }
}