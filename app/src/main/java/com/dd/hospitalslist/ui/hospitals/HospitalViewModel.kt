package com.dd.hospitalslist.ui.hospitals

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dd.hospitalslist.data.HospitalRepository
import com.dd.hospitalslist.data.entities.Hospital

class HospitalViewModel(private val hospitalRepository: HospitalRepository) : ViewModel() {

    val hospitals: LiveData<PagedList<Hospital>> = hospitalRepository.getHospitals()
}