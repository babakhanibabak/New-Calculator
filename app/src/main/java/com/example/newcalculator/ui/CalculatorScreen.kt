package com.example.newcalculator.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newcalculator.ui.component.CalculatorAppBar
import com.example.newcalculator.ui.component.CalculatorButton
import com.example.newcalculator.ui.component.CalculatorRow
import com.example.newcalculator.ui.theme.NewCalculatorTheme

@Composable
fun CalculatorScreen(
    viewModel: CalculatorScreenViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    CalculatorScreenContent(
        uiState = uiState,

    )
}


@Composable
fun CalculatorScreenContent(
    uiState: CalculatorScreenUiState,
    onOperatorClick:(CalculatorOperator)->Unit={},
    onClearClick:()-> Unit={},
    onNumberClick:(Int)->Unit={}
) {
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
           CalculatorAppBar(title = "Calculator")
        }
    ) {paddingValues ->
        Column (
            modifier = Modifier.padding(paddingValues)
        ){
            Box(
                modifier = Modifier
                    .background(color = Color.LightGray.copy(0.2f))
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    text = uiState.result,
                    fontWeight = FontWeight.Bold,
                    fontSize = 64.sp,
                    textAlign = TextAlign.End,
                    color = Color.Black,
                    lineHeight = 76.sp,
                )
            }
CalculatorRow {
CalculatorButton(
    text = "AC",
    onClick = onClearClick,
    )
    CalculatorButton(
        text = "+/-",
        onClick = { onOperatorClick(CalculatorOperator.PlusMinus) }
    )
    CalculatorButton(
        text = "%",
        onClick = { onOperatorClick(CalculatorOperator.Percent) }
    )
    CalculatorButton(
        text = "÷",

        onClick = { onOperatorClick(CalculatorOperator.Divide) }
    )
}
CalculatorRow {
    CalculatorButton(
        text = "7",
        onClick = {onNumberClick(7)}
    )
    CalculatorButton(
        text = "8",
        onClick = {onNumberClick(8)}
    )
    CalculatorButton(
        text = "9",
        onClick = {onNumberClick(9)}
    )
    CalculatorButton(
        text = "*",
        onClick ={onOperatorClick(CalculatorOperator.Multiply)}
    )
}
            CalculatorRow {
                CalculatorButton(
                    text = "4",
                    onClick = {onNumberClick(4)}
                )
                CalculatorButton(
                    text = "5",
                    onClick = {onNumberClick(5)}
                )
                CalculatorButton(
                    text = "6",
                    onClick = {onNumberClick(6)}
                )
                CalculatorButton(
                    text = "-",
                    onClick = {onOperatorClick(CalculatorOperator.Minus)}
                )
            }
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