package com.example.newcalculator.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.newcalculator.ui.theme.NewCalculatorTheme

@Composable
fun CalculatorScreen(
    modifier: Modifier = Modifier
) {
    CalculatorScreenContent()
}


@Composable
fun CalculatorScreenContent(
    modifier: Modifier = Modifier
) {

}

@Preview
@Composable
private fun CalculatorScreenContentPreview() {
    NewCalculatorTheme {
        CalculatorScreenContent()
    }
}