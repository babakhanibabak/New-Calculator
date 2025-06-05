package com.example.newcalculator.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newcalculator.ui.theme.NewCalculatorTheme

@Composable
fun CalculatorScreen(
    viewModel: CalculatorScreenViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    CalculatorScreenContent(
        uiState = uiState
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorScreenContent(
    uiState: CalculatorScreenUiState,
    onOperatorClick:(CalculatorOperator)->Unit={}
) {

}

@Preview
@Composable
private fun CalculatorScreenContentPreview() {
    NewCalculatorTheme {
        CalculatorScreenContent(
            uiState = CalculatorScreenUiState()
        )
    }
}