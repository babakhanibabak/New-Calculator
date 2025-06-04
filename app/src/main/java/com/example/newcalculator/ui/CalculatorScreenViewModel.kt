package com.example.newcalculator.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class CalculatorScreenViewModel @Inject constructor():ViewModel() {

    private val _uiState = MutableStateFlow(CalculatorScreenUiState())
    val uiState = _uiState.asStateFlow()

}