package com.leadpresence.newglobetht.presentation.ui.pupildetail

import androidx.lifecycle.ViewModel
import com.leadpresence.newglobetht.domain.repository.PupilRepository

class EditViewModel(
    private val pupilId: Long,
    private val pupilRepository: PupilRepository
) : ViewModel()