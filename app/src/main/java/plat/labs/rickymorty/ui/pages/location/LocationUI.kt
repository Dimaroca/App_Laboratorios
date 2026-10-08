package plat.labs.rickymorty.ui.pages.location

import plat.labs.rickymorty.Location

data class LocationUi(
    val isLoading: Boolean = false,
    val data: List<Location> = emptyList(),
    val hasError: Boolean = false
)