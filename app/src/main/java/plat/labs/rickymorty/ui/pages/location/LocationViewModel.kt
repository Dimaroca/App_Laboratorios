package plat.labs.rickymorty.ui.pages.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import plat.labs.rickymorty.LocationDb

class LocationsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LocationUi())
    val uiState: StateFlow<LocationUi> = _uiState.asStateFlow()

    init {
        Load()
    }

    fun Load() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, hasError = false)
            delay(4000) // Listado de ubicaciones = 4 segundos
            val locations = LocationDb().getAllLocations()
            _uiState.value = _uiState.value.copy(isLoading = false, data = locations)
        }
    }

    fun LoadingClick() {
        _uiState.value = _uiState.value.copy(isLoading = false, hasError = true)
    }
}