package plat.labs.rickymorty.ui.pages.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import plat.labs.rickymorty.ui.CharacterDb

class CharactersViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CharactersUi())
    val uiState: StateFlow<CharactersUi> = _uiState.asStateFlow()

    init {
        Load()
    }

    fun Load() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, hasError = false)
            delay(4000)
            val characters = CharacterDb().getAllCharacters()
            _uiState.value = _uiState.value.copy(isLoading = false, data = characters)
        }
    }

    fun LoadingClick() {
        _uiState.value = _uiState.value.copy(isLoading = false, hasError = true)
    }
}