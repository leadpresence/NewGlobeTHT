package com.leadpresence.newglobetht.presentation.ui.pupildetail

import androidx.lifecycle.ViewModel
import com.leadpresence.newglobetht.domain.model.Pupil
import com.leadpresence.newglobetht.presentation.ui.common.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PupilDetailViewModel(
//    private val repository: PupilRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<Pupil>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()
//
    fun loadPupil(id: Long) {
//        viewModelScope.launch {
//            try {
//                repository.getPupil(id)
//                    .collect { pupil ->
//                        _uiState.value = UiState.Success(pupil)
//                    }
//            } catch (e: Exception) {
//                _uiState.value = UiState.Error(e.message ?: "Unknown error")
//            }
//        }
    }
}