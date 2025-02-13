package com.leadpresence.newglobetht.presentation.ui.addPupil

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.leadpresence.newglobetht.presentation.ui.common.components.ScreenHeader

@Composable
fun AddPupilScreen(
    viewModel: AddPupilViewModel,
    onSave:()->Unit,
    onBackClick: () -> Unit
    ) {

    Column {
        ScreenHeader(
            title = "Add New Pupil",
            subtitle = "Fill in the details to add a new pupil",
            onBackClick =onBackClick
        )
        // Rest of the screen content
    }
}