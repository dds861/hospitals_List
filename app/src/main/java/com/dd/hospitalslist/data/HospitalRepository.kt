package com.dd.hospitalslist.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.dd.hospitalslist.data.entities.Hospital
import com.dd.hospitalslist.data.entities.Region

class HospitalRepository(private val hospitalDao: HospitalDao) {
    fun getHospitals(): LiveData<PagedList<Hospital>> {
        return hospitalDao.getHospitals().toLiveData(pageSize = 1)
    }

    fun getRegions(): LiveData<List<Region>> {
        return hospitalDao.getRegions()
    }
}