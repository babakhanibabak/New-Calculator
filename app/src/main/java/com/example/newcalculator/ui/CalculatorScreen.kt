package com.example.newcalculator.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newcalculator.ui.component.CalculatorAppBar
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


@Composable
fun CalculatorScreenContent(
    uiState: CalculatorScreenUiState,
    onOperatorClick:(CalculatorOperator)->Unit={}
) {
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
           CalculatorAppBar(title = "Calculator")
        }
    ) {paddingValues ->
        Column (
            modifier = Modifier.padding(paddingValues)
        ){

        }
    }
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