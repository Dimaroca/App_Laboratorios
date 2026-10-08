package plat.labs.rickymorty.ui.pages.location

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import plat.labs.rickymorty.ui.components.Detail
import plat.labs.rickymorty.ui.components.Error
import plat.labs.rickymorty.ui.components.Cargando

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationDetail(
    locationId: Int,
    onBackClick: () -> Unit,
    viewModel: LocationDetailViewModel = viewModel(
        factory = LocationDetailViewModel.Factory(locationId)
    )
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Location details") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
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
            uiState.hasError || uiState.data == null -> {
                Error(
                    paddingValues = paddingValues,
                    onRetry = { viewModel.Load() }
                )
            }
            else -> {
                val location = uiState.data!!
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(location.name, style = MaterialTheme.typography.titleLarge)

                    Spacer(modifier = Modifier.height(24.dp))

                    Detail(label = "ID:", value = location.id.toString())
                    Detail(label = "Type:", value = location.type)
                    Detail(label = "Dimensions:", value = location.dimension)
                }
            }
        }
    }
}