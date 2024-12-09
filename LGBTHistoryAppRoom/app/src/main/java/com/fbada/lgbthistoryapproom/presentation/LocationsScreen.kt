package com.fbada.lgbthistoryapproom.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fbada.lgbthistoryapproom.NavigationBar
import com.fbada.lgbthistoryapproom.R


@Composable
fun LocationsScreen(
    state: LocationState,
    navController: NavController,
    onEvent: (LocationsEvent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.lgbtbackground),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            NavigationBar(navController = navController)
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 140.dp)
                    .background(Color.Transparent),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                items(state.locations.size) { index ->
                    LocationItem(
                        state = state,
                        index = index,
                        onEvent = onEvent
                    )
                }
            }
            FloatingActionButton(onClick = {
                state.name.value = ""
                state.address.value = ""
                navController.navigate("AddLocationScreen")
            },
                modifier = Modifier
                    .padding(30.dp)
                    .align(Alignment.BottomEnd),
                shape = RoundedCornerShape(percent = 50),
                Color.Red.copy(alpha = 0.6f)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add new Location", modifier = Modifier)
            }
        }
    }
}

@Composable
fun LocationItem(
    state: LocationState,
    index: Int,
    onEvent: (LocationsEvent) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(color = Color.Red.copy(alpha = 0.6f))// boxes background
            .padding(12.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = state.locations[index].name,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.background
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = state.locations[index].address,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.background
            )

        }
        IconButton(
            onClick = {
                onEvent(LocationsEvent.DeleteLocation(state.locations[index]))
            }
        ) {
            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = "Delete Location",
                modifier = Modifier.size(35.dp),
                tint = MaterialTheme.colorScheme.onBackground
            )

        }

    }
}