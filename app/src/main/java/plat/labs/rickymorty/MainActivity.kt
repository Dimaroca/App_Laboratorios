package plat.labs.rickymorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import plat.labs.rickymorty.navigation.Login
import plat.labs.rickymorty.navigation.Main
import plat.labs.rickymorty.navigation.Splash
import plat.labs.rickymorty.ui.pages.home.Home
import plat.labs.rickymorty.ui.pages.login.Login as LoginScreen
import plat.labs.rickymorty.ui.pages.splash.Splash as SplashScreen
import plat.labs.rickymorty.ui.theme.RickYMortyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickYMortyTheme {
                RickAndMortyApp()
            }
        }
    }
}

@Composable
fun RickAndMortyApp() {
    val outerNavController = rememberNavController()

    NavHost(
        navController = outerNavController,
        startDestination = Splash
    ) {
        composable<Splash> {
            SplashScreen(
                onNavigateToLogin = {
                    outerNavController.navigate(Login) {
                        popUpTo(outerNavController.graph.id) { inclusive = true }
                    }
                },
                onNavigateToMain = {
                    outerNavController.navigate(Main) {
                        popUpTo(outerNavController.graph.id) { inclusive = true }
                    }
                }
            )
        }

        composable<Login> {
            LoginScreen(
                onNavigateToMain = {
                    outerNavController.navigate(Main) {
                        popUpTo(outerNavController.graph.id) { inclusive = true }
                    }
                }
            )
        }

        composable<Main> {
            Home(
                onLogout = {
                    outerNavController.navigate(Login) {
                        popUpTo(outerNavController.graph.id) { inclusive = true }
                    }
                }
            )
        }
    }
}