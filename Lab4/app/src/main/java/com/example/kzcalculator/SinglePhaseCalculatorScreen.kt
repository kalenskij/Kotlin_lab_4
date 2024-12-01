package com.example.kzcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class SinglePhaseCalculatorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                SinglePhaseCalculatorScreen(onBackPressed = { finish() })
            }
        }
    }
}

@Composable
fun SinglePhaseCalculatorScreen(onBackPressed: () -> Unit) {
    var voltage by remember { mutableStateOf("") }
    var impedance by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    // Calculation logic
    fun calculateSinglePhaseKZ() {
        val v = voltage.toDoubleOrNull()
        val z = impedance.toDoubleOrNull()

        result = when {
            v == null || z == null || z == 0.0 -> "Помилка: перевірте введення даних."
            else -> "Струм однофазного КЗ: %.2f A".format(v / z)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Input fields for voltage and impedance
        InputField(
            label = "Напруга (В)",
            value = voltage,
            onValueChange = { voltage = it }
        )
        InputField(
            label = "Імпеданс (Ом)",
            value = impedance,
            onValueChange = { impedance = it }
        )

        // Buttons for calculation and navigation
        Button(onClick = { calculateSinglePhaseKZ() }, modifier = Modifier.fillMaxWidth()) {
            Text("Розрахувати")
        }
        Button(onClick = onBackPressed, modifier = Modifier.fillMaxWidth()) {
            Text("Повернутись")
        }

        // Display result
        Text(text = result, modifier = Modifier.padding(top = 16.dp))
    }
}

@Composable
fun InputField(label: String, value: String, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth()
    )
}