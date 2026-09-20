package plat.labs.rickymorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import plat.labs.rickymorty.navigation.CharacterDetail
import plat.labs.rickymorty.navigation.Characters
import plat.labs.rickymorty.navigation.Login
import plat.labs.rickymorty.ui.pages.Details
import plat.labs.rickymorty.ui.pages.CharactersScreen
import plat.labs.rickymorty.ui.pages.Login
import plat.labs.rickymorty.ui.theme.RickYMortyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickYMortyTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Login
    ) {
        composable<Login> {
            Login(
                onNavigateToCharacters = {
                    navController.navigate(Characters) {
                        popUpTo(Login) { inclusive = true }
                    }
                }
            )
        }

        composable<Characters> {
            CharactersScreen(
                onCharacterClick = { characterId ->
                    navController.navigate(CharacterDetail(characterId = characterId))
                }
            )
        }

        composable<CharacterDetail> { backStackEntry ->
            val characterDetail: CharacterDetail = backStackEntry.toRoute()
            Details(
                characterId = characterDetail.characterId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
