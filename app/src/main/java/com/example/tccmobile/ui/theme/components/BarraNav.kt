package com.example.tccmobile.ui.theme.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tccmobile.R

private val GreenPrimary = Color(0xFF8CC3A3)
private val UnselectedColor = Color(0xFF8A7B72)
private val BorderBeige = Color(0xFFEDE4DA)

enum class BottomBarItem(val label: String, @DrawableRes val icon: Int) {
    Inicio("Início", R.drawable.home),
    Buscar("Buscar", R.drawable.lupa),
    Comunidades("Comunidades", R.drawable.users_alter),
    Geladeira("Geladeira", R.drawable.geladeira),
    ChefinhoIA("Chefinho IA", R.drawable.chef_hat),
}

@Composable
fun AppBottomBar(
    selected: BottomBarItem?,
    onItemClick: (BottomBarItem) -> Unit,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val barHeight = 72.dp
    val fabSize = 56.dp
    val fabOverhang = 28.dp
    val fabSlotWidth = 88.dp

    BoxWithConstraints(modifier = modifier.fillMaxWidth()) {
        val itemWidth = (maxWidth - fabSlotWidth) / 4

        Surface(
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(barHeight),
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(BorderBeige)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(barHeight + fabOverhang)
                .horizontalScroll(rememberScrollState()),
            verticalAlignment = Alignment.Bottom,
        ) {
            BottomBarItem.values().forEachIndexed { index, item ->
                BottomBarItemView(
                    item = item,
                    selected = item == selected,
                    onClick = { onItemClick(item) },
                    modifier = Modifier
                        .width(itemWidth)
                        .height(barHeight),
                )

                if (index == 1) {
                    Box(
                        modifier = Modifier
                            .width(fabSlotWidth)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.TopCenter,
                    ) {
                        Box(
                            modifier = Modifier
                                .size(fabSize)
                                .shadow(8.dp, CircleShape)
                                .background(GreenPrimary, CircleShape)
                                .clickable(onClick = onAddClick),
                            contentAlignment = Alignment.Center,
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Adicionar",
                                tint = Color.White,
                                modifier = Modifier.size(28.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun BottomBarItemView(
    item: BottomBarItem,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val color = if (selected) GreenPrimary else UnselectedColor

    Column(
        modifier = modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onClick,
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = item.label,
            tint = color,
            modifier = Modifier.size(28.dp),
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = item.label,
            color = color,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            maxLines = 1,
        )
    }
}

@Composable
fun BottomBarExample() {
    var selected by remember { mutableStateOf<BottomBarItem?>(BottomBarItem.Inicio) }

    Scaffold(
        bottomBar = {
            AppBottomBar(
                selected = selected,
                onItemClick = { selected = it },
                onAddClick = { },
                modifier = Modifier.navigationBarsPadding(),
            )
        }
    ) { padding ->
        Box(Modifier.padding(padding))
    }
}