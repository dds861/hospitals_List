package com.dd.hospitalslist.data

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import com.dd.hospitalslist.data.entities.Hospital

@Dao
interface HospitalDao {

    @Query("SELECT * FROM hospitals")
    fun getHospitals(): DataSource.Factory<Int, Hospital>

}