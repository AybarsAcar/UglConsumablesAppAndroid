package com.aybarsacar.uglconsumables.view.home.profile_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aybarsacar.uglconsumables.ui.theme.UglConsumablesTheme
import com.aybarsacar.uglconsumables.ui.theme.profileHeaderBackground


@Composable
fun ProfileHeader(
  username: String,
  department: String
) {
  Surface(
    color = MaterialTheme.colors.profileHeaderBackground
  ) {

    Column(
      modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
        .wrapContentHeight(),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {

      Image(
        modifier = Modifier
          .size(70.dp)
          .border(
            border = BorderStroke(1.dp, color = Color.White),
            shape = CircleShape
          )
          .clip(shape = CircleShape)
          .padding(8.dp),
        imageVector = Icons.Filled.Person,
        contentDescription = "Profile Image",
        colorFilter = ColorFilter.tint(Color.White)
      )

      Spacer(modifier = Modifier.height(8.dp))

      Text(text = username, style = MaterialTheme.typography.h6)

      Spacer(modifier = Modifier.height(2.dp))

      Text(text = department, style = MaterialTheme.typography.caption)

      Spacer(modifier = Modifier.height(8.dp))

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
      ) {

        Icon(
          modifier = Modifier.padding(end = 15.dp),
          imageVector = Icons.Default.Favorite,
          contentDescription = "Favourite"
        )
        Icon(
          modifier = Modifier.padding(start = 15.dp),
          imageVector = Icons.Default.Share,
          contentDescription = "Share"
        )
      }

      Spacer(modifier = Modifier.height(8.dp))

      FollowView()
    }
  }
}


@Composable
fun FollowView() {

  Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

    Column(
      modifier = Modifier.padding(end = 60.dp),
      verticalArrangement = Arrangement.Center
    ) {
      Text(text = "Followers", style = MaterialTheme.typography.caption)
      Text(text = "111", style = MaterialTheme.typography.h6)
    }

    Column(
      modifier = Modifier.padding(start = 60.dp),
      verticalArrangement = Arrangement.Center
    ) {
      Text(text = "Following", style = MaterialTheme.typography.caption)
      Text(text = "222", style = MaterialTheme.typography.h6)
    }
  }

}


@Preview
@Composable
fun ProfileHeaderPreview() {
  ProfileHeader(
    username = "Aybars Acar",
    department = "Data Analyst"
  )
}

@Preview
@Composable
fun ProfileHeaderPreviewDark() {
  UglConsumablesTheme(darkTheme = true) {
    ProfileHeader(
      username = "Aybars Acar",
      department = "Data Analyst"
    )
  }
}