package com.leadpresence.newglobetht.presentation.ui.pupildetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.leadpresence.newglobetht.presentation.ui.common.components.ScreenHeader
import com.leadpresence.newglobetht.presentation.ui.common.state.UiState


@Composable
fun PupilDetailScreen(
    viewModel: PupilDetailViewModel,

    pupilId: Long,
    onEditClick: () -> Unit,
    onBackClick: () -> Unit,

) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(pupilId) {
        viewModel.loadPupil(pupilId)
    }

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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Column {
                    ScreenHeader(
                        title = "Pupil Details",
                        subtitle = "View detailed information about this pupil",
                        onBackClick = onBackClick
                    )
                    // Rest of the screen content
                }
                AsyncImage(
                    model = state.data.image,
                    contentDescription = "Student Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = state.data.name,
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Location: ${state.data.latitude}, ${state.data.longitude}",
                    style = MaterialTheme.typography.bodyLarge
                )
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