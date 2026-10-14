package plat.labs.rickymorty.ui.pages.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import plat.labs.rickymorty.data.Preferences
import plat.labs.rickymorty.ui.components.Detail

@Composable
fun Profile(onLogout: () -> Unit) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val preferences = remember { Preferences(context) }
    val name by preferences.user.collectAsState(initial = null)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Filled.AccountCircle,
            contentDescription = "Foto de perfil",
            modifier = Modifier.size(120.dp),
            tint = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        Detail(label = "Nombre:", value = name ?: "")

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                scope.launch {
                    preferences.clear()
                    onLogout()
                }
            }
        ) {
            Text("Cerrar sesión")
        }
    }
}