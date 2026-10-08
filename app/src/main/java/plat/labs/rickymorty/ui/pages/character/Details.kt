package plat.labs.rickymorty.ui.pages.character

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import plat.labs.rickymorty.ui.components.Detail
import plat.labs.rickymorty.ui.components.Error
import plat.labs.rickymorty.ui.components.Cargando

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Details(
    characterId: Int,
    onBackClick: () -> Unit,
    viewModel: DetailViewModel = viewModel(
        factory = DetailViewModel.Factory(characterId)
    )
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Characters details") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.background
                        )
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
                val character = uiState.data!!
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = character.image,
                        contentDescription = character.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(160.dp)
                            .clip(CircleShape)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(character.name, style = MaterialTheme.typography.titleLarge)

                    Spacer(modifier = Modifier.height(24.dp))

                    Detail(label = "Species:", value = character.species)
                    Detail(label = "Status:", value = character.status)
                    Detail(label = "Gender:", value = character.gender)
                }
            }
        }
    }
}