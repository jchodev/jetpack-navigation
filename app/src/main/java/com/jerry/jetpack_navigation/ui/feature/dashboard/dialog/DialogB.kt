package com.jerry.jetpack_navigation.ui.feature.dashboard.dialog

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController

import com.jerry.jetpack_navigation.model.Food
import com.jerry.jetpack_navigation.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogB(
    onDismissRequest: () -> Unit = {},
    onFoodSelected: (Food) -> Unit = {},
) {
    val list = listOf(
        Food (
            name = "Dialog B Food 1",
            price = "$10.00"
        ),
        Food (
            name = "Dialog B Food 2",
            price = "$20.00"
        )
    )
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Select Food",
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onDismissRequest) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                        )
                    }
                }
            )
        },
    ) {
        paddingValues ->
        LazyColumn (modifier = Modifier.fillMaxSize().padding(paddingValues)){

            items(list){
                Button ( onClick = {
                    onFoodSelected.invoke(it)
                }) {
                    Text(
                        text = it.name,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}