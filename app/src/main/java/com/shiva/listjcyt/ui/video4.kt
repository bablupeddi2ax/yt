package com.shiva.listjcyt.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Cards in Compose
@Composable
fun CardsYt(it:PaddingValues) {

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFa3d9a8))) {
        // Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .height(200.dp),
            colors = CardDefaults.cardColors(Color.Black)
        ) {

        }
        //Elevated Card
        ElevatedCard(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .height(200.dp),
            elevation = CardDefaults.cardElevation(20.dp),
            colors = CardDefaults.elevatedCardColors(Color.Black)) {

        }

        // OutlinedCard
        OutlinedCard(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .height(200.dp),
            colors = CardDefaults.outlinedCardColors(Color.Black),
            border = BorderStroke(5.dp,Color.White)
            ) {

        }

    }
}


/*

@Composable
fun ExpandableCard(it:PaddingValues) {
    // Coroutine scope for animations
    val coroutineScope = rememberCoroutineScope()

    // State to track card expansion
    var isExpanded by remember { mutableStateOf(false) }

    // Animatable for smooth height change
    val heightAnimatable = remember { Animatable(200f) }

    // Animatable for content opacity
    val contentOpacity = remember { Animatable(0f) }

    // Drag offset tracking
    var dragOffset by remember { mutableStateOf(0f) }

    val localDensity = LocalDensity.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(with(localDensity) { heightAnimatable.value.toDp() })
            .draggable(
                orientation = Orientation.Vertical,
                state = rememberDraggableState { delta ->
                    // Use coroutineScope for drag updates
                    coroutineScope.launch {
                        dragOffset += delta

                        // Expand card when dragging down
                        if (delta > 0) {
                            val newHeight = (heightAnimatable.value + delta).coerceAtMost(600f)

                            // Update height
                            heightAnimatable.snapTo(newHeight)

                            // Gradually reveal content
                            if (newHeight > 300f) {
                                contentOpacity.snapTo((newHeight - 300f) / 300f)
                            }
                        }
                        // Collapse card when dragging up
                        else if (delta < 0) {
                            val newHeight = (heightAnimatable.value + delta).coerceAtLeast(200f)

                            heightAnimatable.snapTo(newHeight)

                            // Hide content when collapsing
                            if (newHeight <= 300f) {
                                contentOpacity.snapTo(0f)
                            } else {
                                contentOpacity.snapTo((newHeight - 300f) / 300f)
                            }
                        }
                    }
                },
                onDragStopped = {
                    // Use coroutineScope for final animation
                    coroutineScope.launch {
                        if (heightAnimatable.value > 400f) {
                            heightAnimatable.animateTo(
                                600f,
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioMediumBouncy,
                                    stiffness = Spring.StiffnessMedium
                                )
                            )
                            contentOpacity.animateTo(1f)
                        } else {
                            heightAnimatable.animateTo(
                                200f,
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioMediumBouncy,
                                    stiffness = Spring.StiffnessMedium
                                )
                            )
                            contentOpacity.animateTo(0f)
                        }
                    }
                }
            )
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            // Drag Handle at the top
            Box(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .align(Alignment.TopCenter),
                contentAlignment = Alignment.Center
            ) {
                // Drag indicator
                Box(
                    modifier = Modifier
                        .width(50.dp)
                        .height(5.dp)
                        .background(Color.Gray, shape = RoundedCornerShape(2.5.dp))
                )
            }

            // Expandable Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(contentOpacity.value)
                    .padding(top = 50.dp)
            ) {
                // Your additional content here
                Text("Expanded Content")
                Text("More details...")
            }
        }
    }
}


@Composable
fun ThreeDimensionalCardStack(it:PaddingValues) {
    var rotationX by remember { mutableStateOf(0f) }
    var rotationY by remember { mutableStateOf(0f) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        // 3D Transformation Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .pointerInput(Unit) {
                    detectTransformGestures { _, _, pan, _ ->
                        coroutineScope.launch {
                            // Adjust rotation based on pan movement
                            rotationX = (pan / 10).coerceIn(-45f, 45f)
                            rotationY = -(pan / 10).coerceIn(-45f, 45f)
                        }
                    }
                }
        ) {
            // Card with 3D Transformation
            ElevatedCard(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        // 3D Rotation Transformation
                        this.rotationX = rotationX
                        this.rotationY = rotationY

                        // Add perspective effect
                        cameraDistance = 8f * density
                    }
            ) {
                // Card Content
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "3D Interactive Card",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Drag to rotate and explore 3D transformation",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        // Animated 3D Rotation Option
        Button(
            onClick = {
                coroutineScope.launch {
                    // Automatic 3D rotation animation
                    animate(
                        initialValue = 0f,
                        targetValue = 360f,
                        animationSpec = repeatable(
                            iterations = 1,
                            animation = tween(2000, easing = LinearEasing)
                        )
                    ) { value, _ ->
                        rotationY = value
                    }
                }
            },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            Text("Animate 3D Rotation")
        }
    }
}

// Alternative 3D Card with Parallax Effect
@Composable
fun ParallaxCard() {
    var offset by remember { mutableStateOf(Offset.Zero) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, _, _ ->
                    offset = pan
                }
            }
    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    // Create parallax effect
                    translationX = -offset.x / 20
                    translationY = -offset.y / 20

                    // Add subtle 3D rotation
                    rotationX = offset.y / 100
                    rotationY = -offset.x / 100
                }
        ) {
            // Parallax Card Content
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Parallax Card",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

// Stacked Cards with 3D Perspective
@Composable
fun StackedCardsWith3DEffect() {
    Box(modifier = Modifier.fillMaxSize()) {
        // Bottom Card (Largest, lowest in stack)
        MyCard(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(250.dp)
                .align(Alignment.BottomCenter)
                .graphicsLayer {
                    rotationX = 10f
                    scaleX = 0.9f
                    scaleY = 0.9f
                    transformOrigin = TransformOrigin(0.5f, 1f)
                }
        )

        // Middle Card
        MyCard(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .height(270.dp)
                .align(Alignment.BottomCenter)
                .offset(y = (-30).dp)
                .graphicsLayer {
                    rotationX = 5f
                    scaleX = 0.95f
                    scaleY = 0.95f
                    transformOrigin = TransformOrigin(0.5f, 1f)
                }
        )

        // Top Card (Smallest, on top of stack)
        MyCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .align(Alignment.BottomCenter)
                .offset(y = (-60).dp)
                .graphicsLayer {
                    transformOrigin = TransformOrigin(0.5f, 1f)
                }
        )
    }
*/