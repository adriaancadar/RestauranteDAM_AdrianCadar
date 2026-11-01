package com.example.restaurantedam_adriancadar

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.restaurantedam_adriancadar.ui.theme.RestauranteDAM_AdrianCadarTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.restaurantedam_adriancadar.ui.ViewModelRestaurante
import java.time.LocalDate
import androidx.lifecycle.viewmodel.compose.viewModel



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RestauranteDAM_AdrianCadarTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TituloRestaurante(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
//Titulo y Card Completo
fun TituloRestaurante(
    modifier: Modifier = Modifier,
    viewModel: ViewModelRestaurante = viewModel()) {
    val productoSeleccionado by viewModel.productoSeleccionado.collectAsState()
    val cantidad by viewModel.cantidad.collectAsState()


    val radioOptions = listOf(stringResource(R.string.hamburguesa), stringResource(R.string.pizza))
    var textoCantidad by remember { mutableStateOf("") }
    val imagenes = mapOf(
        "Hamburguesa" to R.drawable.hamburguesa,
        "Pizza" to R.drawable.pizza
    )
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }



    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.restaurante_dam),
            fontSize = 24.sp,
            color = Color.Blue
        )
        Text(
            modifier = Modifier.padding(top = 20.dp),
            text = stringResource(R.string.elige_el_producto),
            fontSize = 18.sp,
            color = Color.Blue
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFB8BFE3))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectableGroup()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                //RADIO BUTTON
                radioOptions.forEach { text ->
                    Column(
                        modifier = Modifier.selectable(
                            selected = (text == selectedOption),
                            onClick = { onOptionSelected(text) },
                            role = Role.RadioButton
                        ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = imagenes[text]!!),
                            contentDescription = text,
                            modifier = Modifier.size(60.dp)
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = (text == selectedOption),
                                onClick = null
                            )
                            Text(
                                text = text,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }

            }
            when (selectedOption) {
                "Hamburguesa" -> CamposHamburguesa()
                "Pizza" -> CamposPizza()
            }
        }

        Row(modifier = modifier.padding(16.dp)) {
            TextField(
                modifier = modifier.width(150.dp),
                value = textoCantidad,
                onValueChange = {textoCantidad = it },
                label = { Text("Cantidad") }


            )
            CampoFecha(modifier = modifier)
        }

        Row(modifier = modifier.padding(20.dp)) {

            Button( modifier = modifier.padding(0.dp,0.dp,15.dp,0.dp),
                onClick = { null}
            ) {
                Text(text = "Añadir")
            }
            Button(modifier = modifier,
                onClick = { null}
            ) {
                Text(text = "Cancelar")
            }

        }

    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CampoFecha(modifier: Modifier = Modifier) {
    val fecha = LocalDate.now().toString()   // formato 2026-11-01

    OutlinedTextField(
        value = fecha,
        onValueChange = {},
        label = { Text("Fecha") },
        enabled = false,
        singleLine = true,
        modifier = modifier
    )
}

@Composable
fun CamposHamburguesa(modifier: Modifier = Modifier){

    val radioOptions = listOf("Pollo","Ternera")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
    var checked by remember { mutableStateOf(true) }
    // Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
    Column(modifier.selectableGroup()) {


        radioOptions.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = null // null recommended for accessibility with screen readers
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically,) {
            Checkbox(
                checked = checked,
                onCheckedChange = {checked = it}
            )
            Text("Queso")
        }

    }

}
@Composable
fun CamposPizza(modifier : Modifier = Modifier){
    val radioOptions = listOf("Pequeña","Mediana","Grande")
    var checked by remember { mutableStateOf(true) }
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
    // Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
    Column(modifier.selectableGroup()) {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = null // null recommended for accessibility with screen readers
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Romana")
            Switch(
                checked = checked,
                modifier = modifier.padding(5.dp,0.dp,5.dp,0.dp),
                onCheckedChange = {
                    checked = it
                }

            )
            Text("Margarita")
        }
    }
}