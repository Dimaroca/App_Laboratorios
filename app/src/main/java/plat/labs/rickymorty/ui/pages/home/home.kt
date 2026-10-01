package plat.labs.rickymorty.ui.pages.home

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
import plat.labs.rickymorty.navigation.Profile
import plat.labs.rickymorty.ui.components.BottomNavBar
import plat.labs.rickymorty.ui.pages.character.CharactersScreen
import plat.labs.rickymorty.ui.pages.character.Details
import plat.labs.rickymorty.ui.pages.location.LocationDetail
import plat.labs.rickymorty.ui.pages.location.Location
import plat.labs.rickymorty.ui.pages.profile.Profile

@Composable
fun Home(onLogout: () -> Unit) {
    val innerNavController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavBar(navController = innerNavController) }
    ) { paddingValues ->
        NavHost(
            navController = innerNavController,
            startDestination = CharactersGraph,
            modifier = Modifier.padding(paddingValues)
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