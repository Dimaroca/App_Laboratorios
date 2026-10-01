package plat.labs.rickymorty

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import plat.labs.rickymorty.navigation.*
import plat.labs.rickymorty.ui.components.BottomNavBar
import plat.labs.rickymorty.ui.pages.character.Details
import plat.labs.rickymorty.ui.pages.character.CharactersScreen
import plat.labs.rickymorty.ui.pages.location.LocationDetail
import plat.labs.rickymorty.ui.pages.location.Location
import plat.labs.rickymorty.ui.pages.profile.Profile

@Composable
fun MainScreen(onLogout: () -> Unit) {
    val innerNavController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavBar(navController = innerNavController) }
    ) { paddingValues ->
        NavHost(
            navController = innerNavController,
            startDestination = CharactersGraph,
            modifier = androidx.compose.ui.Modifier.padding(paddingValues)
        ) {
            navigation<CharactersGraph>(startDestination = CharactersList) {
                composable<CharactersList> {
                    CharactersScreen(
                        onCharacterClick = { id ->
                            innerNavController.navigate(CharacterDetail(characterId = id))
                        }
                    )
                }
                composable<CharacterDetail> { backStackEntry ->
                    val args: CharacterDetail = backStackEntry.toRoute()
                    Details(
                        characterId = args.characterId,
                        onBackClick = { innerNavController.popBackStack() }
                    )
                }
            }

            navigation<LocationsGraph>(startDestination = LocationsList) {
                composable<LocationsList> {
                    Location(
                        onLocationClick = { id ->
                            innerNavController.navigate(LocationDetail(locationId = id))
                        }
                    )
                }
                composable<LocationDetail> { backStackEntry ->
                    val args: LocationDetail = backStackEntry.toRoute()
                    LocationDetail(
                        locationId = args.locationId,
                        onBackClick = { innerNavController.popBackStack() }
                    )
                }
            }

            composable<Profile> {
                Profile(onLogout = onLogout)
            }
        }
    }
}