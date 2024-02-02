package com.app.ui.programs.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.app.ui.common.UiId
import com.app.ui.common.shimmerEffect

@Composable
fun ProgramItemComposable(
    id: UiId,
    title: String,
    subtitle: String,
    urlImage: String,
    urlLogoChannel: String?,
    onItemClicked: (UiId) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    bounded = true
                )
            ) { onItemClicked(id) }
            .padding(8.dp),
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .height(228.dp),
                    model = urlImage,
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                )
                AsyncImage(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.BottomStart)
                        .height(50.dp)
                        .padding(8.dp),
                    model = urlLogoChannel,
                    contentScale = ContentScale.Fit,
                    contentDescription = null,
                )
            }

            Text(
                text = title,
//            color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
            if (subtitle.isNotEmpty()) {
                Text(
                    text = subtitle,
//            color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}

@Composable
fun ShimmerProgramItem(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        items(5) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .height(228.dp)
                            .shimmerEffect()
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(
                        modifier = Modifier
                            .width(100.dp)
                            .height(20.dp)
                            .padding(top = 8.dp)
                            .shimmerEffect()
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(
                        modifier = Modifier
                            .width(200.dp)
                            .height(20.dp)
                            .padding(top = 8.dp)
                            .shimmerEffect()
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF)
@Composable
fun ComposablePreview() {
    ProgramItemComposable(
        id = UiId("id"),
        title = "La fille dans le brouillard",
        subtitle = "Film suspense",
        urlImage = "",
        urlLogoChannel = null,
        onItemClicked = {}
    )
}