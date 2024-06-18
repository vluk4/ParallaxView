package com.vluk4.parallaxview

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vluk4.parallaxview.ui.screens.ExampleFullScreen1
import com.vluk4.parallaxview.ui.screens.ExampleFullScreen2
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Home) {
                    composable<Home> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Button(onClick = { navController.navigate(ParallaxScreen2) }) {
                                    Text(text = "Parallax Screen 1")
                                }
                                Button(onClick = { navController.navigate(ParallaxScreen1) }) {
                                    Text(text = "Parallax Screen 2")
                                }
                            }
                        }
                    }
                    composable<ParallaxScreen1> {
                        ExampleFullScreen1()
                    }
                    composable<ParallaxScreen2> {
                        ExampleFullScreen2()
                    }
                }
            }
        }
    }
}

@Serializable
object Home

@Serializable
object ParallaxScreen2

@Serializable
object ParallaxScreen1