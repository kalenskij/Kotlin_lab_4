package com.example.kzcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class ThreePhaseCalculatorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThreePhaseCalculatorScreen(onBackPressed = { finish() })
        }
    }
}

@Composable
fun ThreePhaseCalculatorScreen(onBackPressed: () -> Unit) {
    var voltageInput by remember { mutableStateOf("") }
    var impedanceInput by remember { mutableStateOf("") }
    var calculationResult by remember { mutableStateOf("") }

    fun calculateThreePhaseFaultCurrent() {
        val voltage = voltageInput.toDoubleOrNull()
        val impedance = impedanceInput.toDoubleOrNull()

        calculationResult = if (voltage != null && impedance != null && impedance != 0.0) {
            val faultCurrent = voltage / (impedance * kotlin.math.sqrt(3.0))
            "Струм трифазного КЗ: %.2f A".format(faultCurrent)
        } else {
            "Помилка: перевірте введення даних."
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = voltageInput,
            onValueChange = { voltageInput = it },
            label = { Text("Напруга (В)") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = impedanceInput,
            onValueChange = { impedanceInput = it },
            label = { Text("Імпеданс (Ом)") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = { calculateThreePhaseFaultCurrent() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Розрахувати")
        }
        Button(
            onClick = onBackPressed,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Повернутись")
        }
        Text(
            text = calculationResult,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}