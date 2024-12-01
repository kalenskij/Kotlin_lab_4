package com.example.kzcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class StabilityCalculatorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                StabilityCalculatorScreen(onBackPressed = { finish() })
            }
        }
    }
}

@Composable
fun StabilityCalculatorScreen(onBackPressed: () -> Unit) {
    var current by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    // Calculation logic
    fun calculateStability() {
        val i = current.toDoubleOrNull()
        val t = duration.toDoubleOrNull()

        result = when {
            i == null || t == null -> "Помилка: перевірте введення даних."
            else -> "Термічна стійкість: %.2f A²·с".format(i * i * t)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Input fields for current and duration
        InputField(
            label = "Ток (А)",
            value = current,
            onValueChange = { current = it }
        )
        InputField(
            label = "Тривалість (с)",
            value = duration,
            onValueChange = { duration = it }
        )

        // Buttons for calculation and navigation
        Button(onClick = { calculateStability() }, modifier = Modifier.fillMaxWidth()) {
            Text("Розрахувати")
        }
        Button(onClick = onBackPressed, modifier = Modifier.fillMaxWidth()) {
            Text("Повернутись")
        }

        // Display result
        Text(text = result, modifier = Modifier.padding(top = 16.dp))
    }
}