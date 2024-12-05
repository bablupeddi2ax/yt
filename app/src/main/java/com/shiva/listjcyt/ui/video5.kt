package com.shiva.listjcyt
import android.media.MediaPlayer
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shiva.listjcyt.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Home(it: PaddingValues) {
    var v1 by remember {
        mutableFloatStateOf(0.0f)
    }
    var v2 by remember {
        mutableFloatStateOf(0.0f)
    }
    var v3 by remember {
        mutableFloatStateOf(0.0f)
    }
    val rc = rememberCoroutineScope()

    val lc = LocalContext.current
    fun animate(){
        v1=0.0f
        v2=0.0f
        v3=0.0f
        rc.launch {
            delay(300)
            androidx.compose.animation.core.animate(
                initialValue = 0f,
                targetValue = 1f,
                animationSpec = tween(durationMillis = 700)
            ) { value, _ ->
                v1 = value
                val mMediaPlayer = MediaPlayer.create(lc, R.raw.cardsound)
                mMediaPlayer.start()
            }
        }
        // Animate v2 with a slight delay
        rc.launch {
            delay(1000)
            androidx.compose.animation.core.animate(
                initialValue = 0f,
                targetValue = 1f,
                animationSpec = tween(durationMillis = 1000)
            ) { value, _ ->
                v2 = value
            }
        }

        // Animate v3 with another delay
        rc.launch {
            delay(1600)
            androidx.compose.animation.core.animate(
                initialValue = 0f,
                targetValue = 1f,
                animationSpec = tween(durationMillis = 1000)
            ) { value, _ ->
                v3 = value
            }
        }
    }
    LaunchedEffect(Unit) {

        rc.launch {
            delay(100)
            androidx.compose.animation.core.animate(
                initialValue = 0f,
                targetValue = 1f,
                animationSpec = tween(durationMillis = 700)
            ) { value, _ ->
                v1 = value
                val mMediaPlayer = MediaPlayer.create(lc, R.raw.cardsound)
                mMediaPlayer.start()
            }
        }
        // Animate v2 with a slight delay
        launch {
            delay(1000)
            androidx.compose.animation.core.animate(
                initialValue = 0f,
                targetValue = 1f,
                animationSpec = tween(durationMillis = 1000)
            ) { value, _ ->
                v2 = value
            }
        }

        // Animate v3 with another delay
        launch {
            delay(1600)
            androidx.compose.animation.core.animate(
                initialValue = 0f,
                targetValue = 1f,
                animationSpec = tween(durationMillis = 1000)
            ) { value, _ ->
                v3 = value
            }
        }
    }
    Column(
        Modifier
            .padding(it)
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
        , verticalArrangement = Arrangement.Top
    ) {

        TextButton(onClick = {animate()}) {
            Text(text = "Play Animation")
        }

        MyCard(
            modifier = Modifier

                .scale(v1.coerceAtMost(0.9f))
                .offset(y = 110.dp)
                .graphicsLayer(rotationX = v1 * 10 - 10,)

        )

        MyCard(
            modifier = Modifier
                .scale(v2.coerceAtMost(0.95f))
                .offset(y = 30.dp)
                .graphicsLayer(rotationX = v2 * 10 - 10)
        )

        MyCard(
            modifier = Modifier
                .scale(v3.coerceAtMost(1.0f))
                .graphicsLayer(rotationX = v3 * 10 - 10)
        )

        Spacer(modifier = Modifier.height(10.dp))


        // Slider to control progress
        Slider(
            value = v1,
            onValueChange = { newValue ->
                v1 = newValue
            },
            valueRange = 0f..1f,
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp),
            colors = SliderDefaults.colors(
                thumbColor = Color.Green,
                activeTrackColor = Color.Green,
                inactiveTrackColor = Color.LightGray
            )
        )

        Slider(
            value = v2,
            onValueChange = { newValue ->
                v2 = newValue
            },
            valueRange = 0f..1f,
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp),
            colors = SliderDefaults.colors(
                thumbColor = Color.Green,
                activeTrackColor = Color.Green,
                inactiveTrackColor = Color.LightGray
            )
        )
        Slider(
            value = v3,
            onValueChange = { newValue ->
                v3 = newValue
            },
            valueRange = 0f..1f,
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp),
            colors = SliderDefaults.colors(
                thumbColor = Color.Green,
                activeTrackColor = Color.Green,
                inactiveTrackColor = Color.LightGray
            )
        )

    }
    DisposableEffect(Unit) {
        val mMediaPlayer = MediaPlayer.create(lc, R.raw.cardsound)
        onDispose { mMediaPlayer.release() }
    }
}


@Composable
fun MyCard(modifier: Modifier) {
    val coroutineScope  = rememberCoroutineScope()

    val heightAnimatable = remember {
        Animatable(600f)
    }

    val contentOpacity = remember {
        Animatable(0f)
    }
    var dragOffset by remember{ mutableFloatStateOf(0f) }

    val localDensity = LocalDensity.current

    ElevatedCard(modifier = modifier

        .padding(top = 0.dp, start = 20.dp, end = 20.dp) // Reduced top padding
        .fillMaxWidth()
        .height(with(localDensity) { heightAnimatable.value.toDp() })

        .draggable(
            orientation = Orientation.Vertical,
            state = rememberDraggableState { delta ->
                coroutineScope.launch {
                    dragOffset += delta
                    if (delta > 0) {
                        val newHeight = (heightAnimatable.value + delta).coerceAtMost(1000f)

                        heightAnimatable.snapTo(newHeight)

                        if (newHeight > 700f) {
                            contentOpacity.snapTo(((newHeight - 600f) / 600f).coerceAtMost(1f))
                        }
                    } else if (delta < 0) {
                        val newHeight = (heightAnimatable.value + delta).coerceAtLeast(600f)
                        heightAnimatable.snapTo(newHeight)
                        if (newHeight <= 300f) {
                            contentOpacity.snapTo(0f)
                        } else {
                            contentOpacity.snapTo(newHeight - 300f)
                        }
                    }
                }
            },
            onDragStopped = {
                coroutineScope.launch {
                    if (heightAnimatable.value > 610f) {
                        heightAnimatable.animateTo(
                            1000f,
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioMediumBouncy,
                                stiffness = Spring.StiffnessMedium
                            )
                        )
                        contentOpacity.animateTo(1f)
                    } else {
                        heightAnimatable.animateTo(
                            600f,
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
        .padding(3.dp)
        ,
        elevation = CardDefaults.elevatedCardElevation(20.dp, pressedElevation = 90.dp),
        shape = RoundedCornerShape(20.dp)

    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFa3d9a8))
            , verticalArrangement = Arrangement.SpaceBetween
        ){
            Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween) {
                ElevatedCard(
                    modifier = Modifier.padding(
                        horizontal = 16.dp, vertical = 5.dp
                    ),
                    colors = CardDefaults.cardColors(Color.White),
                    elevation = CardDefaults.elevatedCardElevation(12.dp),
                    shape = RoundedCornerShape(20.dp)
                ) {

                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = "$3000", style = TextStyle(
                            fontSize = TextUnit(
                                17f,
                                TextUnitType.Sp
                            ),
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )

                }
                ElevatedCard(
                    modifier = Modifier.padding(
                        start = 13.dp,
                        top = 20.dp,
                        bottom = 10.dp,
                        end = 30.dp
                    ),
                    colors = CardDefaults.cardColors(Color.White),
                    elevation = CardDefaults.elevatedCardElevation(12.dp),
                    shape = RoundedCornerShape(20.dp)
                ) {

                    Text(
                        modifier = Modifier.padding(start=10.dp, end = 10.dp, top = 7.dp, bottom = 7.dp),
                        text = buildAnnotatedString {
                            append("+ ") // Add "+" character
                            addStyle(SpanStyle(color = Color.Black, fontWeight = FontWeight.Bold), 0, 1)

                            append("30%") // Add "30%"
                            addStyle(SpanStyle(color = Color.Black, fontWeight = FontWeight.Bold), 1, 4)
                        }, style = TextStyle(
                            fontSize = TextUnit(
                                17f,
                                TextUnitType.Sp
                            ),
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 0.dp), // Add padding for separation
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically // Align items centrally
            ) {
                Canvas(
                    modifier = Modifier
                        .weight(1f) // Share remaining space in the row
                        // Set height for the graph

                        .padding(start = 20.dp, bottom = 70.dp) // Add some padding
                ) {
                    val graphPoints = listOf(
                        Offset(50f, 150f),
                        Offset(100f, 100f),
                        Offset(150f, 120f),
                        Offset(200f, 50f),
                    )

                    // Draw lines connecting points
                    for (i in 0 until graphPoints.size - 1) {
                        drawLine(
                            color = Color.Black,
                            start = graphPoints[i],
                            end = graphPoints[i + 1],
                            strokeWidth = 3f
                        )
                    }

                    // Draw dots at each point
                    for (point in graphPoints) {
                        drawCircle(
                            color = Color.Black,
                            radius = 8f,
                            center = point
                        )
                    }
                }
                ElevatedCard(
                    modifier = Modifier,
                    elevation = CardDefaults.elevatedCardElevation(20.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .background(Color.White)
                            .padding(10.dp)
                        ,// Align text vertically in the center
                        text = "Space X",
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 20.sp
                        )
                    )
                }
            }

            Box(modifier = Modifier
                .height(25.dp)
                .background(Color(0xFFa3d9a8))
                .align(Alignment.CenterHorizontally)) {
                Image(
                    painter = painterResource(id = R.drawable.dragdoen),
                    contentDescription = "drag card down",
                    modifier = Modifier,
                    contentScale = ContentScale.Inside
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column(modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFa3d9a8))
                .alpha(contentOpacity.value)
                .padding(top = 10.dp)) {
                ElevatedCard(
                    modifier = Modifier.padding(start = 20.dp, top = 20.dp,),
                    elevation = CardDefaults.elevatedCardElevation(20.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .background(Color.White)
                            .padding(10.dp)
                        ,// Align text vertically in the center
                        text = "Space X",
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 20.sp
                        )
                    )
                }
            }
        }

    }
}