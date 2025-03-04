package com.leadpresence.newglobetht.presentation.ui.pupildetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leadpresence.newglobetht.domain.model.Pupil
import com.leadpresence.newglobetht.domain.repository.PupilRepository
//import com.leadpresence.newglobetht.domain.usecase.GetPupilUseCase
import com.leadpresence.newglobetht.presentation.ui.common.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class PupilDetailViewModel(
    private val pupilId: Long,
    private val pupilRepository: PupilRepository

) : ViewModel() {


//    private val _uiState = MutableStateFlow<UiState<Pupil>>(UiState.Loading)
//    val uiState = _uiState.asStateFlow()
//
//
//    private val _pupilState = MutableStateFlow<UiState<Pupil>>(UiState.Loading)
//    val pupilState = _pupilState.asStateFlow()

//    fun getPupil(id: Long) {
//        viewModelScope.launch {
//            _pupilState.value = UiState.Loading
//            getPupilUseCase(id).collect { result ->
//                _pupilState.value = when {
//                    result.isSuccess -> UiState.Success(result.getOrNull()!!)
//                    result.isFailure -> UiState.Error(
//                        result.exceptionOrNull()?.message ?: "Unknown error"
//                    )
//                }
//            }
//        }
//    }
}