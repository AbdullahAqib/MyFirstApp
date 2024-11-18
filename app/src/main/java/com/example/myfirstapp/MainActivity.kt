package com.example.myfirstapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myfirstapp.ui.theme.MyFirstAppTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFirstAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {
    
    var inputUnit by remember { mutableStateOf("Meter") }
    var outputUnit by remember { mutableStateOf("Meter") }
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    val iConversionFactor = remember { mutableStateOf(1.0) }
    val oConversionFactor = remember { mutableStateOf(1.0) }

    fun calculateResult () {
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.00
        val result = (inputValueDouble * iConversionFactor.value * 100 / oConversionFactor.value).roundToInt() / 100
        outputValue = result.toString()
    }
    
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Unit Convertor")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue = it
            calculateResult()
        })
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box {
                val expanded = remember { mutableStateOf(false) }
                Button(onClick = {
                    expanded.value = true
                }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown icon")
                }
                DropdownMenu(expanded = expanded.value, onDismissRequest = { /*TODO*/ }) {
                    DropdownMenuItem(text = { Text(text = "Centimeter") }, onClick = {
                        expanded.value = false
                        inputUnit = "Centimeter"
                        iConversionFactor.value = 0.01
                        calculateResult()
                    })
                    DropdownMenuItem(text = { Text(text = "Meter") }, onClick = { 
                        expanded.value = false
                        inputUnit = "Meter"
                        iConversionFactor.value = 1.0
                        calculateResult()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = { 
                        expanded.value = false
                        inputUnit = "Feet"
                        iConversionFactor.value = 0.3048
                        calculateResult()
                    })
                    DropdownMenuItem(text = { Text(text = "Millimeter") }, onClick = { 
                        expanded.value = false
                        inputUnit = "Millimeter"
                        iConversionFactor.value = 0.001
                        calculateResult()
                    })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                val expanded = remember { mutableStateOf(false) }
                
                Button(onClick = {
                    expanded.value = true
                }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown icon")
                }
                DropdownMenu(expanded = expanded.value, onDismissRequest = { /*TODO*/ }) {
                    DropdownMenuItem(text = { Text(text = "Centimeter") }, onClick = {
                        expanded.value = false
                        outputUnit = "Centimeter"
                        oConversionFactor.value = 0.01
                        calculateResult()
                    })
                    DropdownMenuItem(text = { Text(text = "Meter") }, onClick = {
                        expanded.value = false
                        outputUnit = "Meter"
                        oConversionFactor.value = 1.0
                        calculateResult()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        expanded.value = false
                        outputUnit = "Feet"
                        oConversionFactor.value = 0.3048
                        calculateResult()
                    })
                    DropdownMenuItem(text = { Text(text = "Millimeter") }, onClick = {
                        expanded.value = false
                        outputUnit = "Millimeter"
                        oConversionFactor.value = 0.001
                        calculateResult()
                    })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "$outputValue $outputUnit")
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    MyFirstAppTheme {
        UnitConverter()
    }
}