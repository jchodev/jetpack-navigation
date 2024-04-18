package com.jerry.jetpack_navigation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jerry.jetpack_navigation.ui.shape.BottomNavItemCurvedShape


@Composable
fun CustomerNavigationBar(
    modifier: Modifier = Modifier,
    containerColor: Color = NavigationBarDefaults.containerColor,
    contentColor: Color = MaterialTheme.colorScheme.contentColorFor(containerColor),
    tonalElevation: Dp = NavigationBarDefaults.Elevation,
    windowInsets: WindowInsets = NavigationBarDefaults.windowInsets,
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        color = containerColor,
        contentColor = contentColor,
        tonalElevation = tonalElevation,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(windowInsets)
                .height(88.dp)
                .selectableGroup()
            ,
            horizontalArrangement = Arrangement.spacedBy(0.dp),
            content = content
        )
    }
}

@Composable
fun CustomerNavigationBarItem (
    selected: Boolean = true,
    modifier: Modifier = Modifier,
    //bgColor: Color = MaterialTheme.colorScheme.background,
    bgColor: Color = Color.Gray,
    color: Color = MaterialTheme.colorScheme.primary,
    icon: ImageVector = Icons.Default.ArrowBack,
    onClick: () -> Unit = {},
    text: String = "home",
){
    if (selected){
        CustomerNavigationBarItemSelected(
            modifier = modifier,
            bgColor = bgColor,
            color = color,
            icon = icon,
            onClick = onClick,
            text = text
        )
    } else {
        CustomerNavigationBarItemNonSelected(
            modifier = modifier,
            bgColor = bgColor,
            color = color,
            icon = icon,
            onClick = onClick,
            text = text
        )
    }
}

@Composable
fun CustomerNavigationBarItemSelected(
    modifier: Modifier = Modifier,
    //bgColor: Color = MaterialTheme.colorScheme.background,
    bgColor: Color = Color.Gray,
    color: Color = MaterialTheme.colorScheme.primary,
    icon: ImageVector = Icons.Default.ArrowBack,
    onClick: () -> Unit = {},
    text: String = "home",
) {
    Column (
        modifier = modifier
            .fillMaxSize()
            .background(color = bgColor, shape = BottomNavItemCurvedShape())
            .clickable {
                onClick.invoke()
            },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .padding(top = 4.dp)
                .size(40.dp)
                .background(color, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {

            Icon(
                modifier = Modifier.size(24.dp),
                tint = bgColor ,
                imageVector = icon,
                contentDescription = text
            )

        }
        Text(modifier = Modifier.fillMaxWidth(), text = text, textAlign = TextAlign.Center)
    }
}

@Composable
fun CustomerNavigationBarItemNonSelected(
    modifier: Modifier = Modifier,
    //bgColor: Color = MaterialTheme.colorScheme.background,
    bgColor: Color = Color.Gray,
    color: Color = MaterialTheme.colorScheme.primary,
    icon: ImageVector = Icons.Default.ArrowBack,
    onClick: () -> Unit = {},
    text: String = "home",
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(color = bgColor, shape = BottomNavItemCurvedShape(applyCurve = false))
            .clickable {
                onClick.invoke()
            },
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Box(
            modifier = Modifier.size(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                tint = color ,
                imageVector = icon,
                contentDescription = text
            )
        }
    }
}