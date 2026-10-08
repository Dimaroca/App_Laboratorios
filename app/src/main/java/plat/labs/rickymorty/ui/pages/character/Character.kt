package plat.labs.rickymorty.ui.pages.character

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import plat.labs.rickymorty.ui.Character
import plat.labs.rickymorty.ui.components.Error
import plat.labs.rickymorty.ui.components.Cargando

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterScreen(
    onCharacterClick: (Int) -> Unit,
    viewModel: CharactersViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Characters") },
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
                    items(uiState.data) { character ->
                        CharacterRow(
                            character = character,
                            onClick = { onCharacterClick(character.id) }
                        )
                        Divider()
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterRow(character: Character, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = character.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(character.name, style = MaterialTheme.typography.titleMedium)
            Text(
                "${character.species} - ${character.status}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}