package com.shiva.listjcyt
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.shiva.listjcyt.ui.theme.ListJcYtTheme
import kotlin.math.absoluteValue
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListJcYtTheme {
                Scaffold(
                    contentColor = Color(0xFFa3d9a8)
                ) {
                    MyPager(it = it, modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutImage(modifier: Modifier, imageRes: Int, title: String,page:Int,pagerState:PagerState) {
    var isClicked by remember {
        mutableStateOf(false)
    }
    Column (modifier = modifier
        .fillMaxSize()
        .background(contentColorFor(backgroundColor = Color(0xFFa3d9a8)))
        .graphicsLayer {
            val pageOffset = (
                    (pagerState.currentPage - page) + pagerState
                        .currentPageOffsetFraction
                    ).absoluteValue

            // We animate the alpha, between 50% and 100%
            alpha = lerp(
                start = 0.5f,
                stop = 1f,
                fraction = (1f - pageOffset.coerceIn(0f, 1f))
            )
        }
    ){
            Box(modifier = Modifier,
                contentAlignment = Alignment.BottomCenter
            ) {
                OutlinedCard(
                    colors = CardDefaults.cardColors( Color(0xFFa3d9a8)),
                    onClick = { /*TODO*/ },
                    border = BorderStroke(2.5.dp,Color.Black),
                    modifier = Modifier
                        .padding(120.dp)
                        .background(Color(0xFFa3d9a8))
                        .scale(3.5f)
                ) {
                            Spacer(modifier = Modifier
                                .height(70.dp)
                                .width(140.dp))
            }
                Image(
                    modifier = Modifier
                        .height(120.dp)
                        .scale(2.3f),
                    contentScale = ContentScale.Fit,
                    painter = painterResource(id = imageRes),
                    contentDescription =""
                )
        }
        Spacer(modifier = Modifier.height(80.dp))
        Text(text = "   $title",
           style = TextStyle(fontWeight = FontWeight.ExtraBold, fontSize = TextUnit(30f,TextUnitType.Sp), color = Color.Black),

            modifier = Modifier.
            fillMaxWidth())
        Spacer(modifier = Modifier.height(20.dp))
        IconButton(onClick = { isClicked = !isClicked }, modifier = Modifier.height(50.dp)) {
            if (isClicked) {
                Icon(
                    imageVector = Icons.Filled.Favorite, // Filled icon for liked state
                    contentDescription = "liked",
                    tint = Color.Blue
                )
            } else {
                Icon(
                    imageVector = Icons.Outlined.Favorite, // Outlined icon for not liked state
                    contentDescription = "not liked",
                    tint = Color.Gray
                )
            }
        }
    }
}
@Composable
fun MyPager(it: PaddingValues, modifier: Modifier) {
    // Create a list of content you want to display
    val pageContents = listOf(
        PageContentData(title = "Humangausaur", imageRes = R.drawable.humangadour),
        PageContentData(title = "Electro", imageRes = R.drawable.electro),
        PageContentData(title = "Alien X", imageRes = R.drawable.alienx)
    )
    val pagerState = rememberPagerState(pageCount = { pageContents.size })
    HorizontalPager(
        pageSize = PageSize.Fixed(pageSize = 299.dp),
        state = pagerState,
        modifier = modifier.padding(it),
    ) { page ->
        OutImage(
            title = pageContents[page].title,
            imageRes = pageContents[page].imageRes,
            modifier = Modifier.fillMaxSize(), // Ensure each page takes the full size
            page = page,
            pagerState = pagerState
        )
    }
}
data class PageContentData(val title: String, val imageRes: Int)
