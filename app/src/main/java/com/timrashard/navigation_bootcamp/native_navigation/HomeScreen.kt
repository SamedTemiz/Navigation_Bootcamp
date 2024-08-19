package com.timrashard.navigation_bootcamp.native_navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.timrashard.navigation_bootcamp.ui.theme.Beiege
import com.timrashard.navigation_bootcamp.ui.theme.LightGray

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF5a9bd5))
    ) {
        Text(text = "HOME SCREEN", fontSize = 36.sp, fontWeight = FontWeight.Bold)

        Spacer(Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    navController.navigate("pageA")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Beiege,
                    contentColor = Color.Black
                )
            ) {
                Text(text = "Go page A", fontSize = 18.sp)
            }

            Button(
                onClick = {
                    navController.navigate("pageX")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = LightGray,
                    contentColor = Color.Black
                )
            ) {
                Text(text = "Go page X", fontSize = 18.sp)
            }
        }
    }
}
