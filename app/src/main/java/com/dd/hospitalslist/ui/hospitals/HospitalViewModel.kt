package com.dd.hospitalslist.ui.hospitals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dd.hospitalslist.data.HospitalModel
import com.dd.hospitalslist.data.HospitalRepository
import com.dd.hospitalslist.data.entities.Hospital
import kotlinx.coroutines.flow.Flow

class HospitalViewModel(private val hospitalRepository: HospitalRepository) : ViewModel() {
    private val TAG = "HospitalViewModel"


    fun getHospitals(hospitalModel: HospitalModel): Flow<PagingData<Hospital>> {
        return hospitalRepository.getHospitals(hospitalModel).cachedIn(viewModelScope)
    }

    val regionNames = hospitalRepository.getRegionNames()

    val categoriesName = hospitalRepository.getCategoryNames()

    var hospitalModel: HospitalModel = HospitalModel()
}