package plat.labs.rickymorty.ui.pages.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import plat.labs.rickymorty.R
import plat.labs.rickymorty.data.Preferences

@Composable
fun Login(onNavigateToMain: () -> Unit){
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val preferences = remember { Preferences(context) }

    var name by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo Rick y Morty",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    scope.launch {
                        preferences.save(name.trim())
                        onNavigateToMain()
                    }
                },
                enabled = name.isNotBlank(),
                modifier = Modifier.fillMaxWidth(0.6f)
            ) {
                Text("Empezar")
            }
        }
    }
}