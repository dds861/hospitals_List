package com.dd.hospitalslist.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.RawQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.dd.hospitalslist.data.entities.Hospital
import com.dd.hospitalslist.data.entities.Region

@Dao
interface HospitalDao {

    @RawQuery(observedEntities = [Hospital::class])
    fun getHospitals(query:SupportSQLiteQuery): PagingSource<Int, Hospital>

    @Query("SELECT * FROM regions")
    fun getRegions(): LiveData<List<Region>>

}