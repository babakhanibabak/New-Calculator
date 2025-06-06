package com.example.newcalculator.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CalculatorRow(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() ->Unit
    ) {
    Row (
        modifier=modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(0.2.dp)
    ){
        content()
    }
}

