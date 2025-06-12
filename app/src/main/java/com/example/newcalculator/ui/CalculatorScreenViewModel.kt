package com.example.newcalculator.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class CalculatorScreenViewModel @Inject constructor():ViewModel() {

    private val _uiState = MutableStateFlow(CalculatorScreenUiState())
    val uiState = _uiState.asStateFlow()

    fun onClearClick(){
        _uiState.value=CalculatorScreenUiState()
    }

    fun onNumberClick(number: Int){
        if (_uiState.value.operator==null){
            val validFirstNumber=if (_uiState.value.firstNumber=="0")"" else {
                _uiState.value.firstNumber
            }

            val newNumber=validFirstNumber+number
            _uiState.update {
                it.copy(firstNumber = newNumber, result = newNumber)
            }
            }
        else{
            val validSecondNumber=if (_uiState.value.secondNumber=="0")"" else{
                _uiState.value.secondNumber
            }
            val newNumber=validSecondNumber+number
            _uiState.update {
                it.copy(secondNumber = newNumber , result = newNumber)
            }
        }
        }

    fun onOperatorClick(operator: CalculatorOperator){
        when(operator){
            CalculatorOperator.Divide -> TODO()
            CalculatorOperator.Equals -> calculateResult()
            CalculatorOperator.Minus -> TODO()
            CalculatorOperator.Multiply -> TODO()
            CalculatorOperator.Percent -> TODO()
            CalculatorOperator.Plus -> TODO()
            CalculatorOperator.PlusMinus -> TODO()
        }
    }


    }
