package com.leadpresence.newglobetht.presentation.ui.pupils
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.leadpresence.newglobetht.domain.model.Pupil
import com.leadpresence.newglobetht.domain.repository.PupilRepository
import com.leadpresence.newglobetht.presentation.ui.common.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class PupilsViewModel(
    private val pupilRepository: PupilRepository

) : ViewModel() {
    private val _selectedPupilId = MutableStateFlow<Long?>(null)
    val selectedPupilId = _selectedPupilId.asStateFlow()

    private val _pupilState = MutableStateFlow<UiState<Pupil>>(UiState.Loading)
    val pupilState = _pupilState.asStateFlow()

    private val _uiState = MutableStateFlow<UiState<PagingData<Pupil>>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _pupils = MutableStateFlow<PagingData<Pupil>>(PagingData.empty())
    val pupils = _pupils.asStateFlow()
    init {
        getPupils()
    }

    private fun getPupils() {
        viewModelScope.launch {
            try {
                val pager = pupilRepository.getPupils()
                pager.flow
                    .map { UiState.Success(it) as UiState<PagingData<Pupil>> }
                    .onStart { emit(UiState.Loading) }
                    .catch { emit(UiState.Error(it.message ?: "Unknown error")) }
                    .collect { state ->
                        _uiState.value = state
                    }
            } catch (e: Exception) {
                _pupilState.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }

//    init {
//        loadPupils()
//    }
//
//    private fun loadPupils() {
//        viewModelScope.launch {
//            try {
//                _uiState.update { UiState.Loading }
//
//                // Now getPupils is called inside a coroutine
//                val pager = pupilRepository.getPupils()
//                pager.flow
//                    .collect { pagingData ->
//                        _uiState.update { UiState.Success(pagingData) }
//                    }
//            } catch (e: Exception) {
//                _uiState.update { UiState.Error(e.message ?: "Unknown error occurred") }
//            }
//        }
//    }

    fun retry() {
//        loadPupils()
        getPupils()
    }
    fun selectPupil(id: Long) {
        _selectedPupilId.value = id
    }

    fun clearSelection() {
        _selectedPupilId.value = null
    }

}