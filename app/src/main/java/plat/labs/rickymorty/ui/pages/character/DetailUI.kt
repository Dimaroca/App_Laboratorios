package plat.labs.rickymorty.ui.pages.character

import plat.labs.rickymorty.ui.Character

data class DetailUi(
    val isLoading: Boolean = false,
    val data: Character? = null,
    val hasError: Boolean = false
)