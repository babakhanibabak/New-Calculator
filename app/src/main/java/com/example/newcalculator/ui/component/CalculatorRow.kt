package com.example.newcalculator.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newcalculator.ui.theme.numberButtonColor

@Composable
fun CalculatorRow(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(0.5.dp)
    ) {
        content()
    }
}


@Composable
 fun RowScope.CalculatorButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color = numberButtonColor,
    weight: Float = 1f,
    fontSize: TextUnit = 24.sp,
    text: String,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .weight(weight),
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(vertical = 32.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
        ),
        onClick = onClick,
    ) {
        Text(
            text = text,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = fontSize,
        )
    }
}

