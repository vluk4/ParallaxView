# ParallaxView

A lightweight Jetpack Compose library that adds a depth effect to your UI by responding to device orientation (gyroscope/accelerometer). Create immersive experiences with layered content that moves as the user tilts their device.

## Features

*   **Multi-Layer Support**: Define Background, Middle, and Foreground composables.
*   **Sensor-Driven**: Uses device sensors for natural, smooth movement.
*   **Customizable**: Control movement intensity, offset limits, and orientation (Horizontal, Vertical, Full).
*   **Plug & Play**: Easy to integrate into any Compose project.

## Installation

Add the JitPack repository to your root `build.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositories {
        ...
        maven { url = uri("https://jitpack.io") }
    }
}
```

Add the dependency to your module's `build.gradle.kts`:

```kotlin
dependencies {
    implementation("com.github.vluk4:ParallaxView:Tag")
}
```
*Replace `Tag` with the latest release version.*

## Usage

```kotlin
import com.vluk4.parallax.ParallaxView
import com.vluk4.example.parallax.model.ParallaxOrientation

ParallaxView(
    modifier = Modifier.fillMaxSize(),
    backgroundContent = {
        // Your background content here
        Image(
            painter = painterResource(id = R.drawable.background_image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    },
    middleContent = {
        // Your middle layer content here
        Text(text = "Parallax Effect", fontSize = 24.sp, color = Color.White)
    },
    foregroundContent = {
        // Your foreground content here
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = null,
            modifier = Modifier.size(100.dp),
            tint = Color.Yellow
        )
    },
    movementIntensityMultiplier = 20,
    orientation = ParallaxOrientation.Full
)
```

## Configuration

You can customize the behavior of the parallax effect using the following parameters:

| Parameter | Type | Default | Description |
| :--- | :--- | :--- | :--- |
| `movementIntensityMultiplier` | `Int` | `20` | Controls the sensitivity of the movement. Higher values result in more noticeable movement. |
| `verticalOffsetLimit` | `Float` | `0.5f` | Limits the maximum vertical displacement. |
| `horizontalOffsetLimit` | `Float` | `0.5f` | Limits the maximum horizontal displacement. |
| `orientation` | `ParallaxOrientation` | `Full` | Sets the allowed movement direction (`Full`, `Horizontal`, `Vertical`). |
| `backgroundContainerSettings` | `ContainerSettings` | Default | Configure scale and alignment for the background layer. |
| `middleContainerSettings` | `ContainerSettings` | Default | Configure scale and alignment for the middle layer. |
| `foregroundContainerSettings` | `ContainerSettings` | Default | Configure scale and alignment for the foreground layer. |

## Requirements

*   Android SDK 24+
*   Jetpack Compose

## License

[MIT License](LICENSE)
