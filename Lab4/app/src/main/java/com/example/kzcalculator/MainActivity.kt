package com.example.kzcalculator

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import android.content.Context
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.Composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val context = LocalContext.current // Use LocalContext

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                ) {
                    // Buttons
                    NavigationButton("Трифазний КЗ", ThreePhaseCalculatorActivity::class.java, context)
                    NavigationButton("Однофазний КЗ", SinglePhaseCalculatorActivity::class.java, context)
                    NavigationButton("Перевірка стійкості", StabilityCalculatorActivity::class.java, context)
                }
            }
        }
    }
}

@Composable
fun <T> NavigationButton(label: String, destination: Class<T>, context: Context) { // Use Context type for context
    Button(
        onClick = {
            val intent = Intent(context, destination)
            context.startActivity(intent)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(label)
    }
}