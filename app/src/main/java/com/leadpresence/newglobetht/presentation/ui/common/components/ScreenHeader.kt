package com.leadpresence.newglobetht.presentation.ui.common.components



import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun ScreenHeader(
    title: String,
    subtitle: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    showBackButton: Boolean = true
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp, bottom = 24.dp)
    ) {
        if (showBackButton) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "Navigate back",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}



// Example Usage in screens
@Composable
fun DetailsScreen(
    pupilId: Long,
    onBackClick: () -> Unit
) {
    Column {
        ScreenHeader(
            title = "Pupil Details",
            subtitle = "View detailed information about this pupil",
            onBackClick = onBackClick
        )
        // Rest of the screen content
    }
}

@Composable
fun EditScreen(
    pupilId: Long,
    onBackClick: () -> Unit
) {
    Column {
        ScreenHeader(
            title = "Edit Pupil",
            subtitle = "Update pupil information using the form below",
            onBackClick = onBackClick
        )
        // Rest of the screen content
    }
}

@Composable
fun AddPupilScreen(
    onBackClick: () -> Unit
) {
    Column {
        ScreenHeader(
            title = "Add New Pupil",
            subtitle = "Fill in the details to add a new pupil",
            onBackClick = onBackClick
        )
        // Rest of the screen content
    }
}