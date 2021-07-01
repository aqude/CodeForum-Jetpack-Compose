package com.example.codeforum.composables

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codeforum.data.DummyData.userList
import com.example.codeforum.models.User
import com.example.codeforum.ui.theme.CodeForumTheme

@Composable
fun UserInfoScreen(
    userId: Int,
    modifier: Modifier
) {
    val currentUser = userList[userId-1]

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Box(
            contentAlignment = Alignment.TopCenter
        ) {
            UserInfoSection(
                user = currentUser
            )

            UserDisplayImage(
                userImageId = currentUser.imageURL
            )
        }
    }
}

@Composable
private fun UserDisplayImage(
    userImageId: Int
) {
    Image(
        painter = painterResource(id = userImageId),
        contentDescription = "User Image",
        modifier = Modifier
            .padding(
                top = 60.dp
            )
            .size(150.dp)
            .clip(RoundedCornerShape(75.dp))
            .shadow(
                elevation = 16.dp,
                shape = RoundedCornerShape(75.dp)
            ),
    )
}

@Composable
private fun UserInfoSection(
    user: User
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 120.dp,
                bottom = 16.dp,
                start = 16.dp,
                end = 16.dp
            )
            .clip(MaterialTheme.shapes.medium)
            .shadow(
                shape = MaterialTheme.shapes.medium,
                elevation = 10.dp,
            )
            .background(MaterialTheme.colors.primary)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(100.dp))

            UserDisplayName(user.userDisplayName)

            Spacer(modifier = Modifier.height(24.dp))

            UserDescription(user)

            Spacer(modifier = Modifier.height(16.dp))

            UserAnalyticsRow(
                numberOfPosts = user.numberOfPosts,
                followers = user.followers
            )

            Spacer(modifier = Modifier.height(60.dp))

            CoderIcon()
        }
    }
}

@Composable
private fun CoderIcon() {
    Icon(
        imageVector = Icons.Default.AccountCircle,
        contentDescription = "Coder Image",
        modifier = Modifier
            .size(200.dp),
        tint = MaterialTheme.colors.onPrimary.copy(0.3f)
    )
}

@Composable
private fun UserAnalyticsRow(
    numberOfPosts: Int,
    followers: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        val analyticsDataName = if (numberOfPosts == 1) {
            "Post"
        } else {
            "Posts"
        }

        UserAnalyticsItem(
            dataIcon = Icons.Default.Analytics,
            dataValue = numberOfPosts.toString(),
            dataName = analyticsDataName
        )

        Spacer(modifier = Modifier.width(30.dp))

        UserAnalyticsItem(
            dataIcon = Icons.Default.PermIdentity,
            dataValue = followers,
            dataName = "Followers"
        )
    }
}

@Composable
private fun UserAnalyticsItem(
    dataIcon: ImageVector,
    dataValue: String,
    dataName: String
) {
    Icon(
        imageVector = dataIcon,
        contentDescription = "Number of $dataName"
    )

    Spacer(modifier = Modifier.width(8.dp))

    Text(
        text = "$dataValue $dataName",
        style = MaterialTheme.typography.caption,
        fontSize = 16.sp
    )
}

@Composable
private fun UserDescription(
    user: User
) {
    Text(
        text = user.userDescription,
        style = MaterialTheme.typography.h2,
        fontSize = 16.sp
    )
}

@Composable
private fun UserDisplayName(
    name: String
) {
    Text(
        text = name,
        style = MaterialTheme.typography.h1
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewDarkHomeFeed() {
    CodeForumTheme(darkTheme=true) {
        UserInfoScreen(userId = 1, Modifier)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLightHomeFeed() {
    CodeForumTheme(darkTheme=false) {
        UserInfoScreen(userId = 1, Modifier)
    }
}