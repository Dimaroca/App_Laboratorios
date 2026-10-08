package plat.labs.rickymorty.ui.pages.location

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import plat.labs.rickymorty.Location
import plat.labs.rickymorty.ui.components.Error
import plat.labs.rickymorty.ui.components.Cargando

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Location(
    onLocationClick: (Int) -> Unit,
    viewModel: LocationsViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Locations") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        when {
            uiState.isLoading -> {
                Cargando(
                    paddingValues = paddingValues,
                    onClick = { viewModel.LoadingClick() }
                )
            }
            uiState.hasError -> {
                Error(
                    paddingValues = paddingValues,
                    onRetry = { viewModel.Load() }
                )
            }
            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    items(uiState.data) { location ->
                        LocationRow(location = location, onClick = { onLocationClick(location.id) })
                        Divider()
                    }
                }
            }
        }
    }
}

@Composable
private fun LocationRow(location: Location, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Text(location.name, style = MaterialTheme.typography.titleMedium)
        Text(location.type, style = MaterialTheme.typography.bodyMedium)
    }
}