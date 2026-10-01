package plat.labs.rickymorty.navigation

import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
object Main

@Serializable
object CharactersGraph

@Serializable
object CharactersList

@Serializable
data class CharacterDetail(val characterId: Int)

@Serializable
object LocationsGraph

@Serializable
object LocationsList

@Serializable
data class LocationDetail(val locationId: Int)

@Serializable
object Profile