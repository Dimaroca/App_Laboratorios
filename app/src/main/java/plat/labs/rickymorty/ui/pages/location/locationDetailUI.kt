package plat.labs.rickymorty.ui.pages.location

import plat.labs.rickymorty.Location

data class LocationDetailUi(
    val isLoading: Boolean = false,
    val data: Location? = null,
    val hasError: Boolean = false
)