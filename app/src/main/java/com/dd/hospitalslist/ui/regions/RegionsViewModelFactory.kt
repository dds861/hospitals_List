package com.dd.hospitalslist.ui.regions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dd.hospitalslist.data.HospitalRepository

class RegionsViewModelFactory(private val repository: HospitalRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(RegionsViewModel::class.java)) {
            RegionsViewModel(repository) as T
        } else super.create(modelClass)
    }
}