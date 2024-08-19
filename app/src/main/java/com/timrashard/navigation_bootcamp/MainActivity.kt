package com.timrashard.navigation_bootcamp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cafe.adriel.voyager.navigator.Navigator
import com.timrashard.navigation_bootcamp.native_navigation.HomeScreen
import com.timrashard.navigation_bootcamp.native_navigation.PageA
import com.timrashard.navigation_bootcamp.native_navigation.PageB
import com.timrashard.navigation_bootcamp.native_navigation.PageX
import com.timrashard.navigation_bootcamp.native_navigation.PageY
import com.timrashard.navigation_bootcamp.ui.theme.Native
import com.timrashard.navigation_bootcamp.ui.theme.Navigation_BootcampTheme
import com.timrashard.navigation_bootcamp.ui.theme.Voyager
import com.timrashard.navigation_bootcamp.voyager_navigation.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation_BootcampTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    // 0 -> Native || 1 -> Voyager
    val navigationType = remember { mutableStateOf(0) }

    val topBarBackground = remember { mutableStateOf(Native) }

    LaunchedEffect(navigationType.value) {
        Log.e("NavigationType", if (navigationType.value == 0) "Native" else "Voyager")
    }

    Scaffold(
        topBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = topBarBackground.value)
                    .padding(vertical = 16.dp)
            ) {
                Text(
                    text = "Native Navigation",
                    fontSize = 18.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
                Switch(
                    checked = navigationType.value == 1,
                    onCheckedChange = {
                        navigationType.value = if (it) 1 else 0
                        topBarBackground.value = if (it) Voyager else Native
                    },
                )
                Text(
                    text = "Voyager Navigation",
                    fontSize = 18.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(topBarBackground.value)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                /*
                    Ekran bölmesi
                    Seçilen navigation türüne göre farklı yollarla ekran geçişi sağlanır!
                    0 -> Native Navigation
                    1 -> Voyager Navigator
                */

                when (navigationType.value) {
                    0 -> {
                        val navController = rememberNavController()

                        NavHost(navController = navController, startDestination = "home") {
                            composable("home") {
                                HomeScreen(navController)
                            }
                            composable("pageA") {
                                PageA(navController)
                            }
                            composable("pageX") {
                                PageX(navController)
                            }
                            composable("pageB") {
                                PageB(navController)
                            }
                            composable("pageY") {
                                PageY(navController)
                            }
                        }
                    }

                    1 -> {
                        Navigator(screen = HomeScreen())
                    }
                }
            }
        }
    }
}