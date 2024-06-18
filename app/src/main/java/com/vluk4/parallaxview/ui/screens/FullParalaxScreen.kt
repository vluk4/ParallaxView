package com.vluk4.parallaxview.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.vluk4.example.parallax.model.ContainerSettings
import com.vluk4.example.parallax.model.ParallaxOrientation
import com.vluk4.parallax.ParallaxView
import com.vluk4.parallaxview.R

@Composable
fun ExampleFullScreen2() {
    ParallaxView(
        backgroundContent = {
            Image(
                painter = painterResource(id = R.drawable.bg_forest),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillHeight
            )
        },
        middleContent = {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = stringResource(R.string.example_text),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .zIndex(1f)
                        .padding(horizontal = 90.dp)
                        .align(Alignment.Center),
                    fontFamily = FontFamily(Font(R.font.courgette_regular)),
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        },
        middleContainerSettings = ContainerSettings(
            alignment = Alignment.Center
        ),
        backgroundContainerSettings = ContainerSettings(
            scale = 1.4f,
        ),
        movementIntensityMultiplier = 50,
        orientation = ParallaxOrientation.Full
    )
}