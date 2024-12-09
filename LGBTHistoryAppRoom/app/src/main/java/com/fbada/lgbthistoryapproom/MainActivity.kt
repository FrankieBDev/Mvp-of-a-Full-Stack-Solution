package com.fbada.lgbthistoryapproom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.fbada.lgbthistoryapproom.data.LocationsDatabase
import com.fbada.lgbthistoryapproom.presentation.AddLocationScreen
import com.fbada.lgbthistoryapproom.presentation.HomeScreen
import com.fbada.lgbthistoryapproom.presentation.LocationsScreen
import com.fbada.lgbthistoryapproom.presentation.LocationsViewModel
import com.fbada.lgbthistoryapproom.presentation.MapScreen
import com.fbada.lgbthistoryapproom.theme.LgbtHistoryAppRoomTheme


class MainActivity : ComponentActivity() {

    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            LocationsDatabase::class.java,
            "location.db"
        ).build()
    }

    private val viewModel by viewModels<LocationsViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return LocationsViewModel(database.dao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LgbtHistoryAppRoomTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {}
                NavigationMain(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun NavigationMain(viewModel: LocationsViewModel) {
    val state by viewModel.state.collectAsState()
    val navController = rememberNavController()
    LocalContext.current
    NavHost(navController = navController,
        startDestination = "home",
        builder = {
            navigation(
                route = "home",
                startDestination = "HomeScreen"
            ) {
                composable("HomeScreen") {
                    HomeScreen(
                        navController = navController,
                        locations = state.locations,
                        onItemClick = {}
                    )
                }
            }
            navigation(
                route = "location",
                startDestination = "LocationsScreen"
            ) {
                composable("LocationsScreen") {
                    LocationsScreen(
                        state = state,
                        navController = navController,
                        onEvent = viewModel::onEvent
                    )
                }
            }
            navigation(
                route = "addLocation",
                startDestination = "AddLocationsScreen"
            ) {
                composable("AddLocationScreen") {
                    AddLocationScreen(
                        state = state,
                        navController = navController,
                        onEvent = viewModel::onEvent
                    )
                }
            }
            navigation(
                route = "map",
                startDestination = "MapScreen"
            ) {
                composable("MapScreen") {
                    MapScreen()
                }
            }
        }
    )
}

@Composable
fun NavigationBar(navController: NavController) {
    Surface(
        color = Color.Transparent,
        contentColor = Color.Black,
        modifier = Modifier.padding(vertical = 25.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavButton("Home", "home", navController)
            NavButton("List", "location", navController)
            NavButton("Map", "map", navController)
        }
    }
}

@Composable
fun NavButton(text: String, route: String, navController: NavController) {
    ElevatedButton(
        onClick = { navController.navigate(route) },
        colors = ButtonDefaults.buttonColors(
            Color.Red.copy(alpha = 0.8f)
        ),
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 10.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
            ),
            color = Color.White
        )
    }
}



