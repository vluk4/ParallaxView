package com.vluk4.parallaxview.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vluk4.example.parallax.model.ContainerSettings
import com.vluk4.parallaxview.ui.screens.components.ExampleTopBar
import com.vluk4.parallax.ParallaxView
import com.vluk4.parallaxview.R

@Composable
fun ExampleFullScreen1() {
    ParallaxView(
        backgroundContent = {
            Image(
                painter = painterResource(id = R.drawable.bg_galaxy),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.FillHeight
            )
        },
        middleContent = {
            var isCardExpanded by remember {
                mutableStateOf(false)
            }

            Scaffold(
                topBar = {
                    ExampleTopBar()
                },
                containerColor = Color.Transparent
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .align(Alignment.TopCenter)
                            .background(
                                color = Color.Transparent,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .border(
                                width = 2.dp,
                                color = Color.White,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .clickable {
                                isCardExpanded = !isCardExpanded
                            }
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = stringResource(R.string.card_title),
                                    color = Color.White,
                                    fontSize = 18.sp,
                                )
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowDown,
                                    contentDescription = null,
                                    tint = Color.White,
                                )
                            }
                            AnimatedVisibility(
                                visible = isCardExpanded
                            ) {
                                Text(
                                    text = stringResource(R.string.card_description),
                                    color = Color.White,
                                    modifier = Modifier.padding(top = 16.dp)
                                )
                            }
                        }
                    }
                }
            }
        },
        foregroundContent = {
            Image(
                painter = painterResource(id = R.drawable.fg_landscape_night),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )
        },
        backgroundContainerSettings = ContainerSettings(
            scale = 1.1f,
        ),
        foregroundContainerSettings = ContainerSettings(
            scale = 1.2f,
            alignment = Alignment.BottomCenter
        ),
        verticalOffsetLimit = 0.5f,
        horizontalOffsetLimit = 0.5f,
        movementIntensityMultiplier = 40,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
    )

}