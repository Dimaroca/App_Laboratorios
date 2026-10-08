package plat.labs.rickymorty.ui.pages.character

import plat.labs.rickymorty.ui.Character

data class CharactersUi(
    val isLoading: Boolean = false,
    val data: List<Character> = emptyList(),
    val hasError: Boolean = false
)