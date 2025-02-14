package com.leadpresence.newglobetht.presentation.ui.pupildetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.leadpresence.newglobetht.domain.model.Pupil
import com.leadpresence.newglobetht.presentation.ui.common.components.ScreenHeader
import com.leadpresence.newglobetht.presentation.ui.common.state.UiState
import com.leadpresence.newglobetht.presentation.ui.pupils.PupilsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PupilDetailScreen(
    viewModel: PupilDetailViewModel,
//    pupilviewModel: PupilsViewModel,

    pupilId: Long,
    onEditClick: () -> Unit,
    onBackClick: () -> Unit,
) {
//    val uiState by pupilviewModel.selectedPupilId.collectAsState()
//    LaunchedEffect(pupilId) {
//        viewModel.getPupil(pupilId)
//    }

//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Pupil Details") },
//                navigationIcon = {
//                    IconButton(onClick = {
//                        viewModel.clearSelection()
//                        onBackClick()
//                    }) {
//                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
//                    }
//                }
//            )
//        }
//    )
//    {
//        paddingValues ->
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues)
//        ) {
//            when (uiState) {
//                is UiState.Initial -> Unit
//                is UiState.Loading -> {
//                    CircularProgressIndicator(
//                        modifier = Modifier.align(Alignment.Center)
//                    )
//                }
//                is UiState.Success -> {
//                    val pupil = (uiState as UiState.Success).pupil
//                    PupilDetailContent(pupil = pupil)
//                }
//                is UiState.Error -> {
//                    Text(
//                        text = (uiState as UiState.Error).message,
//                        color = MaterialTheme.colorScheme.error,
//                        modifier = Modifier
//                            .align(Alignment.Center)
//                            .padding(16.dp)
//                    )
//                }
//            }
//        }
//    }



//    val uiState by viewModel.uiState.collectAsState()
//
//
//
//    when (val state = uiState) {
//        is UiState.Loading -> {
//            Box(
//                modifier = Modifier.fillMaxSize(),
//                contentAlignment = Alignment.Center
//            ) {
//                CircularProgressIndicator()
//            }
//        }
//        is UiState.Success -> {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(16.dp)
//            ) {
//                Column {
//                    ScreenHeader(
//                        title = "Pupil Details",
//                        subtitle = "View detailed information about this pupil",
//                        onBackClick = onBackClick
//                    )
//                    // Rest of the screen content
//                }
//                AsyncImage(
//                    model = state.data.image,
//                    contentDescription = "Student Image",
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(200.dp)
//                        .clip(RoundedCornerShape(8.dp))
//                )
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                Text(
//                    text = state.data.name,
//                    style = MaterialTheme.typography.headlineMedium
//                )
//
//                Spacer(modifier = Modifier.height(8.dp))
//
//                Text(
//                    text = "Location: ${state.data.latitude}, ${state.data.longitude}",
//                    style = MaterialTheme.typography.bodyLarge
//                )
//            }
//        }
//        is UiState.Error -> {
//            Box(
//                modifier = Modifier.fillMaxSize(),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = state.message,
//                    color = MaterialTheme.colorScheme.error
//                )
//            }
//        }
//    }
}
//
@Composable
private fun PupilDetailContent(pupil: Pupil) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = pupil.name,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "REF: ${pupil.pupilId}",
            style = MaterialTheme.typography.bodyLarge
        )
        // Add more pupil details as needed
    }
}