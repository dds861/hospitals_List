package com.dd.hospitalslist.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import com.dd.hospitalslist.data.entities.Hospital
import com.dd.hospitalslist.data.entities.Region

@Dao
interface HospitalDao {

    @Query("SELECT * FROM hospitals")
    fun getHospitals(): DataSource.Factory<Int, Hospital>

    @Query("SELECT * FROM regions")
    fun getRegions(): LiveData<List<Region>>

}