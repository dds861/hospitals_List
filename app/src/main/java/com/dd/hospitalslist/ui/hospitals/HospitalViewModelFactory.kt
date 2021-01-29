package com.dd.hospitalslist.ui.hospitals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dd.hospitalslist.data.HospitalRepository

class HospitalViewModelFactory(private val hospitalRepository: HospitalRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HospitalViewModel::class.java)) {
            HospitalViewModel(hospitalRepository) as T
        } else super.create(modelClass)
    }

}