package com.example.newcalculator.ui

data class CalculatorScreenUiState(
    val result: String = "0",
    val firstNumber: String = "0",
    val secondNumber: String = "",
    val operator: CalculatorOperator? = null
)

sealed interface CalculatorOperator {
    data object Plus : CalculatorOperator
    data object Minus : CalculatorOperator
    data object PlusMinus : CalculatorOperator
    data object Percent : CalculatorOperator
    data object Divide : CalculatorOperator
    data object Multiply : CalculatorOperator
    data object Equals : CalculatorOperator
}

