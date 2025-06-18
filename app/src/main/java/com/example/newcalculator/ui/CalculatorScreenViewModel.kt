package com.example.newcalculator.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class CalculatorScreenViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(CalculatorScreenUiState())
    val uiState = _uiState.asStateFlow()

    fun onClearClick() {
        _uiState.value = CalculatorScreenUiState()
    }

    fun onNumberClick(number: Int) {
        if (_uiState.value.operator == null) {
            val validFirstNumber = if (_uiState.value.firstNumber == "0") "" else {
                _uiState.value.firstNumber
            }

            val newNumber = validFirstNumber + number
            _uiState.update {
                it.copy(firstNumber = newNumber, result = newNumber)
            }
        } else {
            val validSecondNumber = if (_uiState.value.secondNumber == "0") "" else {
                _uiState.value.secondNumber
            }
            val newNumber = validSecondNumber + number
            _uiState.update {
                it.copy(secondNumber = newNumber, result = newNumber)
            }
        }
    }

    fun onOperatorClick(operator: CalculatorOperator) {
        when (operator) {
            CalculatorOperator.Divide -> TODO()
            CalculatorOperator.Equals -> calculateResult()
            CalculatorOperator.Minus -> TODO()
            CalculatorOperator.Multiply -> TODO()
            CalculatorOperator.Percent -> TODO()
            CalculatorOperator.Plus -> TODO()
            CalculatorOperator.PlusMinus -> setPlusMinus()
            CalculatorOperator.Pow -> TODO()
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
                    CalculatorOperator.Equals -> TODO()
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
        }else{
        if (_uiState.value.operator==null){
            if (!_uiState.value.firstNumber.contains(".")){
                val validSecondNumber=if (_uiState.value.secondNumber=="")"0" else{
                    _uiState.value.secondNumber
                }
                val newNumber="$validSecondNumber."
                _uiState.update { it.copy(secondNumber = newNumber, result = newNumber) }
            }
        }
    }
    }

    fun onSquareClick(){
        val numberToSquare=if (_uiState.value.operator==null){
            _uiState.value.firstNumber
        }else{
            _uiState.value.secondNumber
        }
    }
}
