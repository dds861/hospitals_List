package com.dd.hospitalslist.ui.sections

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dd.hospitalslist.data.HospitalRepository

class SectionsViewModelFactory(private val repository: HospitalRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SectionsViewModel::class.java)) {
            SectionsViewModel(repository) as T
        } else super.create(modelClass)
    }


}