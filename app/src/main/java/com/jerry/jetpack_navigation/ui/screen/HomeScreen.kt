package com.jerry.jetpack_navigation.ui.screen

import android.os.Bundle
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jerry.jetpack_navigation.model.Food
import com.jerry.jetpack_navigation.ui.LoadingCompose
import com.jerry.jetpack_navigation.ui.navigation.MainRoute
import com.jerry.jetpack_navigation.ui.navigation.navigate
import com.jerry.jetpack_navigation.ui.viewmodel.HomeViewModel
import com.jerry.jetpack_navigation.ui.viewmodel.UIState

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    mainNavController: NavHostController = rememberNavController(),
) {
    val uiState = viewModel.uiState.collectAsState().value
    when (uiState) {
        is UIState.Loading -> {
            LoadingCompose()
        }
        is UIState.Success -> {
            HomeScreenContent(
                foodList = uiState.data,
                mainNavController = mainNavController,
                onAddData = viewModel::onAddData
            )
        }
        else -> {
            HomeScreenContent(
                mainNavController = mainNavController,
            )
        }
    }

}

@Composable
fun HomeScreenContent(
    foodList: List<Food> = emptyList(),
    mainNavController: NavHostController = rememberNavController(),
    onAddData: () -> Unit = {},
){
    LazyColumn(
        Modifier.fillMaxSize(),
    ) {
        items(foodList){
            ListItem(
                headlineContent = {
                    Column {
                        Text ("name: ${it.name}")
                        Text ("name: ${it.price}")
                    }
                },
                trailingContent = {
                    Button(onClick = {
                        val bundle = Bundle()
                        bundle.putParcelable("item", it)
                        mainNavController.navigate(
                            route = MainRoute.DetailScreen.route,
                            args = bundle
                        )
                    }){
                        Text ("Detail")
                    }
                }
            )
        }

        item {
            Button(onClick = {
                val bundle = Bundle()
                bundle.putParcelableArrayList("list", ArrayList(foodList))
                mainNavController.navigate(
                    route = MainRoute.FullListScreen.route,
                    args = bundle
                )
            }){
                Text ("With full list")
            }
        }

        item {
            Button(onClick = onAddData
            ){
                Text ("add data")
            }
        }
    }
}