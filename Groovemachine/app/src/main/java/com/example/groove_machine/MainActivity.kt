package com.example.groove_machine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.input.KeyboardType
import com.example.groove_machine.ui.theme.GroovemachineTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GroovemachineTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    GrooveMachineForm()
                }
            }
        }
    }
}

@Composable
fun GrooveMachineForm() {
    var referenceTrack by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf("") }
    var bpm by remember { mutableStateOf("") }
    var kick by remember { mutableStateOf("") }
    var snare by remember { mutableStateOf("") }
    var hiHat by remember { mutableStateOf("") }
    var cymbal by remember { mutableStateOf("") }

    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }
    val items = listOf("X", "Y", "Z")

    var fileUri by remember { mutableStateOf<String?>(null) }

    val openFilePicker =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                // Handle the selected file URI here
                fileUri = uri.toString()
            }
        }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier.fillMaxWidth()
                .border(width = 1.dp, color = Color.Black)
        )
        {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Genre", //items[selectedIndex], // Display the currently selected item's text
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable(onClick = { expanded = true })
                )
                IconButton(
                    onClick = { expanded = true },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = "Drop-down")
                }
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(Color.White)
            ) {
                items.forEachIndexed { index, item ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedIndex = index
                                expanded = false
                            }
                            .border(width = 1.dp, color = Color.Black)
                    ) {
                        Text(
                            text = item,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
        Box(
            modifier = Modifier.fillMaxWidth()
                .border(BorderStroke(1.dp, Color.Black)) // Add border here
        ) {
            //Spacer(modifier = Modifier.height(16.dp))

            // File input field
            Row(verticalAlignment = Alignment.CenterVertically) {
                BasicTextField(
                    value = fileUri ?: " Reference Track",
                    onValueChange = {}, // File path is read-only
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                )
                Button(onClick = { openFilePicker.launch("image/*") }) {
                    Text("Select File")
                }
            }
        }

                Spacer(modifier = Modifier.height(16.dp))

                // Numeric input field for BPM
                OutlinedTextField(
                    value = bpm,
                    onValueChange = { newValue ->
                        // Only accept numeric input
                        if (newValue.all { it.isDigit() || it == '.' }) {
                            bpm = newValue
                        }
                    },
                    label = { Text("BPM/Tempo") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Spacer(modifier = Modifier.height(16.dp))


            Box(
                modifier = Modifier.fillMaxWidth()
                    .border(BorderStroke(1.dp, Color.Black)) // Add border here
            ) {
                //Spacer(modifier = Modifier.height(16.dp))

                // File input field
                Row(verticalAlignment = Alignment.CenterVertically) {
                    BasicTextField(
                        value = fileUri ?: " Kick",
                        onValueChange = {}, // File path is read-only
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp),
                    )
                    Button(onClick = { openFilePicker.launch("image/*") }) {
                        Text("Select File")
                    }
                }
            }
            Box(
                modifier = Modifier.fillMaxWidth()
                    .border(BorderStroke(1.dp, Color.Black)) // Add border here
            ) {
                //Spacer(modifier = Modifier.height(16.dp))

                // File input field
                Row(verticalAlignment = Alignment.CenterVertically) {
                    BasicTextField(
                        value = fileUri ?: " Snare",
                        onValueChange = {}, // File path is read-only
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp),
                    )
                    Button(onClick = { openFilePicker.launch("image/*") }) {
                        Text("Select File")
                    }
                }
            }
            Box(
                modifier = Modifier.fillMaxWidth()
                    .border(BorderStroke(1.dp, Color.Black)) // Add border here
            ) {
                //Spacer(modifier = Modifier.height(16.dp))

                // File input field
                Row(verticalAlignment = Alignment.CenterVertically) {
                    BasicTextField(
                        value = fileUri ?: " HitHat",
                        onValueChange = {}, // File path is read-only
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp),
                    )
                    Button(onClick = { openFilePicker.launch("image/*") }) {
                        Text("Select File")
                    }
                }
            }
            Box(
                modifier = Modifier.fillMaxWidth()
                    .border(BorderStroke(1.dp, Color.Black)) // Add border here
            ) {
                //Spacer(modifier = Modifier.height(16.dp))

                // File input field
                Row(verticalAlignment = Alignment.CenterVertically) {
                    BasicTextField(
                        value = fileUri ?: " Cymbal",
                        onValueChange = {}, // File path is read-only
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp),
                    )
                    Button(onClick = { openFilePicker.launch("image/*") }) {
                        Text("Select File")
                    }
                }
            }
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(16.dp))

                    // Your form fields here...

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Spacer(modifier = Modifier.weight(1f)) // This spacer will push the button to the right
                        Button(
                            onClick = {
                                // Handle form submission here
                            }
                        ) {
                            Text("Submit")
                        }
                    }
                }
            }
        }




@Preview(showBackground = true)
@Composable
fun PreviewGrooveMachineForm() {
    GroovemachineTheme {
        GrooveMachineForm()
    }
}
