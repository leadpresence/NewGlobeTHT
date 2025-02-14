package com.leadpresence.newglobetht.presentation.ui.pupils


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.leadpresence.newglobetht.domain.model.Pupil
import com.leadpresence.newglobetht.presentation.ui.common.components.ScreenHeader


@Composable
fun PupilsScreen(
    viewModel: PupilsViewModel,
    onPupilClick: (Long) -> Unit,
    onBackClick: () -> Unit

) {

//    val uiState by viewModel.uiState.collectAsState()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val pupils = viewModel.pupils.collectAsLazyPagingItems()


    Box(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            is UiState.Initial -> {
                // Show nothing or initial state
            }
            is UiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            is UiState.Success -> {
                LazyColumn {
                    item {
                        ScreenHeader(
                            title = "Pupils",
                            subtitle = "Registered pupils",
                            onBackClick = onBackClick,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    items(
                        count = pupils.itemCount,
                        key = { index ->
                            val pupil = pupils[index]
                            pupil?.id ?: index
                        }
                    ) { index ->
                        val pupil = pupils[index]
                        pupil?.let {
                            PupilItem(
                                pupil = it,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            )
                        }
                    }

                    // Add loading footer
                    when (pupils.loadState.append) {
                        is LoadState.Loading -> {
                            item { LoadingFooter() }
                        }
                        is LoadState.Error -> {
                            item {
                                ErrorFooter(
                                    onRetry = { pupils.retry() }
                                )
                            }
                        }
                        else -> {}
                    }
                }
            }
            is UiState.Error -> {
                ErrorMessage(
                    message = (uiState as UiState.Error).message,
                    onRetry = { viewModel.retry() },
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }



    }

}

@Composable
private fun PupilItem(
    pupil: Pupil,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = pupil.name,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Add other pupil details here
        }
    }
}

@Composable
private fun LoadingFooter() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun ErrorFooter(
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Error loading more items",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.error
        )
        Button(
            onClick = onRetry,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Retry")
        }
    }
}

@Composable
private fun ErrorMessage(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center
        )
        Button(
            onClick = onRetry,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Retry")
        }
    }
}