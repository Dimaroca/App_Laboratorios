package plat.labs.rickymorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import plat.labs.rickymorty.navigation.CharacterDetail
import plat.labs.rickymorty.navigation.CharactersGraph
import plat.labs.rickymorty.navigation.CharactersList
import plat.labs.rickymorty.navigation.LocationDetail
import plat.labs.rickymorty.navigation.LocationsGraph
import plat.labs.rickymorty.navigation.LocationsList
import plat.labs.rickymorty.navigation.Login
import plat.labs.rickymorty.navigation.Main
import plat.labs.rickymorty.navigation.Profile
import plat.labs.rickymorty.ui.components.BottomNavBar
import plat.labs.rickymorty.ui.pages.character.CharacterScreen
import plat.labs.rickymorty.ui.pages.character.Details
import plat.labs.rickymorty.ui.pages.location.Location
import plat.labs.rickymorty.ui.pages.location.LocationDetail as LocationDetailScreen
import plat.labs.rickymorty.ui.pages.login.Login
import plat.labs.rickymorty.ui.pages.profile.Profile as ProfileScreen
import plat.labs.rickymorty.ui.theme.RickYMortyTheme
import plat.labs.rickymorty.ui.pages.home.Home

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
        startDestination = Login
    ) {
        composable<Login> {
            Login(
                onNavigateToMain = {
                    outerNavController.navigate(Main) {
                        popUpTo(Login) { inclusive = true }
                    }
                }
            )
        }

        composable<Main> {
            Home(
                onLogout = {
                    outerNavController.navigate(Login) {
                        popUpTo(outerNavController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}