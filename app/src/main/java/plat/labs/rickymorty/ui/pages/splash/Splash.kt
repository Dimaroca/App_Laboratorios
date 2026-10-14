package plat.labs.rickymorty.ui.pages.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.flow.first
import plat.labs.rickymorty.data.Preferences

@Composable
fun Splash(
    onNavigateToLogin: () -> Unit,
    onNavigateToMain: () -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        val userPreferences = Preferences(context)
        val name = userPreferences.user.first()
        if (!name.isNullOrBlank()) {
            onNavigateToMain()
        } else {
            onNavigateToLogin()
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}