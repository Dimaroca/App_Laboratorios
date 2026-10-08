package plat.labs.rickymorty.ui.pages.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import plat.labs.rickymorty.LocationDb

class LocationDetailViewModel(private val locationId: Int) : ViewModel() {

    private val _uiState = MutableStateFlow(LocationDetailUi())
    val uiState: StateFlow<LocationDetailUi> = _uiState.asStateFlow()

    init {
        Load()
    }

    fun Load() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, hasError = false)
            delay(2000)
            val location = LocationDb().getLocationById(locationId)
            _uiState.value = _uiState.value.copy(isLoading = false, data = location)
        }
    }

    fun LoadingClick() {
        _uiState.value = _uiState.value.copy(isLoading = false, hasError = true)
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val locationId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LocationDetailViewModel(locationId) as T
        }
    }
}