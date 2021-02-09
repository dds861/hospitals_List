package com.dd.hospitalslist.data

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.RawQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.dd.hospitalslist.data.entities.Category
import com.dd.hospitalslist.data.entities.Hospital
import com.dd.hospitalslist.data.entities.Region

@Dao
interface HospitalDao {

    @RawQuery(observedEntities = [Hospital::class])
    fun getHospitals(query: SupportSQLiteQuery): PagingSource<Int, Hospital>

    @Query("SELECT * FROM regions ORDER BY name")
    fun getRegions(): LiveData<List<Region>>

    @Query("SELECT * FROM category ORDER BY name")
    fun getCategory(): LiveData<List<Category>>

    @Query("SELECT * FROM category WHERE name = :name  ORDER BY name")
    fun getCategoryByName(name: String): LiveData<List<Category>>


}