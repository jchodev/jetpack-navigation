package com.jerry.jetpack_navigation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jerry.jetpack_navigation.model.Food
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {


    private val _uiState = MutableStateFlow<UIState>(UIState.Initial)
    val uiState = _uiState.asStateFlow()

    init {
        fetchList()
    }

    fun fetchList(){
        viewModelScope.launch {
            _uiState.value = UIState.Loading
            delay(500)
            _uiState.value = UIState.Success(
                listOf(
                    Food(
                        name = "apple",
                        price = "$20.00"
                    ),
                    Food(
                        name = "Meat",
                        price = "$100.00"
                    )
                )
            )
        }
    }

    fun onAddData(){
        _uiState.value = when (val currentState = _uiState.value) {
            is UIState.Success -> {
                val newList = currentState.data + listOf(Food(name = "rice", price = "$15.00"))
                UIState.Success(newList)
            }
            else -> currentState
        }
    }
}


sealed interface UIState {
    data object Initial : UIState
    data object Loading : UIState
    data class Success(val data: List<Food>) : UIState
    data class Error(val exception: Throwable) : UIState
}
