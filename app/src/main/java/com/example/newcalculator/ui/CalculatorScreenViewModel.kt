package com.example.newcalculator.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.pow
import kotlin.math.sqrt

@HiltViewModel
class CalculatorScreenViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(CalculatorScreenUiState())
    val uiState = _uiState.asStateFlow()

    fun onClearClick() {
        _uiState.value = CalculatorScreenUiState()
    }

    fun onNumberClick(number: Int) {
        if (_uiState.value.operator == null) {
            val validFirstNumber = _uiState.value.firstNumber

            val newNumber = when {
                validFirstNumber.isEmpty()  -> number.toString()
                validFirstNumber == "0" && !validFirstNumber.contains(".") ->
                    {if (number==0)"0" else number.toString()}
                else -> validFirstNumber + number
            }
            _uiState.update {
                it.copy(firstNumber = newNumber, result = newNumber)
            }
        } else {
            val validSecondNumber = _uiState.value.secondNumber

            val newNumber = when {
                validSecondNumber.isEmpty()  -> number.toString()
                validSecondNumber == "0" && !validSecondNumber.contains(".") ->
                {if (number==0)"0" else number.toString()}
                else -> validSecondNumber + number
            }
            _uiState.update {
                it.copy(secondNumber = newNumber, result = newNumber)
            }
        }
    }

    fun onOperatorClick(operator: CalculatorOperator) {
        when (operator) {
            CalculatorOperator.Equals -> calculateResult()
            CalculatorOperator.PlusMinus -> setPlusMinus()
            CalculatorOperator.Percent -> TODO()
            else -> {
                _uiState.update { it.copy(operator = operator) }
            }
        }
    }

    private fun calculateResult() {
        with(_uiState.value) {
            if (firstNumber.isNotEmpty() && secondNumber.isNotEmpty() && operator != null) {
                val decimalFirstNumber = firstNumber.toBigDecimal()
                val decimalSecondNumber = secondNumber.toBigDecimal()

                val result = when (operator) {
                    is CalculatorOperator.Plus -> decimalFirstNumber + decimalSecondNumber
                    is CalculatorOperator.Minus -> decimalFirstNumber - decimalSecondNumber
                    is CalculatorOperator.Divide -> decimalFirstNumber / decimalSecondNumber
                    CalculatorOperator.Equals -> calculateResult()
                    CalculatorOperator.Multiply -> decimalFirstNumber * decimalSecondNumber
                    CalculatorOperator.Percent -> decimalFirstNumber % decimalSecondNumber
                    CalculatorOperator.PlusMinus -> TODO()
                    CalculatorOperator.Pow -> TODO()
                }
                _uiState.update {
                    it.copy(
                        result = result.toString(),
                        firstNumber = result.toString(),
                        secondNumber = "",
                        operator = null
                    )
                }
            }
        }
    }

    private fun setPlusMinus() {
        val number = if (_uiState.value.operator == null) {
            _uiState.value.firstNumber
        } else {
            _uiState.value.secondNumber
        }

        val newNumber = if (number.isNotEmpty() && number != "0" && number.first() != '-') {
            "-$number"
        } else if (number.isNotEmpty() && number.first() == '-') {
            number.substring(1)
        } else {
            number
        }
        if (_uiState.value.operator == null) {
            _uiState.update {
                it.copy(firstNumber = newNumber, result = newNumber)
            }
        } else {
            _uiState.update {
                it.copy(secondNumber = newNumber, result = newNumber)
            }
        }
    }

    fun onDotClick() {
        if (_uiState.value.operator == null) {
            if (!_uiState.value.firstNumber.contains(".")) {
                val validFirstNumber = if (_uiState.value.firstNumber == "") "0" else {
                    _uiState.value.firstNumber
                }
                val newNumber = "$validFirstNumber."
                _uiState.update { it.copy(firstNumber = newNumber, result = newNumber) }
            }
        } else {
            if (_uiState.value.operator == null) {
                if (!_uiState.value.firstNumber.contains(".")) {
                    val validSecondNumber = if (_uiState.value.secondNumber == "") "0" else {
                        _uiState.value.secondNumber
                    }
                    val newNumber = "$validSecondNumber."
                    _uiState.update { it.copy(secondNumber = newNumber, result = newNumber) }
                }
            }
        }
    }

    fun onSquareClick() {
        val numberToSquare = if (_uiState.value.operator == null) {
            _uiState.value.firstNumber
        } else {
            _uiState.value.secondNumber
        }

        val squaredResult = numberToSquare.toBigDecimal()?.let {
            it.pow(2).toString()
        } ?: return

        if (_uiState.value.operator == null) {
            _uiState.value = _uiState.value.copy(
                firstNumber = squaredResult,
                result = squaredResult
            )
        } else {
            _uiState.value = _uiState.value.copy(
                secondNumber = squaredResult,
                result = squaredResult
            )
        }
    }

    fun onSquareRootClick() {
        val numberToRoot = if (_uiState.value.operator == null) {
            _uiState.value.firstNumber
        } else {
            _uiState.value.secondNumber
        }

        val number = numberToRoot.toDoubleOrNull()

        // Check for invalid input: not a number or negative
        val resultText = if (number == null || number <= 0) {
            "Invalid input"
        } else {

            // If valid, calculate the square root
            val result = sqrt(number)
            if (result % 1 == 0.0) result.toInt().toString() else result.toString()
        }
        if (_uiState.value.operator == null) {
            _uiState.value = _uiState.value.copy(
                firstNumber = resultText,
                result = resultText
            )
        } else {
            _uiState.value = _uiState.value.copy(
                secondNumber = resultText,
                result = resultText
            )
        }
    }

   fun onDeleteClick() {
        if (_uiState.value.operator == null) {
            val updated = _uiState.value.firstNumber.dropLast(1)
            _uiState.value = _uiState.value.copy(
                firstNumber = updated,
               result = updated
            )
        } else {
           val updated = _uiState.value.secondNumber.dropLast(1)
            _uiState.value = _uiState.value.copy(
                secondNumber = updated,
                result = updated
           )
        }
    }

    //x³
    fun onCubeClick() {
        val numberToCube = if (_uiState.value.operator == null) {
            _uiState.value.firstNumber
        } else {
            _uiState.value.secondNumber
        }
        val cubeResult = numberToCube.toDoubleOrNull()?.let {
            val result = it.pow(3)
            // Clean formatting (e.g. show 8 instead of 8.0)
            if (result % 1 == 0.0) result.toInt().toString() else result.toString()
        } ?: "Invalid Input"
        if (_uiState.value.operator == null) {
            _uiState.value = _uiState.value.copy(
                firstNumber = cubeResult,
                result = cubeResult
            )
        } else {
            _uiState.value = _uiState.value.copy(
                secondNumber = cubeResult,
                result = cubeResult
            )
        }
    }

    fun onReciprocalClick() {
        val number = if (_uiState.value.operator == null) {
            _uiState.value.firstNumber
        } else {
            _uiState.value.secondNumber
        }

        val reciprocalResult = number.toDoubleOrNull().takeIf { it != 0.0 }?.let {
            val result = 1 / it
            // Clean formatting: remove .0 for integers
            if (result % 1 == 0.0) result.toInt().toString() else result.toString()
        } ?: "Invalid input"

        if (_uiState.value.operator == null) {
            _uiState.value = _uiState.value.copy(
                firstNumber = reciprocalResult,
                result = reciprocalResult
            )
        } else {
            _uiState.value = _uiState.value.copy(
                secondNumber = reciprocalResult,
                result = reciprocalResult
            )
        }
    }
}


