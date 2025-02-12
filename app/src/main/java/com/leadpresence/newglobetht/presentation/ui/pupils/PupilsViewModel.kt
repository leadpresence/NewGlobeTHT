package com.leadpresence.newglobetht.presentation.ui.pupils
import androidx.lifecycle.ViewModel
import com.leadpresence.newglobetht.domain.model.Pupil
import com.leadpresence.newglobetht.domain.repository.PupilRepository
import com.leadpresence.newglobetht.presentation.ui.common.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class PupilsViewModel(
    private val repository: PupilRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<Pupil>>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    private var currentPage = 1
    private val pageSize = 20

    init {
        loadPupils()
    }

    fun loadPupils() {
//        viewModelScope.launch {
//            try {
//                repository.getPupils(currentPage, pageSize)
//                    .collect { pupils ->
//                        _uiState.value = UiState.Success(pupils)
//                    }
//            } catch (e: Exception) {
//                _uiState.value = UiState.Error(e.message ?: "Unknown error")
//            }
//        }
    }

    fun loadNextPage() {
        currentPage++
        loadPupils()
    }
}