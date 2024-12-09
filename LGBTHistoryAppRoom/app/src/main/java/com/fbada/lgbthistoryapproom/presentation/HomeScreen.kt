package com.fbada.lgbthistoryapproom.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.fbada.lgbthistoryapproom.NavigationBar
import com.fbada.lgbthistoryapproom.R
import com.fbada.lgbthistoryapproom.domain.Location


@Composable
fun HomeScreen(
    navController: NavController,
    locations: List<Location>,
    onItemClick: (Location) -> Unit
) {
    val imageIdMapper = remember {
        mutableMapOf(
            1 to R.drawable.alanstatue,
            2 to R.drawable.fleshhacienda,
            3 to R.drawable.temhall,
            4 to R.drawable.townhall,
            5 to R.drawable.gayvillage,
            6 to R.drawable.qlit
                )
    }

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

            LazyVerticalStaggeredGrid(
                modifier = Modifier.padding(top = 140.dp),
                columns = StaggeredGridCells.Adaptive(200.dp),
                verticalItemSpacing = 4.dp,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                content = {
                    items(locations.size) { index ->
                        val location = locations[index]
                        val imageResId = imageIdMapper[location.id] ?: R.drawable.aihome
                        ImageButtonWithText(
                            imageResId = imageResId,
                            buttonText = location.name,
                            location = location,
                            onButtonClick = { onItemClick(location) }
                        )
                    }
                })
        }
    }
}

@Composable
fun ImageButtonWithText(
    imageResId: Int,
    buttonText: String,
    location: Location?,
    onButtonClick: (Location?) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    ElevatedButton(
        onClick = {
            showDialog = true
            onButtonClick(location)
        },
        colors = ButtonDefaults.buttonColors(
            Color.Red.copy(alpha = 0.6f)
        ),
        modifier = Modifier.size(200.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = "Location Image",
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.FillWidth
            )
            Text(
                text = buttonText,
                color = Color.White,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                ),
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }
    }

    if (showDialog) {
        LocationDialog(location = location, onClose = { showDialog = false })
    }
}

@Composable
fun LocationDialog(location: Location?, onClose: () -> Unit) {
    Dialog(
        onDismissRequest = onClose,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        if (location != null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.LightGray.copy(alpha = 0.8f), shape = RoundedCornerShape(30.dp))
                    .padding(16.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    IconButton(
                        onClick = onClose,
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(top = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Color.Black
                        )
                    }

                    Text(
                        text = location.name,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.1.em,
                            lineHeight = 31.sp,
                            fontSize = 25.sp
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Address:",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            letterSpacing = 0.1.em,
                            lineHeight = 30.sp,
                            fontSize = 22.sp
                        ),
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    Text(
                        text = location.address,
                        style = TextStyle(color = Color.Black),
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )

                    Text(
                        text = "About:",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            letterSpacing = 0.1.em,
                            lineHeight = 30.sp,
                            fontSize = 20.sp
                        ),
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    Text(
                        text = location.about,
                        style = TextStyle(color = Color.Black),
                        lineHeight = 22.sp,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(bottom = 6.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                    Text(
                        text = "Coordinates:",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            letterSpacing = 0.1.em,
                            lineHeight = 20.sp,
                            fontSize = 20.sp
                        ),
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    Text(
                        text = location.latitude.toString(),
                        style = TextStyle(color = Color.Black),
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    Text(
                        text = location.longitude.toString(),
                        style = TextStyle(color = Color.Black),
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                }
            }
        }
    }
}
