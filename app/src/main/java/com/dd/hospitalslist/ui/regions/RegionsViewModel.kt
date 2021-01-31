package com.dd.hospitalslist.ui.regions

import androidx.lifecycle.ViewModel
import com.dd.hospitalslist.data.HospitalRepository

class RegionsViewModel(private val repository: HospitalRepository) :ViewModel() {

    val regions = repository.getRegions()
}