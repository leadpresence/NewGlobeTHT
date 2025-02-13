package com.leadpresence.newglobetht.presentation.ui.pupils


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.leadpresence.newglobetht.presentation.ui.common.state.UiState
import com.leadpresence.newglobetht.presentation.ui.pupils.components.PupilItem
import androidx.compose.runtime.getValue
import com.leadpresence.newglobetht.presentation.ui.common.components.ScreenHeader


@Composable
fun PupilsScreen(
    viewModel: PupilsViewModel,
    onPupilClick: (Long) -> Unit,
    onBackClick: () -> Unit

) {
    val uiState by viewModel.uiState.collectAsState()

    when (val state = uiState) {
        is UiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is UiState.Success -> {
            LazyColumn {

                item {
                    ScreenHeader(
                        title = "Pupils",
                        subtitle = "View all registered pupils",
                        onBackClick = onBackClick,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                items(state.data) { pupil ->
                    PupilItem(
                        pupil = pupil,
                        onTabSelected = {},
                    )
                }
                item {
                    Button(
                        onClick = { viewModel.loadNextPage() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text("Load More")
                    }
                }
            }
        }

        is UiState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = state.message,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}