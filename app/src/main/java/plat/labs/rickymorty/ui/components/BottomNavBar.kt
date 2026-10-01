package plat.labs.rickymorty.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import plat.labs.rickymorty.navigation.CharactersGraph
import plat.labs.rickymorty.navigation.LocationsGraph
import plat.labs.rickymorty.navigation.Profile

@Composable
fun BottomNavBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        NavigationBarItem(
            selected = currentDestination?.hierarchy?.any { it.hasRoute<CharactersGraph>() } == true,
            onClick = {
                navController.navigate(CharactersGraph) {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = { Icon(Icons.Filled.Group, contentDescription = "Characters") },
            label = { Text("Characters") }
        )
        NavigationBarItem(
            selected = currentDestination?.hierarchy?.any { it.hasRoute<LocationsGraph>() } == true,
            onClick = {
                navController.navigate(LocationsGraph) {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = { Icon(Icons.Filled.Public, contentDescription = "Locations") },
            label = { Text("Locations") }
        )
        NavigationBarItem(
            selected = currentDestination?.hierarchy?.any { it.hasRoute<Profile>() } == true,
            onClick = {
                navController.navigate(Profile) {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
            label = { Text("Profile") }
        )
    }
}