package com.dd.hospitalslist.ui.hospitals

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dd.hospitalslist.data.HospitalRepository
import com.dd.hospitalslist.data.entities.Hospital
import kotlinx.coroutines.flow.Flow

class HospitalViewModel(private val hospitalRepository: HospitalRepository) : ViewModel() {
    private val TAG = "HospitalViewModel"
    val hospitals: Flow<PagingData<Hospital>>
        get() {
            Log.i(TAG, "hospitalRepository.hospitals: ${hospitalRepository.hospitals}")
            return hospitalRepository.hospitals.cachedIn(viewModelScope)
        }

}