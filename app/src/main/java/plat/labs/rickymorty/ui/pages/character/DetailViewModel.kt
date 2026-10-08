package plat.labs.rickymorty.ui.pages.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import plat.labs.rickymorty.ui.CharacterDb

class DetailViewModel(private val characterId: Int) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailUi())
    val uiState: StateFlow<DetailUi> = _uiState.asStateFlow()

    init {
        Load()
    }

    fun Load() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, hasError = false)
            delay(2000)
            val character = CharacterDb().getCharacterById(characterId)
            _uiState.value = _uiState.value.copy(isLoading = false, data = character)
        }
    }

    fun LoadingClick() {
        _uiState.value = _uiState.value.copy(isLoading = false, hasError = true)
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val characterId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return DetailViewModel(characterId) as T
        }
    }
}