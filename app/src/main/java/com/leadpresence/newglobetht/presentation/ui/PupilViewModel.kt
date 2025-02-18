package com.leadpresence.newglobetht.presentation.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.leadpresence.newglobetht.domain.model.Pupil
import com.leadpresence.newglobetht.domain.repository.PupilRepository
import com.leadpresence.newglobetht.domain.usecase.PupilUseCases
import kotlinx.coroutines.launch

@SuppressLint("CheckResult")
class PupilViewModel(
//    private val useCases: PupilUseCases
    private val repository: PupilRepository
) : ViewModel() {

        // Pupils paging flow
    val pupilPagingFlow = repository.getPupils()
        .cachedIn(viewModelScope)  // Keep alive across recompositions

    // Other operations...
//    fun refresh() {
//        pupilPagingFlow.
//    }
    fun createPupil(pupil: Pupil) = viewModelScope.launch {
        repository.createPupil(pupil)
    }

    fun updatePupil(pupil: Pupil) = viewModelScope.launch {
        repository.updatePupil(pupil)
    }

    fun deletePupil(pupilId: Int) = viewModelScope.launch {
        repository.deletePupil(pupilId)
    }

//    fun getPupil(pupilId: Int) = viewModelScope.launch {
//        useCases.GetPupilUseCase().invoke(pupilId)
//    }
//
//    fun createPupil(pupil: Pupil) = viewModelScope.launch {
//        useCases.CreatePupilUseCase().invoke(pupil)
//    }
//
//    fun updatePupil(pupil: Pupil) = viewModelScope.launch {
//        useCases.UpdatePupilUseCase().invoke(pupil)
//    }
//
//    fun deletePupil(pupilId: Int) = viewModelScope.launch {
//        useCases.DeletePupilUseCase().invoke(pupilId)
//    }
}