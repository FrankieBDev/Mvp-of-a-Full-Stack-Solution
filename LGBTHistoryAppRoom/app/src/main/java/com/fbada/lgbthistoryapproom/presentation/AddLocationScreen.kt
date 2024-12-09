package com.fbada.lgbthistoryapproom.presentation


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fbada.lgbthistoryapproom.NavigationBar
import com.fbada.lgbthistoryapproom.R

@Composable
fun AddLocationScreen(
    state: LocationState,
    navController: NavController,
    onEvent: (LocationsEvent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        BoxWithBackground(navController = navController) {
            LocationFields(state = state)
            SaveLocationButton(
                state = state,
                onEvent = onEvent,
                navController = navController
            )
        }
    }
}

@Composable
fun BoxWithBackground(
    navController: NavController,
    content: @Composable () -> Unit
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
        content()
    }
}

@Composable
fun LocationFields(state: LocationState) {
    Column(
        modifier = Modifier
            .padding(top = 100.dp)
            .fillMaxSize()
    ) {
        Modifier.fillMaxWidth().padding(16.dp)
        TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 17.sp)

        TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    value = state.name.value,
                    onValueChange = {
                        state.name.value = it
                    },
                    textStyle = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 17.sp
                    ),
                    placeholder = {
                        Text(text = "Location Name")
                    }
                )

                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    value = state.address.value,
                    onValueChange = {
                        state.address.value = it
                    },
                    placeholder = {
                        Text(text = "Address")
                    }
                )
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    value = state.about.value,
                    onValueChange = {
                        state.about.value = it
                    },
                    placeholder = {
                        Text(text = "About")
                    }
                )
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    value = state.latitude.toString(),
                    onValueChange = {
                        state.about.value = it
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    placeholder = {
                        Text(text = "Latitude")
                    }
                )
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    value = state.longitude.toString(),
                    onValueChange = {
                        state.about.value = it
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    placeholder = {
                        Text(text = "Longitude")
                    }
                )

    }
}

@Composable
fun SaveLocationButton(
    state: LocationState,
    onEvent: (LocationsEvent) -> Unit,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .padding(top = 540.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            ExtendedFloatingActionButton(
                onClick = {
                    onEvent(
                        LocationsEvent.SaveLocations(
                            name = state.name.value,
                            about = state.about.value,
                            address = state.address.value,
                            latitude = state.latitude,
                            longitude = state.longitude
                        )
                    )
                    navController.popBackStack()
                },
                text = { Text(text = "Save New Location") },
                icon = { Icon(Icons.Filled.Check, "Save New Location") },
                modifier = Modifier
                    .padding(bottom = 100.dp, top = 20.dp)
                    .align(Alignment.BottomCenter),
                shape = RoundedCornerShape(percent = 30),
                containerColor = Color.Red.copy(alpha = 0.6f),
                contentColor = Color.White
            )
        }
    }
}