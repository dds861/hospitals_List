package com.dd.hospitalslist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.sqlite.db.SimpleSQLiteQuery
import com.dd.hospitalslist.data.entities.Hospital
import com.dd.hospitalslist.utils.Constants.STRING_EMPTY
import kotlinx.coroutines.flow.Flow

class HospitalRepository(private val hospitalDao: HospitalDao) {

    fun getHospitals(hospitalModel: HospitalModel): Flow<PagingData<Hospital>> {
        return Pager(
                config = PagingConfig(pageSize = 5, enablePlaceholders = true, prefetchDistance = 7),
                pagingSourceFactory = { hospitalDao.getHospitals(getFilteredQuery(hospitalModel)) }
        ).flow
    }


    fun getRegionNames(): LiveData<List<String>> {
        return hospitalDao.getRegions().map { list ->
            list.map {
                it.name
            }
        }
    }


    fun getCategoryNames(): LiveData<List<String>> {
        return hospitalDao.getCategory().map { list ->
            list.map {
                it.name
            }
        }
    }


    private fun getFilteredQuery(hospitalModel: HospitalModel): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM ${Hospital.TABLE_NAME} ")

        val regionsQuery = if (hospitalModel.regionState == RegionState.SPECIFIC_ITEM) {
            " ${Hospital.REGION} = \"${hospitalModel.regionName}\""
        } else STRING_EMPTY

        val categoriesQuery = if (hospitalModel.categoryState == CategoryState.SPECIFIC_ITEM) {
            " ${Hospital.CATEGORY} = \"${hospitalModel.categoryName}\""
        } else STRING_EMPTY

        when {
            regionsQuery.isEmpty() and categoriesQuery.isEmpty() -> {
                simpleQuery.append("")
            }
            regionsQuery.isNotEmpty() and categoriesQuery.isNotEmpty() -> {
                simpleQuery.append("WHERE $regionsQuery AND $categoriesQuery")
            }
            regionsQuery.isNotEmpty() or categoriesQuery.isNotEmpty() -> {
                simpleQuery.append("WHERE $regionsQuery  $categoriesQuery")
            }
        }


        simpleQuery.append(" ORDER BY ${Hospital.BRANCH}")
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}