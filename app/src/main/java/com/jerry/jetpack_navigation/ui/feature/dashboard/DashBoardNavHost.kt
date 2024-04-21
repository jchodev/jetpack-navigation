package com.jerry.jetpack_navigation.ui.feature.dashboard

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jerry.jetpack_navigation.ui.components.CustomerNavigationBar
import com.jerry.jetpack_navigation.ui.components.CustomerNavigationBarItem
import com.jerry.jetpack_navigation.ui.navigation.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DashBoardNavHost(
    navController: NavHostController = rememberNavController(),
) {
    val currentSelectedScreen by navController.currentScreenAsState()
    Scaffold(
        bottomBar = {
            CustomerNavigationBar (
                containerColor = Color.White
            ){
                CustomerNavigationBarItem (
                    selected = currentSelectedScreen == Screen.Dashboard.HomeScreen,
                    modifier = Modifier.weight(1f),
                    icon = Icons.Filled.Home,
                    text = "Home",
                    onClick = {
                        navController.navigate(Screen.Dashboard.HomeScreen.route)
                    }
                )
                CustomerNavigationBarItem (
                    selected = currentSelectedScreen == Screen.Dashboard.SettingScreen,
                    modifier = Modifier.weight(1f),
                    icon = Icons.Filled.Settings,
                    text = "Setting",
                    onClick = {
                        navController.navigate(Screen.Dashboard.SettingScreen.route)
                    }
                )
            }

        }
    ) {
        paddingValues->
        val heightToDecrease = 20.dp

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding() - heightToDecrease
                )
        ) {
            NavHost(
                navController = navController,
                startDestination = Screen.Dashboard.HomeScreen.route
            ) {
                composable(route = Screen.Dashboard.HomeScreen.route) {entry->
                    Text("this is home screen")
                }

                composable(route = Screen.Dashboard.SettingScreen.route) {entry->
                    Text("this is setting screen")
                }
            }
        }
    }
}

@Stable
@Composable
private fun NavController.currentScreenAsState(): State<Screen> {
    val selectedItem = remember { mutableStateOf<Screen>(Screen.Dashboard.HomeScreen) }
    DisposableEffect(key1 = this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            when {
                destination.hierarchy.any { it.route == Screen.Dashboard.HomeScreen.route } -> {
                    selectedItem.value = Screen.Dashboard.HomeScreen
                }
                destination.hierarchy.any { it.route == Screen.Dashboard.SettingScreen.route } -> {
                    selectedItem.value = Screen.Dashboard.SettingScreen
                }
            }

        }
        addOnDestinationChangedListener(listener)
        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }
    return selectedItem
}
